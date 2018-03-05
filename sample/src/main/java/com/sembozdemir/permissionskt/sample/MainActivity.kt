package com.sembozdemir.permissionskt.sample

import android.Manifest
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.sembozdemir.permissionskt.askPermissions
import com.sembozdemir.permissionskt.handlePermissionsResult
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonPermissionsCamera.setOnClickListener {
            askPermissions(Manifest.permission.CAMERA) {
                onGranted {
                    toast("Camera permission is granted.")
                }

                onDenied {
                    toast("Camera permission is denied")
                }

                onShowRationale { request ->
                    snack("You should grant permission for Camera") { request.retry() }
                }

                onNeverAskAgain {
                    toast("Never ask again for camera permission")
                }
            }
        }

        buttonPermissionsPhoneAndSms.setOnClickListener {
            askPermissions(Manifest.permission.CALL_PHONE, Manifest.permission.READ_SMS) {
                onGranted {
                    toast("Call Phone and Read Sms permission is granted.")
                }

                onDenied {
                    it.forEach {
                        when (it) {
                            Manifest.permission.CALL_PHONE -> toast("Call Phone is denied")
                            Manifest.permission.READ_SMS -> toast("Read Sms is denied")
                        }
                    }
                }

                onShowRationale { request ->

                    var permissions = ""
                    request.permissions.forEach {

                        permissions += when (it) {
                            Manifest.permission.CALL_PHONE -> " Call Phone"
                            Manifest.permission.READ_SMS -> " Read Sms"
                            else -> ""
                        }

                    }

                    snack("You should grant permission for $permissions") {
                        request.retry()
                    }
                }

                onNeverAskAgain {
                    it.forEach {
                        when (it) {
                            Manifest.permission.CALL_PHONE -> toast("Never ask again for Call Phone")
                            Manifest.permission.READ_SMS -> toast("Never ask again for Read Sms")
                        }
                    }
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        handlePermissionsResult(requestCode, permissions, grantResults)
    }

    private fun toast(messsage: String) {
        Toast.makeText(this, messsage, Toast.LENGTH_LONG).show()
    }

    private fun snack(message: String, action: () -> Unit = {}) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry", { _ -> action() })
                .show()
    }
}
