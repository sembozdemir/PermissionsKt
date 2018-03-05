package com.sembozdemir.permissionskt

interface PermissionCallbacks {

    /**
     * Will be invoked if all permissions are granted.
     */
    fun onGranted()

    /**
     * Will be invoked if any permission is denied.
     *
     * @param permissions list of permissions user denied
     */
    fun onDenied(permissions: List<String>)

    /**
     * Will be invoked if rationale message should be shown.
     *
     * @param permissionRequest use this to retry permission request
     */
    fun onShowRationale(permissionRequest: PermissionRequest)

    /**
     * Will be invoked if any permission is permanently denied.
     *
     * @param permissions list of permissions user select never ask again
     */
    fun onNeverAskAgain(permissions: List<String>)
}

/**
 * DSL implementation for [PermissionCallbacks].
 */
class PermissionCallbacksDSL : PermissionCallbacks {

    private var onGranted: () -> Unit = {}
    private var onDenied: (permissions: List<String>) -> Unit = {}
    private var onShowRationale: (permissionRequest: PermissionRequest) -> Unit = {}
    private var onNeverAskAgain: (permissions: List<String>) -> Unit = {}

    fun onGranted(func: () -> Unit) {
        onGranted = func
    }

    fun onDenied(func: (permissions: List<String>) -> Unit) {
        onDenied = func
    }

    fun onShowRationale(func: (permissionRequest: PermissionRequest) -> Unit) {
        onShowRationale = func
    }

    fun onNeverAskAgain(func: (permissions: List<String>) -> Unit) {
        onNeverAskAgain = func
    }

    override fun onGranted() {
        onGranted.invoke()
    }

    override fun onDenied(permissions: List<String>) {
        onDenied.invoke(permissions)
    }

    override fun onShowRationale(permissionRequest: PermissionRequest) {
        onShowRationale.invoke(permissionRequest)
    }

    override fun onNeverAskAgain(permissions: List<String>) {
        onNeverAskAgain.invoke(permissions)
    }

}