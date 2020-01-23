package log

expect class Logger() {

    fun log(tag: String, msg: String)

    fun logError(tag: String, error: Throwable,  msg: String? = null)
}