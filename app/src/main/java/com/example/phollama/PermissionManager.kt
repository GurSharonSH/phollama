package com.example.myandroidapp

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionsManager {

    fun requestPermissions(activity: AppCompatActivity) {
        val permissions = arrayOf(Manifest.permission.READ_CONTACTS)
        if (permissions.any { ContextCompat.checkSelfPermission(activity, it) != PackageManager.PERMISSION_GRANTED }) {
            ActivityCompat.requestPermissions(activity, permissions, REQUEST_CODE)
        }
    }

    private const val REQUEST_CODE = 1
}