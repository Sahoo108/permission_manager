package com.sahoo.permission_manager

interface IPermissionManager {
    fun registerPermissionLauncher(callback: IPermissionCallback?)
    fun requestPermissions(permissionRequest: PermissionRequest)
}