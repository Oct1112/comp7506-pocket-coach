package com.hkucs.pocketcoach.network

import com.hkucs.pocketcoach.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET(ApiConfig.ENDPOINT_TODAYS_LESSON)
    suspend fun getTodaysLesson(): Response<TodaysLessonResponse>

    @GET(ApiConfig.ENDPOINT_FIVE_MIN_READ)
    suspend fun getFiveMinRead(): Response<FiveMinReadResponse>

    @GET(ApiConfig.ENDPOINT_DAILY_BOOKS)
    suspend fun getDailyBooks(): Response<DailyBooksResponse>

    @GET(ApiConfig.ENDPOINT_CARDS)
    suspend fun getCards(
        @Query("tag") tag: String? = null,
        @Query("podcastId") podcastId: String? = null
    ): Response<CardsResponse>

    @GET(ApiConfig.ENDPOINT_CARD_DETAIL)
    suspend fun getCardDetail(@Path("id") id: String): Response<CardDetailResponse>

    @POST(ApiConfig.ENDPOINT_SAVE_CARD)
    suspend fun saveCard(@Path("id") id: String): Response<SaveCardResponse>
}
