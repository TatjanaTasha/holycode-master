package com.holycode.github.ui.userrepos

import com.holycode.github.domain.usecases.UserReposUseCase
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class UserReposPresenter(private val view: UserReposContract.View,private val userReposUseCase: UserReposUseCase):
    UserReposContract.Presenter, CoroutineScope {

    override fun getRepos(username: String) {
        launch {
            view.showProgressBar(true)
            val userRepos = userReposUseCase.getUserRepos(username)
            withContext(Dispatchers.Main) {
                userRepos.onSuccess {userRepos->
                       userRepos.let {
                         view.showRepos(userRepos!!.toMutableList())
                           view.showProgressBar(false)}
                }.onFailure {
                    view.showProgressBar(false)
                    view.showError(it.localizedMessage)
                }
            }
        }
    }

    override fun onDestory() {
        this.cancel()
    }
        override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.IO
}