package com.playlist.video.player.ui.videoplayer

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.playlist.video.player.R
import com.playlist.video.player.data.model.Video
import com.playlist.video.player.ext.gone
import com.playlist.video.player.ext.visible
import com.playlist.video.player.ui.common.BaseActivity
import kotlinx.android.synthetic.main.activity_video_player.*
import org.jetbrains.anko.sdk19.listeners.onClick
import org.jetbrains.anko.toast

class VideoPlayerActivity : BaseActivity(), Player.EventListener, Runnable {

    companion object {
        private const val EXTRA_PLAYLIST = "extra_playlist"
        private const val EXTRA_VIDEO = "extra_video"

        private const val KEY_VIDEO = "key_video"
        private const val KEY_POSITION = "key_position"

        fun createIntent(ctx: Context, playlist: List<Video>, position: Int): Intent {
            val intent = Intent(ctx, VideoPlayerActivity::class.java)
            intent.putParcelableArrayListExtra(EXTRA_PLAYLIST, ArrayList(playlist))
            intent.putExtra(EXTRA_VIDEO, position)
            return intent
        }
    }

    private lateinit var player: SimpleExoPlayer
    private val playlist: MutableList<Video> = mutableListOf()
    private var currentVideo = 0
    private var userSeeking = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        playlist.addAll(intent.extras.getParcelableArrayList(EXTRA_PLAYLIST))
        currentVideo = savedInstanceState?.getInt(KEY_VIDEO) ?: intent.extras.getInt(EXTRA_VIDEO)

        setupExoPlayer()
        startVideoStream(currentVideo)

        savedInstanceState?.let { player.seekTo(it.getLong(KEY_POSITION)) }

        btnPlay.onClick {
            player.playWhenReady = !btnPlay.isSelected
            btnPlay.isSelected = !btnPlay.isSelected
        }

        btnPrevious.onClick {
            startVideoStream(--currentVideo)
        }

        btnNext.onClick {
            startVideoStream(++currentVideo)
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    player.seekTo((progress.toDouble() / 100 * player.duration.toDouble()).toLong())
                }

                if (progress == 100) {
                    if (currentVideo != playlist.lastIndex) {
                        startVideoStream(++currentVideo)
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                userSeeking = true
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                userSeeking = false
            }
        })

        Handler().post(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_VIDEO, currentVideo)
        outState.putLong(KEY_POSITION, player.currentPosition)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }

    private fun setupExoPlayer() {
        val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(DefaultBandwidthMeter())
        val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)

        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector)
        exoPlayerView.player = player
    }

    private fun startVideoStream(position: Int) {
        val video = playlist[position]

        val uri = Uri.parse(video.videoUrl)
        val dataSourceFactory = DefaultDataSourceFactory(this, Util.getUserAgent(this, "videoPlayer"), null)
        val videoSource = HlsMediaSource(uri, dataSourceFactory, 1, null, null)

        player.addListener(this)
        player.prepare(videoSource)
        player.repeatMode = Player.REPEAT_MODE_OFF
        player.playWhenReady = true

        btnPlay.isSelected = true
        txtTitle.text = video.title
        seekBar.progress = 0

        // TODO TIME + FORMATTING

        when (currentVideo) {
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
        if (!userSeeking) {
            seekBar.progress = ((player.currentPosition.toDouble() / player.duration.toDouble() * 100)).toInt()
        }

        if (!isFinishing) {
            Handler().postDelayed(this, 300)
        }
    }

    override fun onPlayerError(error: ExoPlaybackException?) {
        toast(R.string.error_loading_video)
    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        when (playbackState) {
            Player.STATE_BUFFERING -> progressBar.visible()
            else -> progressBar.gone()
        }
    }

    override fun onLoadingChanged(isLoading: Boolean) {
        // Not implemented
    }

    override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters?) {
        // Not implemented
    }

    override fun onTracksChanged(trackGroups: TrackGroupArray?, trackSelections: TrackSelectionArray?) {
        // Not implemented
    }

    override fun onPositionDiscontinuity() {
        // Not implemented
    }

    override fun onRepeatModeChanged(repeatMode: Int) {
        // Not implemented
    }

    override fun onTimelineChanged(timeline: Timeline?, manifest: Any?) {
        // Not implemented
    }
}
