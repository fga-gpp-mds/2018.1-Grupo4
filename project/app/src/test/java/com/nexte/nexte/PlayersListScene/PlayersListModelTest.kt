package com.nexte.nexte.PlayersListScene

import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.MatchScene.MatchModel
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.*

@Suppress("DEPRECATION")
class PlayersListModelTest {

    @Before
    fun setUp(){

    }

    @Test
    fun successConstructors(){
        //prepare
        //call
        val testModel = PlayersListModel()
        val testShowRanking = PlayersListModel.ShowRankingPlayersRequest()
        val testSelectPlayer = PlayersListModel.SelectPlayerForChallengeRequest()
        val testChallengeButton = PlayersListModel.ChallengeButtonRequest()

        //assert
        assertNotNull(testModel)
        assertNotNull(testShowRanking)
        assertNotNull(testSelectPlayer)
        assertNotNull(testChallengeButton)
    }

    @Test
    fun successShowRankingRequest(){
        //prepare
        val testInt = 2


        //call
        val testShowRankingRequestRequest = PlayersListModel.ShowRankingPlayersRequest.Request(testInt)
        testShowRankingRequestRequest.challengerRankingPosition = testInt

        //assert
        assertEquals(testInt, testShowRankingRequestRequest.challengerRankingPosition)
    }

    @Test
    fun successShowRankingResponse(){
        //prepare
        val testUser = User("1",
                "André Rede",
                null,
                "André",
                Date(1987, 5, 15),
                3,
                "andre@nexte.com",
                "130",
                162,
                69,
                User.Gender.MALE,
                null,
                User.Status.AVAILABLE,
                null,
                null,
                emptyList())
        val testUsersList = listOf(testUser)


        //call

        val testShowRankingRequestResponse = PlayersListModel.ShowRankingPlayersRequest.Response(testUsersList)
        testShowRankingRequestResponse.usersAbove = testUsersList


        //assert
        assertEquals(testUsersList, testShowRankingRequestResponse.usersAbove)
    }

    @Test
    fun successShowRankingViewModel(){
        //prepare
        val testFormatPlayer = PlayersListModel.FormattedPlayer("2", "Gabriel Albino",
                                                             "2",
                                                             "identifier",
                                                                    User.Status.AVAILABLE)
        val testPlayersFormattedList = listOf(testFormatPlayer)

        //call
        val testShowRankingRequestViewModel = PlayersListModel.ShowRankingPlayersRequest.ViewModel(
                testPlayersFormattedList)
        testShowRankingRequestViewModel.formattedPlayer = testPlayersFormattedList

        //assert
        assertEquals(testFormatPlayer, testShowRankingRequestViewModel.formattedPlayer[0])
    }

    ///////////////////////////////////////////////

    @Test
    fun formatteUserGet() {

        //prepare and call
        val user: PlayersListModel.FormattedPlayer?
        user = PlayersListModel.FormattedPlayer("1",
                "Miguel Pimentel",
                "1",
                "1", User.Status.AVAILABLE)
        user.identifier = "23232"
        assertNotNull(user)
    }




    @Test
    fun successSelectPlayerRequest(){
        //prepare
        val testInt = 2


        //call
        val testSelectPlayerRequest = PlayersListModel.SelectPlayerForChallengeRequest.Request(testInt)
        testSelectPlayerRequest.challengedRankingPosition= testInt

        //assert
        assertEquals(testInt, testSelectPlayerRequest.challengedRankingPosition)
    }

    @Test
    fun successSelectPlayerResponse(){
        //prepare
        val testUser = User("1",
                "André Rede",
                null,
                "André",
                Date(1987, 5, 15),
                3,
                "andre@nexte.com",
                "130",
                162,
                69,
                User.Gender.MALE,
                null,
                User.Status.AVAILABLE,
                null,
                null,
                emptyList())


        //call

        val testSelectPlayerRequest = PlayersListModel.SelectPlayerForChallengeRequest.Response(testUser)
        testSelectPlayerRequest.challengedPersonalDetails = testUser


        //assert
        assertEquals(testUser, testSelectPlayerRequest.challengedPersonalDetails)
    }

    @Test
    fun successSelectPlayerViewModel(){
        //prepare
        val numbers: MutableList<Int> = mutableListOf(1, 2, 3)
        var list:List<Int> = numbers

        val testRankingDetails = PlayersListModel.FormattedRankingDetails("Daniel Maike",
                                                                          "4",
                                                                          "6",
                                                                          "1",
                                                                          "10%",
                                                                          "10/03/2018",list,"2")


        //call
        val testSelectPlayerForChallenge = PlayersListModel.SelectPlayerForChallengeRequest.ViewModel(testRankingDetails)
        testSelectPlayerForChallenge.challengedRankingDetails = testRankingDetails

        //assert
        assertEquals(testRankingDetails, testSelectPlayerForChallenge.challengedRankingDetails)
    }

    ///////////////////////////////////////////////

    @Test
    fun successChallengeButtonRequest(){
        //prepare
        val testChallenged = "Lorrany Freire"


        //call
        val testChallengeButtonRequest = PlayersListModel.ChallengeButtonRequest.Request(testChallenged)
        testChallengeButtonRequest.userChallenged = testChallenged

        //assert
        assertEquals(testChallenged, testChallengeButtonRequest.userChallenged)
    }

    @Test
    fun successChallengeButtonResponse(){
        //prepare
        val testUsername = "Letícia Meneses"
        val challenged = MatchModel.MatchPlayer("Letícia Meneses", 1)
        val challenger = MatchModel.MatchPlayer("Helena Goulart", 2)
        val testChallenge = MatchModel.MatchData(challenged, challenger, "1")


        //call

        val testChallengeButtonResponse = PlayersListModel.ChallengeButtonRequest.Response(testUsername,
                                                                                           testChallenge)
        testChallengeButtonResponse.username = testUsername
        testChallengeButtonResponse.challenge = testChallenge

        //assert
        assertEquals(testUsername,testChallengeButtonResponse.username)
        assertEquals(testChallenge.challenged, testChallengeButtonResponse.challenge.challenged)
        assertEquals(testChallenge.challenger, testChallengeButtonResponse.challenge.challenger)
    }

    @Test
    fun successChallengeButtonViewModel(){
        //prepare
        val testMessageForChallenge = "you did it"
        val testMatchMessage = "the match did it"
        val challenged = MatchModel.MatchPlayer("Letícia Meneses", 1)
        val challenger = MatchModel.MatchPlayer("Helena Goulart", 2)
        val testMatchData: MatchModel.MatchData?
        testMatchData = MatchModel.MatchData(challenged, challenger, "1")

        //call
        val testChallengeButtonViewModel = PlayersListModel.ChallengeButtonRequest.ViewModel(testMessageForChallenge,
                                                                                             testMatchMessage,
                                                                                             testMatchData)
        testChallengeButtonViewModel.matchMessage = testMatchMessage
        testChallengeButtonViewModel.messageForChallenger = testMessageForChallenge
        testChallengeButtonViewModel.matchData?.challenger = challenger
        testChallengeButtonViewModel.matchData?.challenged = challenged
        testChallengeButtonViewModel.matchData = testMatchData


        //assert
        assertEquals(testMessageForChallenge, testChallengeButtonViewModel.messageForChallenger)
        assertEquals(testMatchMessage, testChallengeButtonViewModel.matchMessage)
        assertEquals(testMatchData, testChallengeButtonViewModel.matchData)
    }

    ////////////////////////////////////////

    @Test
    fun successFormattedPlayer(){
        //prepare
        val testIdentifier = "2"
        val testName = "Mendelson"
        val testPosition = "2"
        val testPicture = "validToken"

        //call
        val testFormattedPlayer = PlayersListModel.FormattedPlayer(testIdentifier, testName, testPosition, testPicture, User.Status.AVAILABLE)
        testFormattedPlayer.name = testName
        testFormattedPlayer.rankingPosition = testPosition
        testFormattedPlayer.pictureAddress = testPicture

        //assert
        assertEquals(testName, testFormattedPlayer.name)
        assertEquals(testPosition, testFormattedPlayer.rankingPosition)
        assertEquals(testPicture, testFormattedPlayer.pictureAddress)
    }

    @Test
    fun successFormattedRankingDetails(){
        //prepare
        val testName = "Mendelson"
        val testPosition = "2"
        val testLosses = "0"
        val testWins = "3"
        val numbers: MutableList<Int> = mutableListOf(1, 2, 3)
        var list:List<Int> = numbers
        val rankingPosition = "5"
        val userID = "10"


        //call
        val testFormattedRankingDetails = PlayersListModel.FormattedRankingDetails(testName,
                testWins,rankingPosition,"Amador","50%","10/06/2018",list,userID)
        testFormattedRankingDetails.name = testName
        testFormattedRankingDetails.rankingPosition = testPosition
        testFormattedRankingDetails.wins = testWins

        //assert
        assertEquals(testName, testFormattedRankingDetails.name)
        assertEquals(testPosition, testFormattedRankingDetails.rankingPosition)
        assertEquals(testWins, testFormattedRankingDetails.wins)
    }

    @Test
    fun successPlayerRankingDetails(){
        //prepare
        val testName = "Mendelson"
        val testPosition = 2
        val testLosses = 0
        val testWins = 3

        //call
        val testPlayerRankingDetails = PlayersListModel.PlayerRankingDetails(testName,
                                                                             testWins,
                                                                             testLosses,
                                                                             testLosses)

        testPlayerRankingDetails.name = testName
        testPlayerRankingDetails.wins = testWins
        testPlayerRankingDetails.loses = testLosses
        testPlayerRankingDetails.rankingPosition = testPosition

        //assert
        assertEquals(testName, testPlayerRankingDetails.name)
        assertEquals(testPosition, testPlayerRankingDetails.rankingPosition)
        assertEquals(testLosses, testPlayerRankingDetails.loses)
        assertEquals(testWins, testPlayerRankingDetails.wins)
    }



    @After
    fun tearDown(){

    }


}
