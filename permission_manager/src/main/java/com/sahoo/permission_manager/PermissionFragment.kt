package com.sahoo.permission_manager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * extend this class to use permission manager at top level in fragment
 */
open class PermissionFragment : Fragment(), IPermissionCallback {

    private lateinit var permissionManager: PermissionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionManager = PermissionManager(activity as AppCompatActivity)
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