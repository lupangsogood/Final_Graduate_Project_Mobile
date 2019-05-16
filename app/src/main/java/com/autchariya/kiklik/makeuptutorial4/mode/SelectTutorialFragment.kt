package com.autchariya.kiklik.makeuptutorial4.mode


import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.autchariya.kiklik.makeuptutorial4.R
import com.autchariya.kiklik.makeuptutorial4.dialog.SelectDialog


fun newInstace(url: String): SelectTutorialFragment {
    val fragment = SelectTutorialFragment()
    val agr = Bundle()
    agr.putString("url", url)
    fragment.arguments = agr
    return fragment
}


/**
 * A simple [Fragment] subclass.
 *
 */

class SelectTutorialFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_tutorial, container, false)
    }


}
