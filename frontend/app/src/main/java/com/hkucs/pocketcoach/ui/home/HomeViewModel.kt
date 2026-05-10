package com.hkucs.pocketcoach.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hkucs.pocketcoach.data.Repository
import com.hkucs.pocketcoach.model.Book
import com.hkucs.pocketcoach.model.FiveMinRead
import com.hkucs.pocketcoach.model.TodaysLesson
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository = Repository()

    private val _todaysLesson = MutableLiveData<TodaysLesson>()
    val todaysLesson: LiveData<TodaysLesson> = _todaysLesson

    private val _fiveMinRead = MutableLiveData<FiveMinRead>()
    val fiveMinRead: LiveData<FiveMinRead> = _fiveMinRead

    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>> = _books

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _todaysLesson.value = repository.getTodaysLesson()
            _fiveMinRead.value = repository.getFiveMinRead()
            _books.value = repository.getDailyBooks()
        }
    }
}
