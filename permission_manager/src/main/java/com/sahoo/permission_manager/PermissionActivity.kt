package com.sahoo.permission_manager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class PermissionActivity : AppCompatActivity(), IPermissionCallback{

    private lateinit var permissionManager: PermissionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionManager = PermissionManager(this)
    }

    fun requestPermissions(permissionRequest: PermissionRequest) {
        permissionManager.requestPermissions(permissionRequest, this)
    }

    override fun onPermissionGranted() {
        // will be implemented by the subclass
    }

    override fun onPermissionDenied(deniedPermission: List<String>) {
        // will be implemented by the subclass
    }

    override fun onPermissionPermanentlyDenied(permanentlyDeniedPermissions: List<String>) {
        // will be implemented by the subclass
    }
}