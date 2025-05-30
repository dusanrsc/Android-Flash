package com.infinitysoftware.flash

import android.content.Context
import android.hardware.camera2.CameraManager
import android.os.Bundle
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        val cameraId = cameraManager.cameraIdList.firstOrNull { id ->
            cameraManager.getCameraCharacteristics(id)
                .get(android.hardware.camera2.CameraCharacteristics.FLASH_INFO_AVAILABLE) == true }

        cameraId?.let {
            try {
                val newState = !FlashState.isOn
                cameraManager.setTorchMode(it, newState)
                FlashState.isOn = newState
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        finish()
    }
}
