package com.nexte.nexte.FeedScene

import com.nexte.nexte.R
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class FeedModelTest {

    @Before
    fun setUp() {
    }

    @Test
    fun testCreateRequestGetFeedActivities(){
        //prepare // call
        val request = FeedModel.GetFeedActivities.Request()

        //assert
        assertNotNull(request)
    }

    @Test
    fun testCreateResponseGetFeedActivities(){
        //preprare
        val challenger1 = FeedModel.FeedPlayer("Helena", R.mipmap.ic_launcher, 2)
        val challenged1 = FeedModel.FeedPlayer("Gabriel", R.mipmap.ic_launcher, 3)
        val date = Date()
        val identifier = "id"
        val activity1 = FeedModel.FeedActivity(challenge = FeedModel.FeedChallenge(challenger = challenger1, challenged = challenged1, challengeDate = date), feedDate = date, identifier = identifier, likes = mutableListOf(challenger1))

        //call
        val response = FeedModel.GetFeedActivities.Response(feedActivities = mutableListOf(activity1))
        response.feedActivities

        //assert
        assertEquals(challenged1, response.feedActivities[0].challenge.challenged)
        assertEquals(challenger1, response.feedActivities[0].challenge.challenger)
        assertEquals(date, response.feedActivities[0].feedDate)
        assertEquals(identifier, response.feedActivities[0].identifier)
        assertEquals(challenger1, response.feedActivities[0].likes[0])
        assertEquals(1, response.feedActivities.size)
        assertEquals(response.feedActivities.size, 1)
    }

    @Test
    fun testCreateViewModelGetFeedActivities(){
        //prepare
        val identifier = "id"
        val challengerName = "Luis"
        val challengerPhoto = 1
        val challengerSets = "1/3"
        val challengedName = "Miguel"
        val challengedPhoto =  2
        val challengedSets = "2/3"
        val feedDate = "10/02/2018"
        val numberOfLikes = "1"
        val feedActivityFormated = FeedModel.FeedActivityFormatted(challengerName = challengerName, challengerPhoto = challengerPhoto, challengerSets = challengerSets, challengedName = challengedName, challengedPhoto = challengedPhoto, challengedSets = challengedSets, feedDate = feedDate, identifier = identifier, numberOfLikes = numberOfLikes)

        //call
        val viewModel = FeedModel.GetFeedActivities.ViewModel(feedActivities = mutableListOf(feedActivityFormated))
        viewModel.feedActivities

        //assert
        assertEquals(challengerName, viewModel.feedActivities[0].challengerName)
        assertEquals(challengerPhoto, viewModel.feedActivities[0].challengerPhoto)
        assertEquals(challengerSets, viewModel.feedActivities[0].challengerSets)
        assertEquals(challengedName, viewModel.feedActivities[0].challengedName)
        assertEquals(challengedPhoto, viewModel.feedActivities[0].challengedPhoto)
        assertEquals(challengedSets, viewModel.feedActivities[0].challengedSets)
        assertEquals(feedDate, viewModel.feedActivities[0].feedDate)
        assertEquals(identifier, viewModel.feedActivities[0].identifier)
        assertEquals(numberOfLikes, viewModel.feedActivities[0].numberOfLikes)
        assertEquals(1, viewModel.feedActivities.size)
    }

    @Test
    fun testCreateFeedPlayer(){
        //prepare
        val name = "Luis"
        val photo = 1
        val set =  2

        //call
        val feedPlayer = FeedModel.FeedPlayer(name = name, photo = photo, set = set)
        feedPlayer.name
        feedPlayer.set
        feedPlayer.photo

        //assert
        assertEquals(name, feedPlayer.name)
        assertEquals(photo, feedPlayer.photo)
        assertEquals(set, feedPlayer.set)
    }

    @Test
    fun testCreateFeedChallenge(){
       //prepare
        val challenger = FeedModel.FeedPlayer(name = "Luis", photo = 1, set = 2)
        val challenged =  FeedModel.FeedPlayer(name = "Miguel", photo = 2, set = 1)
        val challengeDate = Date()

        //call
        val feedChallenge = FeedModel.FeedChallenge(challenger = challenger, challenged = challenged, challengeDate = challengeDate)
        feedChallenge.challengeDate
        feedChallenge.challenged
        feedChallenge.challenger

        //assert
        assertEquals(challenger, feedChallenge.challenger)
        assertEquals(challenged, feedChallenge.challenged)
        assertEquals(challengeDate, feedChallenge.challengeDate)
    }

    @Test
    fun createFeedActivity(){
        //prepare
        val challenger = FeedModel.FeedPlayer(name = "Luis", photo = 1, set = 2)
        val challenged =  FeedModel.FeedPlayer(name = "Miguel", photo = 2, set = 1)
        val challengeDate = Date()
        val challenge =  FeedModel.FeedChallenge(challenger = challenger, challenged = challenged, challengeDate = challengeDate)
        val feedDate = Date()
        val identifier = "id"
        val likes = mutableListOf(challenger)

        //call
        val feedActivity = FeedModel.FeedActivity(challenge = challenge, feedDate = feedDate, identifier = identifier, likes = likes)
        feedActivity.challenge
        feedActivity.feedDate
        feedActivity.identifier
        feedActivity.likes

        //assert
        assertEquals(challenge, feedActivity.challenge)
        assertEquals(feedDate, feedActivity.feedDate)
        assertEquals(identifier, feedActivity.identifier)
        assertEquals(likes, feedActivity.likes)
    }

    @Test
    fun feedActivityFormatted(){
        //prepare
        val identifier = "id"
        val challengerName =  "Luis"
        val challengerPhoto = 1
        val challengerSets = "1"
        val challengedName = "Miguel"
        val challengedPhoto =  2
        val challengedSets = "3"
        val feedDate =  "10/02/2018"
        val numberOfLikes = "1"

        //call
        val feedActivityFormatted = FeedModel.FeedActivityFormatted(challengerName = challengerName, challengerPhoto = challengerPhoto, challengerSets = challengerSets, challengedName = challengedName, challengedPhoto = challengedPhoto, challengedSets =  challengedSets, feedDate = feedDate, identifier = identifier, numberOfLikes = numberOfLikes)
        feedActivityFormatted.challengedName
        feedActivityFormatted.challengedPhoto
        feedActivityFormatted.challengedSets
        feedActivityFormatted.challengerName
        feedActivityFormatted.challengerPhoto
        feedActivityFormatted.challengerSets
        feedActivityFormatted.feedDate
        feedActivityFormatted.identifier
        feedActivityFormatted.numberOfLikes

        //assert
        assertEquals(challengerName, feedActivityFormatted.challengerName)
        assertEquals(challengerPhoto, feedActivityFormatted.challengerPhoto)
        assertEquals(challengerSets, feedActivityFormatted.challengerSets)
        assertEquals(challengedName, feedActivityFormatted.challengedName)
        assertEquals(challengedPhoto, feedActivityFormatted.challengedPhoto)
        assertEquals(challengedSets, feedActivityFormatted.challengedSets)
        assertEquals(feedDate, feedActivityFormatted.feedDate)
        assertEquals(identifier, feedActivityFormatted.identifier)
        assertEquals(numberOfLikes, feedActivityFormatted.numberOfLikes)
    }

    @Test
    fun successCreateRequestLikeAndUnlike(){
        //prepare
        val identifier = "id"

        // call
        val request = FeedModel.LikeAndUnlike.Request(identifier = identifier)
        request.identifier

        //assert
        assertEquals(identifier, request.identifier)
        assertNotNull(request)
    }

    @Test
    fun successCreateResponseLikeAndUnlike(){
        //prepare
        val challenger1 = FeedModel.FeedPlayer("Helena", R.mipmap.ic_launcher, 2)
        val challenged1 = FeedModel.FeedPlayer("Gabriel", R.mipmap.ic_launcher, 3)
        val date = Date()
        val identifier = "id"
        val challenge = FeedModel.FeedChallenge(challenger = challenger1, challenged = challenged1, challengeDate = date)
        val likes = mutableListOf(challenger1)
        val activity1 = FeedModel.FeedActivity(challenge = challenge, feedDate = date, identifier = identifier, likes = likes)

        //call
        val response = FeedModel.LikeAndUnlike.Response(likedActivity = activity1)
        response.likedActivity

        //assert
        assertEquals(activity1, response.likedActivity)
        assertEquals(challenge, response.likedActivity.challenge)
        assertEquals(identifier, response.likedActivity.identifier)
        assertEquals(date, response.likedActivity.feedDate)
        assertEquals(likes, response.likedActivity.likes)

    }

    @Test
    fun successCreateViewModelLikeAndUnlike(){

    }

    @Test
    fun successFeedModel() {
        //prepare

        //call
        val model = FeedModel()

        //assert
        assertNotNull(model)
    }

    @After
    fun tearDown() {
    }
}
