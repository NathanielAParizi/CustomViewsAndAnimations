package com.example.newsapp.ViewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.Datasource.Remote.API_KEY
import com.example.newsapp.Datasource.Remote.NewsApiService
import com.example.newsapp.Model.NewsApiResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewsListViewModel : ViewModel() {
    val newsList = MutableLiveData<NewsApiResponse>()
    init {
        NewsApiService
            .getNewsApiService()
            .getTopHeadlines("us", API_KEY)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map { result -> newsList.postValue(result) }
            .subscribe()
    }
}