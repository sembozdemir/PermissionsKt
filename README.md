# PermissionsKt
Handle Android Runtime Permissions in Kotlin way

## How to add
PermissionsKt is published with [JitPack.io](https://jitpack.io).
To add this library to your project, add these lines to your build.gradle

```
repositories {
    maven { url "https://jitpack.io" }
}

dependencies {
    compile 'com.github.sembozdemir:PermissionsKt:1.0.0'
}
```


## How to use

- #### It is so simple. Just ask for permission you want anywhere on your Activity

```
askPermissions(Manifest.permission.CAMERA) {
    onGranted {
        showCamera()
    }
}
```

- #### Then, call delegated extension function on [onRequestPermissionsResult]

```
override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
    handlePermissionsResult(requestCode, permissions, grantResults)
}
```

- #### You may also listen other callbacks

```
askPermissions(Manifest.permission.CAMERA) {
    onGranted {
        showCamera()
    }

    onDenied {
        Log.d(TAG, "Camera permission is denied.")
    }

    onShowRationale { request ->
        Snackbar.make(rootView, "You should grant Camera permission", Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry") { request.retry() }
                .show()
    }

    onNeverAskAgain {
        Log.d(TAG, "Never ask again for Camera permission")
    }
}
```

- #### You may also ask for multiple permissions

```
askPermissions(Manifest.permission.CALL_PHONE, Manifest.permission.READ_SMS) {
    onGranted {
        makeCall()
        sendSms()
    }

    onDenied { permissions ->
        permissions.forEach {
            when (it) {
                Manifest.permission.CALL_PHONE -> Log.d(TAG, "Call Phone is denied")
                Manifest.permission.READ_SMS -> Log.d(TAG, "Read Sms is denied")
            }
        }
    }

    onShowRationale { request ->
        Snackbar.make(rootView, "You should grant Call Phone and Read Sms permissions, Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry") { request.retry() }
                .show()
    }

    onNeverAskAgain { permissions ->
        permissions.forEach {
            when (it) {
                Manifest.permission.CALL_PHONE -> Log.d(TAG, "Never ask again for Call Phone")
                Manifest.permission.READ_SMS -> Log.d(TAG, "Never ask again for Read Sms")
            }
        }
    }
}
```

You could view `sample` module for more details.

## Licence
The MIT License (MIT)

Copyright (c) 2018 Semih Bozdemir

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
