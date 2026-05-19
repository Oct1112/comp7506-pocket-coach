package com.hkucs.pocketcoach.model

data class PodcastMeta(
    val podcastId: String,
    val title: String,
    val guest: String,
    val sourceLabel: String
)

data class TodaysLesson(
    val podcastId: String,
    val title: String,
    val description: String,
    val deepDiveUrl: String = "",
    val ctaLabel: String = "Deep Dive"
)

data class FiveMinRead(
    val podcast: PodcastMeta,
    val title: String,
    val keyTakeaways: List<String>,
    val fullSummary: String,
    val buttonLabel: String = "Read Summary"
)

data class GlossaryItem(
    val term: String,
    val definition: String
)

data class Card(
    val id: String,
    val podcastId: String,
    val typeLabel: String = "Key Insight",
    val keyInsight: String,
    val explanation: String,
    val quote: String,
    val actionItem: String,
    val tags: List<String>,
    val glossary: List<GlossaryItem>,
    val sourceLabel: String,
    val isSaved: Boolean = false
)

data class Book(
    val id: String,
    val title: String,
    val author: String,
    val reason: String
)

// ---- API response wrappers ----

data class TodaysLessonResponse(
    val lesson: TodaysLesson
)

data class FiveMinReadResponse(
    val summary: FiveMinRead
)

data class DailyBooksResponse(
    val books: List<Book>
)

data class CardsResponse(
    val cards: List<Card>
)

data class CardDetailResponse(
    val card: Card
)

data class SaveCardResponse(
    val success: Boolean,
    val message: String
)

// ---- Library models ----

data class InProgressBook(
    val title: String,
    val author: String,
    val progressPercent: Int,
    val started: Boolean = true
)

data class LibrarySpace(
    val name: String,
    val titleCount: Int,
    val isPrivate: Boolean
)

data class LibraryData(
    val inProgressBooks: List<InProgressBook>,
    val savedCount: Int,
    val downloadsCount: Int,
    val extractsCount: Int,
    val spaces: List<LibrarySpace>
)
