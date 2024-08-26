package com.sahoo.permission_manager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * extend this class to use permission manager at top level in activity
 */
open class PermissionActivity : AppCompatActivity(), IPermissionCallback{

    private lateinit var permissionManager: PermissionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionManager = PermissionManager(this)
        permissionManager.registerPermissionLauncher(this)
    }

    fun requestPermissions(permissionRequest: PermissionRequest) {
        permissionManager.requestPermissions(permissionRequest)
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

    override fun onOpenAppSettings() {
        // will be implemented by the subclass
    }
}