package com.holycode.github.ui.userdetails

import com.holycode.github.domain.usecases.UserDetailsUseCase
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class UserDetailsPresenter(
    private val view: UserDetailsContract.View,
    private val userDetailsUseCase: UserDetailsUseCase
) : UserDetailsContract.Presenter, CoroutineScope {

    private val username = "octocat"

    override fun getUserDetails() {
        launch {
            view.showProgressBar(true)
            val userDetails = userDetailsUseCase.getUserDetails(username)
            withContext(Dispatchers.Main) {
                userDetails.onSuccess {userDetails->
                    userDetails?.let {
                        view.showProgressBar(false)
                        view.setupView(userDetails)}
                }.onFailure {
                    view.showError(it.localizedMessage)
                    view.showProgressBar(false)
                }
            }
        }
    }

   override fun onReposClicked(activity: UserDetailsActivity){
   }

    override fun onDestory() {
        this.cancel()
    }

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.IO

}