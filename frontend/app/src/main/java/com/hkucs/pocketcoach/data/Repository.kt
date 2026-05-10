package com.hkucs.pocketcoach.data

import com.hkucs.pocketcoach.model.*
import com.hkucs.pocketcoach.network.ApiConfig
import com.hkucs.pocketcoach.network.RetrofitClient

/**
 * Single source of truth for all data.
 * Set ApiConfig.USE_MOCK = false to switch to real network calls.
 */
class Repository {

    // ---- Home ----

    suspend fun getTodaysLesson(): TodaysLesson {
        return if (ApiConfig.USE_MOCK) {
            MockDataSource.todaysLesson
        } else {
            RetrofitClient.apiService.getTodaysLesson().body()!!.lesson
        }
    }

    suspend fun getFiveMinRead(): FiveMinRead {
        return if (ApiConfig.USE_MOCK) {
            MockDataSource.fiveMinRead
        } else {
            RetrofitClient.apiService.getFiveMinRead().body()!!.summary
        }
    }

    suspend fun getDailyBooks(): List<Book> {
        return if (ApiConfig.USE_MOCK) {
            MockDataSource.books
        } else {
            RetrofitClient.apiService.getDailyBooks().body()!!.books
        }
    }

    // ---- Learn ----

    suspend fun getCards(tag: String? = null, podcastId: String? = null): List<Card> {
        return if (ApiConfig.USE_MOCK) {
            MockDataSource.getCards(tag, podcastId)
        } else {
            RetrofitClient.apiService.getCards(tag, podcastId).body()!!.cards
        }
    }

    suspend fun getCardDetail(cardId: String): Card {
        return if (ApiConfig.USE_MOCK) {
            MockDataSource.getCardDetail(cardId)
        } else {
            RetrofitClient.apiService.getCardDetail(cardId).body()!!.card
        }
    }

    suspend fun saveCard(cardId: String): Boolean {
        return if (ApiConfig.USE_MOCK) {
            MockDataSource.saveCard(cardId)
        } else {
            RetrofitClient.apiService.saveCard(cardId).body()?.success ?: false
        }
    }

    // ---- Library ----

    fun getLibraryData(): LibraryData = MockDataSource.libraryData
}
