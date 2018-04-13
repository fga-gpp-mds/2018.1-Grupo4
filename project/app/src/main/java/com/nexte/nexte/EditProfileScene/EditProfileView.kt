package com.nexte.nexte.EditProfileScene

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import com.nexte.nexte.Player
import com.nexte.nexte.R
import kotlinx.android.synthetic.main.activity_edit_profile.*

/**
 * Interface responsible to define methods used to get user information data from presenter and display it
 */
interface ShowProfileToEditDisplayLogic {

    fun displayProfileToEdit (viewModel: EditProfileModel.FirstRequest.ViewModel)
}

/**
 * Interface responsible to define methods used to get error message from presenter and display it
 */
interface EditProfileDisplayLogic {

    fun displayEditedProfile (viewModel: EditProfileModel.SecondRequest.ViewModel)
}


class EditProfileView : AppCompatActivity(), ShowProfileToEditDisplayLogic, EditProfileDisplayLogic {

    private var firstRequestInteractor: GetProfileToEditBusinessLogic? = null
    private var secondRequestInteractor: EditProfileBusinessLogic? = null

    /**
     * Class responsible to define behavior of password validation (checking if password and confirmation match)
     */
    private class PasswordWatcher(var view: EditProfileView) : TextWatcher {

        override fun afterTextChanged(s: Editable?) {
            // Does nothing
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // Does nothing
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if(this.view.passwordConfirmationTextEdit.text.trim().toString() == view.passwordTextEdit.text.trim().toString()) {
                view.passwordValidationTextView.text = "✓"
                view.passwordValidationTextView.setTextColor(Color.GREEN)
                view.updateProfileButton.isEnabled = true
            } else {
                view.passwordValidationTextView.text = "✕"
                view.passwordValidationTextView.setTextColor(Color.RED)
                view.updateProfileButton.isEnabled = false
            }
        }
    }

    /**
     * Method called on scene creation and responsible to show initial user information for edition
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        setupEditProfileScene()

        this.passwordConfirmationTextEdit.addTextChangedListener(PasswordWatcher(this))
        this.passwordTextEdit.addTextChangedListener(PasswordWatcher(this))

        val firstRequest: EditProfileModel.FirstRequest.Request = EditProfileModel.FirstRequest.Request(
                "gabrielalbino",
                "UHDASFUHADSUHF2828HJDDJFHA")

        firstRequestInteractor?.getProfileToEdit(firstRequest)

        updateProfileButton.setOnClickListener {

            val user = Player(username.text.toString(),
                    rankingID.text.removeRange(0, 1).toString().toInt(),
                    "",
                    emailTextEdit.text.trim().toString(),
                    "",
                    clubName.text.toString(),
                    ageTextEdit.text.trim().toString().toInt(),
                    passwordTextEdit.text.trim().toString())

            val secondRequest: EditProfileModel.SecondRequest.Request = EditProfileModel.SecondRequest.Request(user)

            secondRequestInteractor?.setEditedProfile(secondRequest)
        }
    }

    /**
     * Method responsible to get user information and display it
     *
     * @param viewModel contains formatted player data
     */
    override fun displayProfileToEdit(viewModel: EditProfileModel.FirstRequest.ViewModel) {

        this.username.text = viewModel.playerToEdit.username
        this.rankingID.text = viewModel.playerToEdit.ranking
        this.emailTextEdit.setText(viewModel.playerToEdit.email, TextView.BufferType.EDITABLE)
        this.ageTextEdit.setText(viewModel.playerToEdit.age, TextView.BufferType.EDITABLE)
        this.clubName.text = viewModel.playerToEdit.club
    }

    /**
     * Method responsible to get edit profile error and display it
     *
     * @param viewModel contains formatted error message
     */
    override fun displayEditedProfile(viewModel: EditProfileModel.SecondRequest.ViewModel) {

        val errorMessage = viewModel.errorMessage

        if(errorMessage == null) {
            this.finish()
        } else {
            this.errorMessageTextView.text = errorMessage
        }
    }

    /**
     * Method responsible to set all the references in this scene
     */
    private fun setupEditProfileScene() {
        val interactor = EditProfileInteractor()
        val presenter = EditProfilePresenter()
        val view = this

        view.firstRequestInteractor = interactor
        view.secondRequestInteractor = interactor
        interactor.firstPresenter = presenter
        interactor.secondPresenter = presenter
        presenter.firstView = view
        presenter.secondView = view
    }
}
