package com.autchariya.kiklik.makeuptutorial4.mode

import android.Manifest
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.autchariya.kiklik.makeuptutorial4.R
import kotlinx.android.synthetic.main.activity_mode.*

class
ModeActivity : AppCompatActivity() {

    //private var tabLayout: TabLayout? = null
    //private var viewPager: ViewPager? = null
    private var adapter: ViewPagerAdapter? = null
    internal var position: Int = 0
    val REQUEST_CAMERA_PERMISSION = 200;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mode)
        requestPermission()
        tb1.setNavigationOnClickListener { finish() }



        adapter = ViewPagerAdapter(supportFragmentManager)
        view_pager.setAdapter(adapter)
        tab_layout.setupWithViewPager(view_pager)
        tab_layout.setTabGravity(TabLayout.GRAVITY_FILL)
        tab_layout.setTabMode(TabLayout.MODE_FIXED)
        //viewPager.addOnPageChangeListener(this);
        //viewPager.setCurrentItem(0);

        val bundle = intent.extras
        if (bundle != null) {
            position = bundle.getInt("position")
            view_pager.setCurrentItem(position)
        }
    }


     fun requestPermission(){

         if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

             ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_CAMERA_PERMISSION)
             return
         }

    }



    }

