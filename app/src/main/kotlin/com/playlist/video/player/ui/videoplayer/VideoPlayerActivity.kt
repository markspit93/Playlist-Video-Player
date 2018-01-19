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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        playlist.addAll(intent.extras.getParcelableArrayList(EXTRA_PLAYLIST))

        setupExoPlayer()
        startVideoStream(intent.extras.getInt(EXTRA_POSITION))
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
        val videoUri = Uri.parse(playlist[position].videoUrl)
        val dataSourceFactory = DefaultDataSourceFactory(this, Util.getUserAgent(this, "videoPlayer"), null)
        val videoSource = HlsMediaSource(videoUri, dataSourceFactory, 1, null, null)

        player.prepare(videoSource)
        player.repeatMode = Player.REPEAT_MODE_OFF
        player.playWhenReady = true
    }
}
