package com.sahoo.permission_manager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

open class PermissionFragment : Fragment(), IPermissionCallback {

    private lateinit var permissionManager: PermissionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionManager = PermissionManager(activity as AppCompatActivity)
    }

    fun requestPermissions(permissionRequest: PermissionRequest) {
        permissionManager.requestPermissions(permissionRequest, this)
    }

    override fun onPermissionGranted() {

    }

    override fun onPermissionDenied(deniedPermission: List<String>) {

    }

    override fun onPermissionPermanentlyDenied(permanentlyDeniedPermissions: List<String>) {

    }
}