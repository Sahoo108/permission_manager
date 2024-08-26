package com.sahoo.permission_manager

interface IPermissionCallback {
    fun onPermissionGranted()
    fun onPermissionDenied(deniedPermission:List<String>)
    fun onPermissionPermanentlyDenied(permanentlyDeniedPermissions: List<String>)
    fun onOpenAppSettings()
}