package com.nexte.nexte.LoginScene


/**
 * Interface that defines Business Login to Interactor and defines comunication
 * with worker and presenter
 */
interface LoginBusinessLogic {
    fun doAuthentication(request: LoginModel.Request)
}

/**
 * Class that defines a response from a request provided using comunication
 * between worker and presenter
 *
 * @property worker Request model of feed that contains data to pass for Worker
 * @property presenter
 */
class LoginInteractor : LoginBusinessLogic {

    var worker = LoginWorker()
    var presenter: LoginPresentationLogic? =  null

    /**
     * Method that will send request provided by worker to presenter as response
     *
     * @param request Request model of feed that contains data to pass for Worker
     */
    override fun doAuthentication(request: LoginModel.Request) {

        worker.authenticateUser(request) { response ->
            this.presenter?.presentLogin(response)
        }
    }
}