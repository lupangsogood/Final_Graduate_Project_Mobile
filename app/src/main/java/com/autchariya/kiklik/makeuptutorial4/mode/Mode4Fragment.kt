package com.autchariya.kiklik.makeuptutorial4.mode


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.autchariya.kiklik.makeuptutorial4.R
import com.autchariya.kiklik.makeuptutorial4.dialog.SelectDialog
import com.autchariya.kiklik.makeuptutorial4.simulate.showImage
import kotlinx.android.synthetic.main.fragment_mode4.*

/**
 * A simple [Fragment] subclass.
 *
 */
class Mode4Fragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance() = Mode4Fragment()
    }

    var Tutor1: String = "55xM7Roer0Y"
    var Tutor2: String = "LdC5cKBJrXQ"
    var Tutor3: String = "t75_kC1QQLc"
    var TexeTu1 : String = "แต่งหน้าสวยเปรี้ยว"
    var TexeTu2 : String = "แต่งหน้าสวยเหวาน"
    var TexeTu3 : String = "แต่งหน้าสวยแซ่บ"

    var Mode_ : String = ""
    var Style_ : String = ""

    var Mode_Tutorial : String = ""
    var Style_Tutorial : String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mode4, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_tutorial.setOnClickListener  { Dialog1() }
        btn_tutorial1.setOnClickListener { Dialog2() }
        btn_tutorial2.setOnClickListener { Dialog3() }

        btn_simulate.setOnClickListener  { simulate()  }
        btn_simulate1.setOnClickListener { simulate1() }
        btn_simulate2.setOnClickListener { simulate2() }
    }

    fun save_Mode_style () {
        val sharePref = this.activity!!.getSharedPreferences("makeup_info", Context.MODE_PRIVATE)

        val editor = sharePref.edit()
        editor.putString("MODE",Mode_)
        editor.putString("STYLE",Style_)
        editor.apply()


    }

    fun save_ModeStyle_Tutorial(){

        val sharePref_Tutorial = this.activity!!.getSharedPreferences("makeup_tutorial",Context.MODE_PRIVATE)

        val editor = sharePref_Tutorial.edit()
        editor.putString("MODE_TUTORIAL",Mode_Tutorial)
        editor.putString("STYLE_TUTORIAL",Style_Tutorial)
        editor.apply()


    }

    private fun simulate() {
        val intent = Intent(activity!!, showImage::class.java)
        intent.putExtra("Mode2", "WorkingOld")
        startActivity(intent)
        activity!!.finish()

        Mode_ = "4"
        Style_ = "1"

        save_Mode_style()

    }

    private fun simulate1() {

        val intent = Intent(activity!!, showImage::class.java)
        intent.putExtra("Mode2","WorkingChic")
        startActivity(intent)
        activity!!.finish()

        Mode_ = "4"
        Style_ = "2"

        save_Mode_style()
    }

    private fun simulate2() {

        val intent = Intent(activity!!, showImage::class.java)
        intent.putExtra("Mode2","WorkingCool")
        startActivity(intent)
        activity!!.finish()

        Mode_ = "4"
        Style_ = "3"

        save_Mode_style()
    }

    private fun Dialog1() {
        SelectDialog.newInstance(Tutor1,TexeTu1).show(fragmentManager, "select")

        Mode_Tutorial = "4"
        Style_Tutorial = "1"

        save_ModeStyle_Tutorial()

    }
    private fun Dialog2() {

        SelectDialog.newInstance(Tutor2,TexeTu2).show(fragmentManager, "select")

        Mode_Tutorial = "4"
        Style_Tutorial = "2"

        save_ModeStyle_Tutorial()
    }
    private fun Dialog3() {

        SelectDialog.newInstance(Tutor3,TexeTu3).show(fragmentManager, "select")

        Mode_Tutorial = "4"
        Style_Tutorial = "3"

        save_ModeStyle_Tutorial()
    }
}
