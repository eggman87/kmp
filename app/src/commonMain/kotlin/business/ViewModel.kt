package business

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import network.ApplicationDispatcher
import kotlin.coroutines.CoroutineContext

open class ViewModel : CoroutineScope {

    override val coroutineContext: CoroutineContext = ApplicationDispatcher

    fun <T, R> CommonData<R>.loadCommonData(valueLoader: suspend ()-> T, mapper: (T)-> R) {
        val commonData = this
        launch {
            commonData.onLoading(true)
            try {
                val loadedData = mapper(valueLoader())
                commonData.onLoading(false)
                commonData.onSuccess(loadedData)
            } catch (error: Exception) {
                commonData.onLoading(false )
                commonData.onError(error)
            }
        }
    }
}