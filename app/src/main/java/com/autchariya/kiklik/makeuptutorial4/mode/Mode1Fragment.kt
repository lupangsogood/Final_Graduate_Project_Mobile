package com.autchariya.kiklik.makeuptutorial4.mode


import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.autchariya.kiklik.makeuptutorial4.R
import com.autchariya.kiklik.makeuptutorial4.dialog.SelectDialog
import com.autchariya.kiklik.makeuptutorial4.simulate.showImage
import kotlinx.android.synthetic.main.fragment_mode1.*


fun newInstace(): Mode1Fragment {
    return Mode1Fragment()
}




class Mode1Fragment : Fragment() {
    //    private val btnClose: Button? = null
//    private var btn1,: Button? = null
//    private var btn2:Button? = null
//    private var btn3:Button? = null

    var Tutor1: String = "fvX8_b4U4I8"
    var Tutor2: String = "FY8mNzxM8uw"
    var Tutor3: String = "zW-MqH_iYm8"
    var TexeTu1: String = ""
    var TexeTu2: String = "แต่งหน้าสวยเหวาน"
    var TexeTu3: String = "แต่งหน้าสวยแซ่บ"

    var Mode_ : String = ""
    var Style_ : String = ""

    var Mode_Tutorial : String = ""
    var Style_Tutorial : String = ""
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {



        return inflater.inflate(R.layout.fragment_mode1, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_tutorial.setOnClickListener { Dialog1() }
        btn_tutorial1.setOnClickListener { Dialog2() }
        btn_tutorial2.setOnClickListener { Dialog3() }


        btn_simulate.setOnClickListener { simulate() }
        btn_simulate1.setOnClickListener { simulate1() }
        btn_simulate2.setOnClickListener { simulate2() }



    }

    fun save_Mode_style () {
        val sharePref = this.activity!!.getSharedPreferences("makeup_info",Context.MODE_PRIVATE)

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
    //เปลี่ยนหน้าจาก Fragment Kotlin ไป Activity Java
    // ฟังก์ชันจำลองใบหน้า
    private fun simulate() {
        val intent = Intent(activity!!, showImage::class.java)
        intent.putExtra("Mode1", "WorkingOld")
        startActivity(intent)
        activity!!.finish()

        Mode_ = "1"
        Style_ = "1"

        save_Mode_style()

    }

    private fun simulate1() {

        val intent = Intent(activity!!,showImage::class.java)
        intent.putExtra("Mode1","WorkingChic")
        startActivity(intent)
        activity!!.finish()

        Mode_ = "1"
        Style_ = "2"

        save_Mode_style()
    }

    private fun simulate2() {

        val intent = Intent(activity!!,showImage::class.java)
        intent.putExtra("Mode1","WorkingCool")
        startActivity(intent)
        activity!!.finish()

        Mode_ = "1"
        Style_ = "3"

        save_Mode_style()
    }

    private fun Dialog1() {

        SelectDialog.newInstance(Tutor1, TexeTu1).show(fragmentManager, "select")

        Mode_Tutorial = "1"
        Style_Tutorial = "1"

        save_ModeStyle_Tutorial()

    }

    private fun Dialog2() {
        SelectDialog.newInstance(Tutor2, TexeTu2).show(fragmentManager, "select")

        Mode_Tutorial = "1"
        Style_Tutorial = "2"

        save_ModeStyle_Tutorial()

    }

    private fun Dialog3() {
        SelectDialog.newInstance(Tutor3, TexeTu3).show(fragmentManager, "select")

        Mode_Tutorial = "1"
        Style_Tutorial = "3"

        save_ModeStyle_Tutorial()
    }
}




