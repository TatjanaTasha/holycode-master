package com.holycode.github.ui.userrepos

import com.holycode.github.core.BasePresenter
import com.holycode.github.core.BaseView
import com.holycode.github.domain.model.user.UserRepo

interface UserReposContract {

    interface Presenter : BasePresenter {
        fun getRepos(username: String)

    }

    interface View : BaseView {
        fun showRepos(list: MutableList<UserRepo>)
    }
}