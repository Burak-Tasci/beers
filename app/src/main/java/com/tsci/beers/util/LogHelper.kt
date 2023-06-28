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

    fun logThrowable(throwable: Throwable, tag: String = TAG) {
        val stackTrace = throwable.stackTrace
        warning(stackTrace.toString())
    }

    fun Throwable.log() {
        warning(stackTrace.toString())
    }

    private const val TAG: String = "LogHelper"
}