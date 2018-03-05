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
        Toast.makeText(this, "Camera permission is granted.", Toast.LENGTH_LONG).show()
    }
}
```

- #### You may also listen other callbacks

```
askPermissions(Manifest.permission.CAMERA) {
    onGranted {
        Toast.makeText(this, "Camera permission is granted.", Toast.LENGTH_LONG).show()
    }

    onDenied {
        Toast.makeText(this, "Camera permission is denied.", Toast.LENGTH_LONG).show()
    }

    onShowRationale { request ->
        Snackbar.make(rootView, "You should grant Camera permission", Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry") { request.retry() }
                .show()
    }

    onNeverAskAgain {
        Toast.makeText(this, "Never ask again for camera permission", Toast.LENGTH_LONG).show()
    }
}
```

- #### You may also ask for multiple permissions

```
askPermissions(Manifest.permission.CALL_PHONE, Manifest.permission.READ_SMS) {
    onGranted {
        Toast.makeText(this, "Call Phone and Read Sms permission is granted.", Toast.LENGTH_LONG).show()
    }

    onDenied {
        it.forEach {
            when (it) {
                Manifest.permission.CALL_PHONE -> 
                    Toast.makeText(this, "Call Phone is denied", Toast.LENGTH_LONG).show()
                Manifest.permission.READ_SMS -> 
                    Toast.makeText(this, "Read Sms is denied", Toast.LENGTH_LONG).show()
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

        Snackbar.make(rootView, "You should grant permission for $permissions", Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry") { request.retry() }
                .show()
    }

    onNeverAskAgain {
        it.forEach {
            when (it) {
                Manifest.permission.CALL_PHONE -> 
                    Toast.makeText(this, "Never ask again for Call Phone", Toast.LENGTH_LONG).show()
                Manifest.permission.READ_SMS -> 
                    Toast.makeText(this, "Never ask again for Read Sms", Toast.LENGTH_LONG).show()
            }
        }
    }
}
```

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
