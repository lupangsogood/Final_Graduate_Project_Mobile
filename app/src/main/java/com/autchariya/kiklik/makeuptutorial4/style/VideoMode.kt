package com.autchariya.kiklik.makeuptutorial4.style


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController

import com.autchariya.kiklik.makeuptutorial4.R
import com.autchariya.kiklik.makeuptutorial4.dialog.SelectDialog
import android.widget.Toast;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.ErrorReason;
import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;
import kotlinx.android.synthetic.main.activity_video_tutorial.*
import kotlinx.android.synthetic.main.fragment_video_mode.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 *
 */
class VideoMode : Fragment() {

    lateinit var URL :String


    companion object {
        fun newInstance(url: String): VideoMode {
            val fragment: VideoMode = VideoMode()
            val bundle: Bundle = Bundle()
            bundle.putString("url", url)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_mode, container, false)
    }


}
