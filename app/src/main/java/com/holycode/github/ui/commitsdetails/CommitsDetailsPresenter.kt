package com.holycode.github.ui.commitsdetails

import com.holycode.github.domain.usecases.CommitsUseCase
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CommitsDetailsPresenter(
    private val view: CommitsDetailsContract.View,
    private val useCase: CommitsUseCase
) : CommitsDetailsContract.Presenter, CoroutineScope {


    override fun getCommits(owner: String, repo: String) {
        launch {
            view.showProgressBar(true)
            val commits = useCase.getUserCommits(owner, repo)
            withContext(Dispatchers.Main) {
                commits.onSuccess {commits->
                    commits?.let {
                        view.showProgressBar(false)
                        view.setupViews(commits)}
                }.onFailure {
                    view.showProgressBar(false)
                    view.showError(it.localizedMessage)
                }
            }
        }

    }

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.IO


}