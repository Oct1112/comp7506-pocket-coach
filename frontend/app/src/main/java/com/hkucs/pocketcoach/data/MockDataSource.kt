package com.hkucs.pocketcoach.data

import com.hkucs.pocketcoach.model.*

object MockDataSource {

    private val benPodcast = PodcastMeta(
        podcastId = "pod_ben_horowitz_hard_things",
        title = "Mastering the Hard Things: Ben Horowitz on Leadership, AI, and the Abyss",
        guest = "Ben Horowitz",
        sourceLabel = "Lenny's Podcast · Ben Horowitz"
    )

    val todaysLesson = TodaysLesson(
        podcastId = benPodcast.podcastId,
        title = benPodcast.title,
        description = "Discover why real leadership is about making the decisions nobody likes and how to build the psychological muscle to survive the 'CEO abyss.'",
        ctaLabel = "Deep Dive"
    )

    val fiveMinRead = FiveMinRead(
        podcast = benPodcast,
        title = benPodcast.title,
        keyTakeaways = listOf(
            "Real leadership only adds value when you make decisions that most people don't like.",
            "Success isn't one big win, but a chain of small, hard, correct choices.",
            "Great CEOs don't tutor executives; they find world-class talent to lead them."
        ),
        buttonLabel = "Read Summary"
    )

    private val mutableCards = mutableListOf(
        Card(
            id = "card_001",
            podcastId = benPodcast.podcastId,
            keyInsight = "Real leadership only adds value through decisions that most people don't like.",
            explanation = "Think of a leader like a navigator on a ship in a storm. If the path is clear and the sun is out, everyone knows where to go, and the navigator is just a passenger. You only \"earn your keep\" when the map is blurry and the crew is scared. If everyone already agreed with the path, they wouldn't need a leader to get them there.",
            quote = "If everybody agrees with the decision, then you didn't add any value because they would've done that without you.",
            actionItem = "Identify one decision you've been putting off because it might be unpopular, and schedule a 15-minute meeting today to address it.",
            tags = listOf("Leadership", "Strategy"),
            glossary = emptyList(),
            sourceLabel = benPodcast.sourceLabel
        ),
        Card(
            id = "card_002",
            podcastId = benPodcast.podcastId,
            keyInsight = "Success is a chain of small, hard decisions that prevent a total crash.",
            explanation = "Ben uses a pilot analogy: most plane crashes aren't caused by one huge mistake, but 17 tiny bad decisions in a row. Success works the same way in reverse. It's like building a Lego tower - one solid brick doesn't make the tower, but one wobbly brick at the bottom can bring the whole thing down later. You have to take the \"sunk cost\" and move to the next right move, even if it's small.",
            quote = "Success is a small thing, a small thing that's hard to do that doesn't seem to have a high impact, but it leads to the next small hard to do thing.",
            actionItem = "Break your biggest current project into 10 tiny tasks and complete the single most difficult one first.",
            tags = listOf("Strategy", "Growth"),
            glossary = listOf(
                GlossaryItem(
                    "Sunk Cost",
                    "Money or effort already spent that you can't get back, which shouldn't affect your future decisions."
                )
            ),
            sourceLabel = benPodcast.sourceLabel
        ),
        Card(
            id = "card_003",
            podcastId = benPodcast.podcastId,
            keyInsight = "A CEO's job is to find people who make them great, not to fix others.",
            explanation = "As a leader, you aren't a tutor; you're a conductor. If you're a CEO who doesn't know marketing, you can't \"teach\" someone to be a world-class marketer. It's like a basketball coach: you don't spend all your time teaching a center how to be tall; you find the best center and give them the ball so the whole team wins.",
            quote = "You don't make people great. You find people that make you great, that make the company great.",
            actionItem = "Review your team list and ask: 'Is this person telling me what to do, or am I constantly telling them what to do?'",
            tags = listOf("Growth", "Leadership"),
            glossary = listOf(
                GlossaryItem(
                    "Managerial Leverage",
                    "The idea that a manager is most effective when their team members drive the work forward independently."
                )
            ),
            sourceLabel = benPodcast.sourceLabel
        ),
        Card(
            id = "card_004",
            podcastId = benPodcast.podcastId,
            keyInsight = "AI isn't a bubble because it has real business math behind it.",
            explanation = "In the 90s dot-com bubble, companies had no way to make money. Today, AI companies are going from zero to \$800 million in revenue in a single year. It's like comparing a toy car that looks cool but doesn't move to a real car that's already winning races - one is a fad, the other is an engine of growth.",
            quote = "These businesses are all working, and they're being priced appropriately for how they're growing.",
            actionItem = "Research one AI application in your field that moves beyond a 'thin wrapper' and solves a complex, human problem.",
            tags = listOf("AI", "Strategy"),
            glossary = listOf(
                GlossaryItem(
                    "Unit Economics",
                    "The direct revenues and costs associated with a single customer or 'unit' of business."
                ),
                GlossaryItem(
                    "Thin Wrapper",
                    "A simple software layer built on top of someone else's AI model without adding much unique value."
                )
            ),
            sourceLabel = benPodcast.sourceLabel
        )
    )

    fun getCards(tag: String? = null, podcastId: String? = null): List<Card> {
        return mutableCards.filter { card ->
            val tagMatch = tag.isNullOrBlank() || tag == "All" || card.tags.contains(tag)
            val podcastMatch = podcastId.isNullOrBlank() || card.podcastId == podcastId
            tagMatch && podcastMatch
        }
    }

    fun getCardDetail(cardId: String): Card = mutableCards.first { it.id == cardId }

    fun saveCard(cardId: String): Boolean {
        val index = mutableCards.indexOfFirst { it.id == cardId }
        if (index == -1) return false
        val current = mutableCards[index]
        mutableCards[index] = current.copy(isSaved = true)
        return true
    }

    val books = listOf(
        Book(
            id = "book_001",
            title = "The Weirdest People in the World",
            author = "Joseph Henrich",
            reason = "It explains how unique cultural rules allowed for the creation of science and large-scale companies."
        ),
        Book(
            id = "book_002",
            title = "Writing My Wrongs",
            author = "Shaka Senghor",
            reason = "A powerful story of personal transformation and building trust from zero, even in harsh conditions."
        ),
        Book(
            id = "book_003",
            title = "How to Be Free",
            author = "Shaka Senghor",
            reason = "Offers specific techniques for dealing with extreme psychological pressure, which is vital for entrepreneurs."
        )
    )

    val libraryData = LibraryData(
        inProgressBooks = listOf(
            InProgressBook("Thinking In Bets", "Annie Duke", 45, started = true),
            InProgressBook("Learn Like a Lobster", "", 0, started = false)
        ),
        savedCount = 58,
        downloadsCount = 0,
        extractsCount = 0,
        spaces = listOf(
            LibrarySpace("Baca", 1, isPrivate = true)
        )
    )
}
