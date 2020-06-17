package com.holycode.github.ui.commitsdetails

import com.holycode.github.core.BasePresenter
import com.holycode.github.core.BaseView
import com.holycode.github.domain.model.repo.BaseCommit

interface CommitsDetailsContract {

    interface Presenter : BasePresenter {
        fun getCommits(owner:String,repo:String)
    }

    interface View : BaseView {
        fun setupViews(commits:List<BaseCommit>)
    }
}