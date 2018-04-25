package com.nexte.nexte.ChallengeScene

import com.nexte.nexte.Player

/**
 * Singleton responsible for mock challenge data
 */
object ChallengeMocker {

    private const val numPlayers = 10 // Constant to define number of mocked players

    /**
     * Method responsible for generating players.
     */
    fun createPlayers(): List<Player>{

        var list: List<Player> = listOf()

        for(count in 0..numPlayers){
            val player = Player(String.format("nome%d",count+1), count+1,
                    "", "", "", "", 0, "")

            list += player
        }

        return list
    }



}