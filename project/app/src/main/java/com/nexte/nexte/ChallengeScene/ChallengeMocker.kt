package com.nexte.nexte.ChallengeScene

import com.nexte.nexte.Player

/**
 * Singleton responsible for mock challenge data
 */
object ChallengeMocker {

    private const val numPlayers = 9 // Constant to define number of mocked players

    /**
     * Method responsible for generating players.
     *
     * @returns List<Player> containing the mocked data.
     */
    fun git

        var list: List<Player> = listOf()

        for(count in 0..numPlayers){
            val player = Player(String.format("nome%d",count+1), count+1,
                    "", "", "", "", 0, "")

            list += player
        }

        return list
    }

    fun createPlayerDetailedInfo(): List<ChallengeModel.PlayerRankingDatails>{

        var list: List<ChallengeModel.PlayerRankingDatails> = listOf()

        for(count in 0..numPlayers){
            val player = ChallengeModel.PlayerRankingDatails(String.format("nome%d", count+1),
                    10-count,
                    count,
                    count+1
            )

            list += player
        }

        return list
    }


}