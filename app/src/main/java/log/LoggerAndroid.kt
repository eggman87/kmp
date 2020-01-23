package log

import android.util.Log

actual class Logger {
    actual fun log(tag: String, msg: String) {
        Log.d(tag, msg)
    }

    actual fun logError(tag: String, error: Throwable, msg: String?) {
        Log.e(tag,msg ?: "",  error)
    }
}