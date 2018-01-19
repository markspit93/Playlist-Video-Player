package com.playlist.video.player.ui.videoplayer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.playlist.video.player.R
import com.playlist.video.player.data.model.Video
import com.playlist.video.player.ui.common.BaseActivity

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)
    }
}
