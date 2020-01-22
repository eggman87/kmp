package network

import kotlinx.coroutines.*

internal actual val ApplicationDispatcher: CoroutineDispatcher = Dispatchers.Main
