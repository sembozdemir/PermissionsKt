package com.sembozdemir.permissionskt

import java.util.concurrent.atomic.AtomicInteger

/**
 * Used for generating request code and hold permission callbacks on a map.
 */
internal object PermissionsMap {

    private val atomicInteger = AtomicInteger(100)

    private val map = mutableMapOf<Int, PermissionCallbacks>()

    fun put(callbacks: PermissionCallbacks): Int {
        return atomicInteger.getAndIncrement().also {
            map.put(it, callbacks)
        }
    }

    fun get(requestCode: Int): PermissionCallbacks? {
        return map[requestCode].also {
            map.remove(requestCode)
        }
    }

}