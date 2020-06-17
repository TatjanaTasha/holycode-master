package com.holycode.github.ui.userdetails

import android.os.Bundle
import android.view.View
import com.holycode.github.core.BaseActivity
import com.holycode.github.R
import com.holycode.github.data.repository.RepositoryImpl
import com.holycode.github.domain.model.user.UserDetails
import com.holycode.github.domain.usecases.UserDetailsUseCase
import com.holycode.github.loadImage
import com.holycode.github.ui.userrepos.UserReposActivity
import kotlinx.android.synthetic.main.activity_userdetails.*

class UserDetailsActivity : BaseActivity(), UserDetailsContract.View {

    override lateinit var presenter: UserDetailsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userdetails)

        val repo = RepositoryImpl()
        val userDetailsUseCase = UserDetailsUseCase(repo)
        presenter = UserDetailsPresenter(this, userDetailsUseCase)
        presenter.getUserDetails()
    }

    override fun setupView(userDetails: UserDetails) {
        avatar_img.loadImage(userDetails.avatarUrl)

        name_txt.text = userDetails.name.toString()
        company_txt.text = userDetails.company.toString()

        repos_btn.setOnClickListener {
            UserReposActivity.start(
                this, userDetails.login
            )
        }
    }


    override fun showProgressBar(show: Boolean) {
        progress_bar.visibility = if(show) View.VISIBLE else View.GONE
    }

}
