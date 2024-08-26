package com.sahoo.permission_manager

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

private val PERMISSIONS = mapOf(Manifest.permission.ACCESS_COARSE_LOCATION to "Location")

/**
 * @param activity : activity
 */
class PermissionManager(private val activity: AppCompatActivity) : IPermissionManager {

    private var callback: IPermissionCallback? = null
    private var launcher: ActivityResultLauncher<Array<String>>? = null


    /**
     * @param callback : register callback and launcher
     */
    override fun registerPermissionLauncher(callback: IPermissionCallback?) {
        this.callback = callback
        launcher = activity.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->

            val deniedPermissions = permissions.filterValues { !it }.keys.toList()
            if (deniedPermissions.isEmpty()) {
                callback?.onPermissionGranted()
            } else {
                val permanentlyDeniedPermissions = deniedPermissions.filter { permission ->
                    !ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)
                }

                if (permanentlyDeniedPermissions.isNotEmpty()) {
                    showAlert(permanentlyDeniedPermissions)
                } else {
                    callback?.onPermissionDenied(deniedPermissions)
                }
            }
        }
    }

    /**
     * @permissionRequest : permission request object with permission list
     */
    override fun requestPermissions(permissionRequest: PermissionRequest) {
        val permissionsToRequest = permissionRequest.permissions.filter { permission ->
            ContextCompat.checkSelfPermission(
                activity,
                permission
            ) != PackageManager.PERMISSION_GRANTED
        }
        if (permissionsToRequest.isEmpty()) {
            // All permissions are already granted
            callback?.onPermissionGranted()
        } else {
            // Check which of the permissions are permanently denied
            // Request the permissions that are not granted
            launcher?.launch(permissionsToRequest.toTypedArray())
        }
    }

    // Open app settings to grant permission
    private fun openAppSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.parse("package:${activity.packageName}")
        }
        activity.startActivity(intent)
    }

    // alert to be shown to user when permissions are permanently denied
    private fun showAlert(permission: List<String>) {
        val title = permission.map { PERMISSIONS[it] ?: "" }
        val alertBuilder = AlertDialog.Builder(activity)
        alertBuilder.setMessage(
            activity.getString(
                R.string.open_settings_desc,
                title.joinToString(),
                title.joinToString()
            )
        )
        alertBuilder.setPositiveButton(
            activity.getString(R.string.open_settings_title)
        ) { _, _ ->
            callback?.onOpenAppSettings()
            openAppSettings()
        }
        alertBuilder.setNegativeButton(
            activity.getString(R.string.cancel_title)
        ) { _, _ -> callback?.onPermissionPermanentlyDenied(permission) }
        alertBuilder.setCancelable(false)
        alertBuilder.show()
    }
}

