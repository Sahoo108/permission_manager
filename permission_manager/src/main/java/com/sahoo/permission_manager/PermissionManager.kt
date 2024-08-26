package com.sahoo.permission_manager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class PermissionManager(private val activity: AppCompatActivity) {
    private var permissionLauncher: ActivityResultLauncher<Array<String>>
    private var callback: IPermissionCallback? = null

    init {
        permissionLauncher = activity.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            //val grantedPermissions = permissions.filterValues { it }.keys.toList()
            val deniedPermissions = permissions.filterValues { !it }.keys.toList()

            if (deniedPermissions.isEmpty()) {
                callback?.onPermissionGranted()
            } else {
                val permanentlyDeniedPermissions = deniedPermissions.filter { permission ->
                    ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)
                        .not()
                }

                if (permanentlyDeniedPermissions.isNotEmpty()) {
                    callback?.onPermissionPermanentlyDenied(permanentlyDeniedPermissions)
                } else {
                    callback?.onPermissionDenied(deniedPermissions)
                }
            }
        }
    }

    fun requestPermissions(permissionRequest: PermissionRequest, callback: IPermissionCallback) {
        this.callback = callback
        permissionLauncher.launch(permissionRequest.permissions.toTypedArray())
    }

}