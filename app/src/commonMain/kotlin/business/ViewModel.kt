package business

import kotlinx.coroutines.CoroutineScope
import network.ApplicationDispatcher
import kotlin.coroutines.CoroutineContext

open class ViewModel : CoroutineScope {
    override val coroutineContext: CoroutineContext = ApplicationDispatcher
}