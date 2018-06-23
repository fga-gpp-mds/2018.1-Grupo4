package com.nexte.nexte.MatchScene

import com.nexte.nexte.Entities.Challenge.Challenge
import com.nexte.nexte.Entities.Challenge.ChallengeManager
import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserCategory.UserCategory
import com.nexte.nexte.Entities.User.UserManager

/**
 * Created by leticia on 01/05/18.
 */

/**
 * Interface to define Presentation Logic to Match Class that
 * will be used to call this Interactor on other class layer
 */
interface MatchPresentationLogic {

    /**
     * Method responsible to format match data information and send to view
     *
     * @param response contains unformatted data received from [MatchModel]
     */
    fun presentMatch (response: MatchModel.InitScene.Response)

    /**
     * Method responsible for creating the appropriate message for
     * a success match result
     *
     * @param response contains the data about the status of the match result
     */
    fun presentSuccessMessageForMatchResult (response: MatchModel.SendMatchResult.Response)

    /**
     * Method responsible for creating the appropriate message for
     * a failed match result
     *
     * @param response contains the data about the status of the match result
     */
    fun presentErrorMessageForMatchResult (response: MatchModel.SendMatchResult.Response)

    /**
     * Method responsible for creating the appropriate message for
     * a decline match result
     *
     * @param response contains the data about the status of the decline match result
     */
    fun presentDeclineMatch(response: MatchModel.DeclineChallengeRequest.Response)
}

/**
 * Class needed to format response so the data can be displayed on activity at [MatchFragment]
 *
 * @property viewController Reference to the activity where data will be displayed on view
 */
class MatchPresenter(var viewController: MatchDisplayLogic? = null) : MatchPresentationLogic {

    var challengeManager: ChallengeManager? = null
    var userManager: UserManager? = null

    override fun presentMatch(response: MatchModel.InitScene.Response) {

        val viewModel = MatchModel.InitScene.ViewModel(matchFormatted(response.match))

        viewController?.displayMatch(viewModel)
    }

    /**
     * Function that formats data of players to be displayed on [MatchFragment]
     *
     * @param challenge list with all challenges
     */
    private fun formatMatch(challenge: Array<Challenge>?) : List<MatchModel.FormattedMatchData> {

        val matchModelChallengesSentMutable: MutableList<MatchModel.FormattedMatchData> = mutableListOf()

        val emptyUser = User("", "", "", "", null, -1,
                "", "", -1, -1, User.Gender.FEMALE, UserCategory("",""),
                User.Status.UNAVAILABLE,null, null, null)

        if(challenge == null) {
            return listOf()
        }
        //TODO
        for (challenge in challenge) {
            val user = userManager?.get(challenge.user!!)

        }

    }

    override fun presentSuccessMessageForMatchResult(response: MatchModel.SendMatchResult.Response) {
        val message = "Resultado do desafio enviado com sucesso!"
        val viewModel = MatchModel.SendMatchResult.ViewModel(message)
        this.viewController?.displayMatchResultMessage(viewModel)
    }

    override fun presentErrorMessageForMatchResult(response: MatchModel.SendMatchResult.Response) {
        val message = "Resultado do desafio não pode ser enviado. Por favor, tente novamente mais tarde."
        val viewModel = MatchModel.SendMatchResult.ViewModel(message)
        this.viewController?.displayMatchResultMessage(viewModel)
    }

    override fun presentDeclineMatch(response: MatchModel.DeclineChallengeRequest.Response) {
        var viewModel: MatchModel.DeclineChallengeRequest.ViewModel? = null
        var message: String? = null

        if (response.status == MatchModel.DeclineChallengeRequest.Status.SUCCESS){
            message = ""
        }else{
            message = "Não foi possível cancelar esse desafio!"
        }

        viewModel = MatchModel.DeclineChallengeRequest.
                ViewModel(response.status, message)
        viewController?.displayDeclineMatch(viewModel)
    }
}