package com.holycode.github.ui.userrepos

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.holycode.github.R
import com.holycode.github.core.BaseActivity
import com.holycode.github.data.repository.RepositoryImpl
import com.holycode.github.domain.model.user.UserRepo
import com.holycode.github.domain.usecases.UserReposUseCase
import com.holycode.github.setDivider
import com.holycode.github.ui.commitsdetails.CommitsDetailsActivity
import kotlinx.android.synthetic.main.activity_userrepos.*

class UserReposActivity : BaseActivity(), UserReposContract.View {

    override lateinit var presenter: UserReposContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userrepos)
        val repo = RepositoryImpl()
        val userReposUseCase = UserReposUseCase(repo)
        presenter = UserReposPresenter(this, userReposUseCase)
        intent.extras?.let {
            presenter.getRepos(it.getString(EXTRAS_USERNAME, ""))
        }

    }

    override fun showRepos(list: MutableList<UserRepo>) {
        val adapter = UserReposAdapter(list) { repo -> openCommits(repo) }
        repos_recyclerview.layoutManager = LinearLayoutManager(this)
        repos_recyclerview.adapter = adapter
        repos_recyclerview.setDivider(R.drawable.recycler_view_divider)
    }

    private fun openCommits(userRepo: UserRepo) {
        CommitsDetailsActivity.start(this, userRepo.owner.login, userRepo.name)
    }

    companion object {
        private const val EXTRAS_USERNAME = "username"

        fun start(context: Context, username: String) {
            val intent = Intent(context, UserReposActivity::class.java)
            intent.putExtra(EXTRAS_USERNAME, username)
            context.startActivity(intent)
        }
    }

    override fun showProgressBar(show: Boolean) {
      progress_bar.visibility = if(show) View.VISIBLE else View.GONE
    }
}