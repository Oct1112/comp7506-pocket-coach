package com.hkucs.pocketcoach.network

/**
 * All backend API endpoints are defined here.
 * Flip USE_MOCK to false when the Node.js backend is ready.
 */
object ApiConfig {

    // ---- Toggle: true = use local mock data, false = call real API ----
    const val USE_MOCK = true

    // ---- Base URLs (used when USE_MOCK = false) ----
    const val BASE_URL_ANDROID_EMULATOR = "http://10.0.2.2:3000/"
    const val BASE_URL_MUMU = "http://10.0.2.2:3000/"
    const val BASE_URL = BASE_URL_MUMU

    // ---- Home endpoints ----
    const val ENDPOINT_TODAYS_LESSON = "api/home/todays-lesson"
    const val ENDPOINT_FIVE_MIN_READ = "api/home/5min-read"
    const val ENDPOINT_DAILY_BOOKS = "api/home/daily-books"

    // ---- Learn endpoints ----
    const val ENDPOINT_CARDS = "api/cards" // GET ?tag=xxx&podcastId=xxx
    const val ENDPOINT_CARD_DETAIL = "api/cards/{id}" // GET
    const val ENDPOINT_SAVE_CARD = "api/cards/{id}/save" // POST
}
