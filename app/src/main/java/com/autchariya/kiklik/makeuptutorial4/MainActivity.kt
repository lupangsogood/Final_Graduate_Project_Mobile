package com.autchariya.kiklik.makeuptutorial4

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import com.autchariya.kiklik.makeuptutorial4.camera.Camera1Activity
//import com.autchariya.kiklik.makeuptutorial4.database.AppDatabase
import com.autchariya.kiklik.makeuptutorial4.dialog.StartDialog
//import com.autchariya.kiklik.makeuptutorial4.entity.User
import com.autchariya.kiklik.makeuptutorial4.mode.ModeActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    val REQUEST_CAMERA_PERMISSION = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        StartDialog.newInstance().show(supportFragmentManager,"start")



        ibtn_mode1.setOnClickListener {
            val intent = Intent(this@MainActivity, ModeActivity::class.java)
            startActivity(intent)
        }
        ibtn_mode2.setOnClickListener {
            val intent = Intent(this@MainActivity, ModeActivity::class.java)
            intent.putExtra("position", 1)
            startActivity(intent)
        }
        ibtn_mode3.setOnClickListener {
            val intent = Intent(this@MainActivity, ModeActivity::class.java)
            intent.putExtra("position", 2)
            startActivity(intent)
        }
        ibtn_mode4.setOnClickListener {
            val intent = Intent(this@MainActivity, ModeActivity::class.java)
            intent.putExtra("position", 3)
            startActivity(intent)
        }
        camera1.setOnClickListener {
            val intent = Intent(this@MainActivity, Camera1Activity::class.java)
            startActivity(intent)
        }
    }
}
