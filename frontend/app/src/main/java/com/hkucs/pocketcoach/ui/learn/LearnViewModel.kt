package com.hkucs.pocketcoach.ui.learn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hkucs.pocketcoach.data.Repository
import com.hkucs.pocketcoach.model.Card
import kotlinx.coroutines.launch

class LearnViewModel : ViewModel() {

    private val repository = Repository()

    private val _cards = MutableLiveData<List<Card>>()
    val cards: LiveData<List<Card>> = _cards

    private val _selectedTag = MutableLiveData("All")
    val selectedTag: LiveData<String> = _selectedTag

    val tags = listOf("All", "Growth", "Strategy", "AI", "Leadership")

    init {
        loadCards()
    }

    fun selectTag(tag: String) {
        _selectedTag.value = tag
        loadCards(tag)
    }

    private fun loadCards(tag: String? = null) {
        viewModelScope.launch {
            _cards.value = repository.getCards(tag)
        }
    }

    fun loadCardDetail(cardId: String) {
        viewModelScope.launch {
            repository.getCardDetail(cardId)
        }
    }

    fun saveCard(cardId: String) {
        viewModelScope.launch {
            val saved = repository.saveCard(cardId)
            if (saved) {
                loadCards(_selectedTag.value)
            }
        }
    }
}
