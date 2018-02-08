package com.playlist.video.player.ui.videoplayer

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.WindowManager
import android.widget.SeekBar
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.playlist.video.player.R
import com.playlist.video.player.data.model.Video
import com.playlist.video.player.ext.gone
import com.playlist.video.player.ext.visible
import com.playlist.video.player.ui.common.BaseActivity
import com.playlist.video.player.util.formatTime
import kotlinx.android.synthetic.main.activity_video_player.*
import org.jetbrains.anko.sdk19.listeners.onClick
import org.jetbrains.anko.toast

class VideoPlayerActivity : BaseActivity(), Runnable {

    companion object {
        private const val EXTRA_PLAYLIST = "extra_playlist"
        private const val EXTRA_PLAYLIST_POSITION = "extra_playlist_position"

        private const val KEY_PLAYLIST_POSITION = "key_playlist_position"
        private const val KEY_VIDEO_POSITION = "key_video_position"

        fun createIntent(ctx: Context, playlist: List<Video>, position: Int): Intent {
            val intent = Intent(ctx, VideoPlayerActivity::class.java)
            intent.putParcelableArrayListExtra(EXTRA_PLAYLIST, ArrayList(playlist))
            intent.putExtra(EXTRA_PLAYLIST_POSITION, position)
            return intent
        }
    }

    private lateinit var player: SimpleExoPlayer
    private val playlist: MutableList<Video> = mutableListOf()
    private var playlistPosition = 0
    private var userSeeking = false
    private var checkTime = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        playlist.addAll(intent.extras.getParcelableArrayList(EXTRA_PLAYLIST))
        playlistPosition = savedInstanceState?.getInt(KEY_PLAYLIST_POSITION) ?: intent.extras.getInt(EXTRA_PLAYLIST_POSITION)

        setupExoPlayer()
        startVideoStream(playlistPosition)

        savedInstanceState?.let { player.seekTo(it.getLong(KEY_VIDEO_POSITION)) }

        btnPlay.onClick {
            player.playWhenReady = !btnPlay.isSelected
            btnPlay.isSelected = !btnPlay.isSelected
        }

        btnPrevious.onClick { startVideoStream(--playlistPosition) }
        btnNext.onClick { startVideoStream(++playlistPosition) }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            private var userSeek: Long = 0

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    userSeek = (progress.toDouble() / 100 * playlist[playlistPosition].duration.toDouble()).toLong()
                } else if (progress == 100 && playlistPosition != playlist.lastIndex) {
                    startVideoStream(++playlistPosition)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                userSeeking = true
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                userSeeking = false
                player.seekTo(userSeek)

                if (seekBar.progress == 100 && playlistPosition != playlist.lastIndex) {
                    startVideoStream(++playlistPosition)
                }

                userSeek = 0
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_PLAYLIST_POSITION, playlistPosition)
        outState.putLong(KEY_VIDEO_POSITION, player.currentPosition)
        super.onSaveInstanceState(outState)
    }

    override fun onStart() {
        super.onStart()
        player.playWhenReady = true
    }

    override fun onPause() {
        player.playWhenReady = false
        super.onPause()
    }

    override fun onDestroy() {
        player.release()
        super.onDestroy()
    }

    private fun setupExoPlayer() {
        val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(DefaultBandwidthMeter())
        player = ExoPlayerFactory.newSimpleInstance(this, DefaultTrackSelector(videoTrackSelectionFactory))
        player.addListener(object : Player.DefaultEventListener(){
            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                when (playbackState) {
                    Player.STATE_BUFFERING -> {
                        checkTime = false
                        progressBar.visible()
                    }

                    Player.STATE_READY ->  {
                            checkTime = true
                            Handler().removeCallbacks(this@VideoPlayerActivity)
                            Handler().removeCallbacksAndMessages(null)
                            progressBar.gone()
                            Handler().post(this@VideoPlayerActivity)
                    }
                }
            }

            override fun onPlayerError(error: ExoPlaybackException?) {
                toast(R.string.error_loading_video)
            }
        })

        exoPlayerView.player = player
    }

    private fun startVideoStream(position: Int) {
        val video = playlist[position]
        val uri = Uri.parse(video.videoUrl)
        val dataSourceFactory = DefaultDataSourceFactory(this, Util.getUserAgent(this, "videoPlayer"), null)
        val videoSource = HlsMediaSource(uri, dataSourceFactory, 1, null, null)

        player.prepare(videoSource)
        player.repeatMode = Player.REPEAT_MODE_OFF
        player.playWhenReady = true

        btnPlay.isSelected = true
        txtTitle.text = video.title
        seekBar.progress = 0

        txtCurrentTime.text = formatTime(0)
        txtTotalDuration.text = formatTime(video.duration)

        when (playlistPosition) {
            0 -> {
                btnPrevious.isEnabled = false
            }
            playlist.lastIndex -> {
                btnNext.isEnabled = false
            }
            else -> {
                btnPrevious.isEnabled = true
                btnNext.isEnabled = true
            }
        }
    }

    override fun run() {
        if (!isFinishing && !userSeeking && checkTime) {
            txtCurrentTime.text = formatTime(player.currentPosition)
            seekBar.progress = ((player.currentPosition.toDouble() / playlist[playlistPosition].duration.toDouble() * 100)).toInt()
            Handler().postDelayed(this, 200)
        }
    }
}
