package com.playlist.video.player.ui.videoplayer

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
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
import com.playlist.video.player.ui.common.BaseActivity
import kotlinx.android.synthetic.main.activity_video_player.*
import org.jetbrains.anko.sdk19.listeners.onClick

class VideoPlayerActivity : BaseActivity() {

    companion object {
        private val EXTRA_PLAYLIST = "extra_playlist"
        private val EXTRA_POSITION = "extra_position"

        fun createIntent(ctx: Context, playlist: List<Video>, position: Int): Intent {
            val intent = Intent(ctx, VideoPlayerActivity::class.java)
            intent.putParcelableArrayListExtra(EXTRA_PLAYLIST, ArrayList(playlist))
            intent.putExtra(EXTRA_POSITION, position)
            return intent
        }
    }

    private lateinit var player: SimpleExoPlayer
    private val playlist: MutableList<Video> = mutableListOf()
    private var currentPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        playlist.addAll(intent.extras.getParcelableArrayList(EXTRA_PLAYLIST))
        currentPosition = savedInstanceState?.getInt(EXTRA_POSITION) ?: intent.extras.getInt(EXTRA_POSITION)

        setupExoPlayer()
        startVideoStream(currentPosition)

        btnPlay.onClick {
            player.playWhenReady = !btnPlay.isSelected
            btnPlay.isSelected = !btnPlay.isSelected
        }

        btnPrevious.onClick {
            startVideoStream(--currentPosition)
        }

        btnNext.onClick {
            startVideoStream(++currentPosition)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(EXTRA_POSITION, currentPosition)
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

        player.prepare(videoSource)
        player.repeatMode = Player.REPEAT_MODE_OFF
        player.playWhenReady = true

        btnPlay.isSelected = true
        txtTitle.text = video.title

        // TODO TIME + FORMATTING

        when (currentPosition) {
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
}
