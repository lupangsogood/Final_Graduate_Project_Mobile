package com.jozzee.android.cores.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat

class PermissionUtil {
    companion object {
        fun checkSelfPermission(conText: Context, permissions: Array<String>): Boolean {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                for (permission in permissions) {
                    if (ContextCompat.checkSelfPermission(conText, permission) != PackageManager.PERMISSION_GRANTED) {
                        // Permission is not granted
                        return false
                    }
                }
            }
            return true
        }

        fun requestPermission(activity: Activity, permissions: Array<String>, requestCode: Int) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return
            ActivityCompat.requestPermissions(activity, permissions, requestCode)
        }

        fun isPermissionGranted(permissions: Array<String>, grantResults: IntArray): Boolean {
            if (grantResults.indices.isEmpty()) return false
            for (i in permissions.indices) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) return false
            }
            return true
        }
    }
}