package com.knotes.app.ui.commons.base


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

interface ViewModelLifeCycleInterface {
    fun initialize()
    fun destroy()
}

abstract class BaseViewModel() : ViewModel(), ViewModelLifeCycleInterface {
    abstract val TAG: String

    var isBusy = MutableLiveData(false)

    protected fun showLoader() {
        isBusy.value = true
    }

    protected fun hideLoader() {
        isBusy.value = false
    }

}