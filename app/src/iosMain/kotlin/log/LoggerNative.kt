package log

import platform.Foundation.NSLog

actual class Logger {

    actual fun log(tag: String, msg: String) {
        NSLog("[$tag] $msg")
    }

    actual fun logError(tag: String, error: Throwable, msg: String?) {
        NSLog("[$tag] ${error.message} ")

        val trace = error.getStackTrace()
        for (line in trace) {
            NSLog("[$tag] $line")
        }
    }
}