package com.hkucs.pocketcoach.ui.library

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hkucs.pocketcoach.data.Repository
import com.hkucs.pocketcoach.model.LibraryData

class LibraryViewModel : ViewModel() {

    private val repository = Repository()

    private val _libraryData = MutableLiveData<LibraryData>()
    val libraryData: LiveData<LibraryData> = _libraryData

    init {
        _libraryData.value = repository.getLibraryData()
    }
}
