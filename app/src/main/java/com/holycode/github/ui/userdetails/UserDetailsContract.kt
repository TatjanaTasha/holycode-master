package com.holycode.github.ui.userdetails

import com.holycode.github.core.BasePresenter
import com.holycode.github.core.BaseView
import com.holycode.github.domain.model.user.UserDetails
import com.holycode.github.domain.usecases.UserDetailsUseCase

interface UserDetailsContract {

    interface Presenter : BasePresenter {
        fun getUserDetails()
        fun onReposClicked(activity: UserDetailsActivity)
    }

    interface View : BaseView {
        fun setupView(userDetails: UserDetails)
    }
}