package com.sahoo.permissionmanager

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sahoo.permission_manager.IPermissionCallback
import com.sahoo.permission_manager.PermissionActivity
import com.sahoo.permission_manager.PermissionManager
import com.sahoo.permission_manager.PermissionRequest
import com.sahoo.permissionmanager.ui.theme.PermissionManagerTheme

class MainActivity : PermissionActivity(), IPermissionCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PermissionManagerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
                    MainScreen(object : IOnClick<String> {
                        override fun onClick(data: String) {
                            Toast.makeText(this@MainActivity, "Clicked", Toast.LENGTH_SHORT).show()
                            requestPermissions(PermissionRequest(listOf(Manifest.permission.ACCESS_COARSE_LOCATION), ""))
                        }
                    })
                }
            }
        }
    }

    override fun onPermissionDenied(deniedPermission: List<String>) {
        super.onPermissionDenied(deniedPermission)
        Toast.makeText(this@MainActivity, "onPermissionDenied", Toast.LENGTH_SHORT).show()
    }

    override fun onPermissionGranted() {
        super.onPermissionGranted()
        Toast.makeText(this@MainActivity, "onPermissionGranted", Toast.LENGTH_SHORT).show()
    }

    override fun onPermissionPermanentlyDenied(permanentlyDeniedPermissions: List<String>) {
        super.onPermissionPermanentlyDenied(permanentlyDeniedPermissions)
        Toast.makeText(this@MainActivity, "onPermissionPermanentlyDenied", Toast.LENGTH_SHORT).show()
    }

    override fun onOpenAppSettings() {
        super.onOpenAppSettings()
        Toast.makeText(this@MainActivity, "onOpenAppSettings", Toast.LENGTH_SHORT).show()
    }

}

interface IOnClick<T> {
    fun onClick(data: T)
}

@Composable
fun MainScreen(onCLick: IOnClick<String>) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Column() {
            Text(text = "Experiment")
            Button(onClick = { onCLick.onClick("") }) {
                Text(text = "Location Permission")
            }
            Button(onClick = { onCLick.onClick("") }) {
                Text(text = "Camera Permission")
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PermissionManagerTheme {
        MainScreen(object : IOnClick<String> {
            override fun onClick(data: String) {

            }
        })
    }
}