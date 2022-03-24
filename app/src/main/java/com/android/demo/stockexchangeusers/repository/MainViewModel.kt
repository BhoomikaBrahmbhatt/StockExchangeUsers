package com.android.demo.stockexchangeusers.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class MainViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val movieList = MutableLiveData<List<Items>>()
    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun getAllMovies(
        page:Int,
        pagesize:Int,
         order:String,
         sort:String,
         site:String,
    filter:String) {

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = mainRepository.getAllUsers(page, pagesize, order, sort, site,filter)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    movieList.postValue(response.body()?.items)
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }

    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}