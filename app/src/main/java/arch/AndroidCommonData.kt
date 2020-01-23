package arch


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import business.CommonData

class AndroidCommonData<T> : CommonData<T>() {

    val data: MutableLiveData<Resource<T>> = MutableLiveData()

    override fun onSuccess(data: T) {
        this.data.value = Resource.Success(data)
    }

    override fun onLoading(isLoading: Boolean) {
        this.data.value = Resource.Loading()
    }

    override fun onError(error: Throwable) {
        this.data.value = Resource.Error(error)
    }
}


//need a better way than this
fun<T> CommonData<T>.android(): AndroidCommonData<T> {
    return this as AndroidCommonData<T>
}

/**
 * Represents a UI that responds to loading of a resource. These use cases (success, error, and loading)
 * apply to 90% of most use cases. For other needs, observing LiveData can be done directly in
 * the UI component.
 */
interface ResourceView<T> {
    fun showData(data: T)
    fun showLoading(isLoading: Boolean)
    fun showError(error: Throwable)
}

class ResourceViewObserver<T>(private val view: ResourceView<T>) : Observer<Resource<T>> {
    override fun onChanged(resource: Resource<T>?) {
        handleResourceChange(view, resource)
    }
}

/**
 * Applies a [Resource] to a [ResourceView].
 *
 * @param view the resource view to set a [Resource] to.
 * @param resource the resource to assign to the [ResourceView].
 */
private fun <T>handleResourceChange(view: ResourceView<T>, resource: Resource<T>?) {
    when (resource) {
        is Resource.Success -> {
            view.showLoading(false)
            view.showData(resource.data)
        }
        is Resource.Error -> {
            view.showLoading(false)
            view.showError(resource.error)
        }
        is Resource.Loading -> {
            view.showLoading(true)
        }
    }
}

sealed class Resource<T> {

    /**
     * Indicates the resource was successfully loaded.
     *
     * @param data the loaded resource value.
     */
    data class Success<T>(val data: T) : Resource<T>()

    /**
     * Indicates the resource is currently being loaded.
     */
    class Loading<T> : Resource<T>()

    /**
     * Indicates there was a error when trying to load the given resource.
     *
     * @param error the error that occurred while loading the resource.
     */
    data class Error<T>(val error: Throwable) : Resource<T>()
}