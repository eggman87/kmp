package business

abstract class CommonData<T> {

    abstract fun onSuccess(data: T)

    abstract fun onLoading(isLoading: Boolean)

    abstract fun onError(error: Throwable)
}