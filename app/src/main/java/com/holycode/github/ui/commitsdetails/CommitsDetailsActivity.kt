package com.holycode.github.ui.commitsdetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.holycode.github.R
import com.holycode.github.core.BaseActivity
import com.holycode.github.data.repository.RepositoryImpl
import com.holycode.github.domain.model.repo.BaseCommit
import com.holycode.github.domain.usecases.CommitsUseCase
import com.holycode.github.setDivider
import kotlinx.android.synthetic.main.activity_commitdetails.*

class CommitsDetailsActivity : BaseActivity(), CommitsDetailsContract.View {

    override lateinit var presenter: CommitsDetailsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_commitdetails)
        val repo = RepositoryImpl()
        val commitsUseCase = CommitsUseCase(repo)
        presenter = CommitsDetailsPresenter(this, commitsUseCase)
        intent.extras?.let {
            presenter.getCommits(it.getString(EXTRAS_OWNER, ""), it.getString(EXTRAS_REPO_NAME, ""))
        }

    }


    override fun setupViews(commits: List<BaseCommit>) {
        val adapter = ComitsAdapter(commits)
        commits_recyclerview.layoutManager = LinearLayoutManager(this)
        commits_recyclerview.adapter = adapter
        commits_recyclerview.setDivider(R.drawable.recycler_view_divider)
    }


    override fun showProgressBar(show: Boolean) {
        progress_bar.visibility = if(show) View.VISIBLE else View.GONE
    }

    companion object {
        private const val EXTRAS_OWNER = "owner"
        private const val EXTRAS_REPO_NAME = "repo"

        fun start(context: Context, owner: String, repoName: String) {
            val intent = Intent(context, CommitsDetailsActivity::class.java)
            intent.putExtra(EXTRAS_OWNER, owner)
            intent.putExtra(EXTRAS_REPO_NAME, repoName)
            context.startActivity(intent)
        }
    }
}