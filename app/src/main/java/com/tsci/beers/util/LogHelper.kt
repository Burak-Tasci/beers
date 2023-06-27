package com.tsci.beers.util

import android.util.Log

/**
 * Created by tasci on 25.06.2023.
 */
object LogHelper {

    fun debug(message: String, tag: String = TAG) = Log.d(tag, message)

    fun info(message: String, tag: String = TAG) = Log.i(tag, message)

    fun error(message: String, tag: String = TAG) = Log.e(tag, message)

    fun warning(message: String, tag: String = TAG) = Log.w(tag, message)

    fun logOnException(tag: String = TAG, exception: Exception) {
        val stackTrace = exception.stackTrace
        warning(stackTrace.toString())
    }

    fun Exception.log(){
        warning(stackTrace.toString())
    }

    private const val TAG: String = "LogHelper"
}