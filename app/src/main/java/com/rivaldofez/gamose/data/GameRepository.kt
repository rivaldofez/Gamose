package com.rivaldofez.gamose.data

import com.rivaldofez.gamose.model.DetailGame
import com.rivaldofez.gamose.model.Game
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


object FakeGameDataSource {
    val dummyGames = listOf<Game>(
        Game(
            id = 540,
            title = "Overwatch 2",
            thumbnail = "https://www.freetogame.com/g/540/thumbnail.jpg",
            shortDescription = "A hero-focused first-person team shooter from Blizzard Entertainment.",
            gameUrl = "https://www.freetogame.com/open/overwatch-2",
            genre = "Shooter",
            platform = "PC (Windows)",
            publisher = "Activision Blizzard",
            developer = "Blizzard Entertainment",
            releaseDate = "2022-10-04",
            freetogameProfileUrl = "https://www.freetogame.com/overwatch-2"
        ),
        Game(
            id = 540,
            title = "Overwatch 2",
            thumbnail = "https://www.freetogame.com/g/540/thumbnail.jpg",
            shortDescription = "A hero-focused first-person team shooter from Blizzard Entertainment.",
            gameUrl = "https://www.freetogame.com/open/overwatch-2",
            genre = "Shooter",
            platform = "PC (Windows)",
            publisher = "Activision Blizzard",
            developer = "Blizzard Entertainment",
            releaseDate = "2022-10-04",
            freetogameProfileUrl = "https://www.freetogame.com/overwatch-2"
        ),
        Game(
            id = 540,
            title = "Overwatch 2",
            thumbnail = "https://www.freetogame.com/g/540/thumbnail.jpg",
            shortDescription = "A hero-focused first-person team shooter from Blizzard Entertainment.",
            gameUrl = "https://www.freetogame.com/open/overwatch-2",
            genre = "Shooter",
            platform = "PC (Windows)",
            publisher = "Activision Blizzard",
            developer = "Blizzard Entertainment",
            releaseDate = "2022-10-04",
            freetogameProfileUrl = "https://www.freetogame.com/overwatch-2"
        ),
        Game(
            id = 540,
            title = "Overwatch 2",
            thumbnail = "https://www.freetogame.com/g/540/thumbnail.jpg",
            shortDescription = "A hero-focused first-person team shooter from Blizzard Entertainment.",
            gameUrl = "https://www.freetogame.com/open/overwatch-2",
            genre = "Shooter",
            platform = "PC (Windows)",
            publisher = "Activision Blizzard",
            developer = "Blizzard Entertainment",
            releaseDate = "2022-10-04",
            freetogameProfileUrl = "https://www.freetogame.com/overwatch-2"
        ),
        Game(
            id = 540,
            title = "Overwatch 2",
            thumbnail = "https://www.freetogame.com/g/540/thumbnail.jpg",
            shortDescription = "A hero-focused first-person team shooter from Blizzard Entertainment.",
            gameUrl = "https://www.freetogame.com/open/overwatch-2",
            genre = "Shooter",
            platform = "PC (Windows)",
            publisher = "Activision Blizzard",
            developer = "Blizzard Entertainment",
            releaseDate = "2022-10-04",
            freetogameProfileUrl = "https://www.freetogame.com/overwatch-2"
        ),
    )
}

class GameRepository {
    private val games = mutableListOf<Game>()

    init {
        if (games.isEmpty()) {
            FakeGameDataSource.dummyGames.forEach {
                games.add(it)
            }
        }
    }

    fun getDetailGame(gameId: Int): DetailGame {
        return DetailGame(
            title = "Gta 5",
            id = 10,
            description = "Lorem ipsum dolor sit amet",
            shortDescription = "Lorem short",
            thumbnail = "",
            freetogameProfileUrl = "",
            platform = "",
            gameUrl = "",
            releaseDate = "",
            genre = "",
            publisher = "",
            developer = "",
            status = "",
        )
    }

    fun getAllGame(): Flow<List<Game>> {
        return flowOf(games)
    }

    companion object {
        @Volatile
        private var instance: GameRepository? = null

        fun getInstance(): GameRepository =
            instance ?: synchronized(this) {
                GameRepository().apply {
                    instance = this
                }
            }
    }

}