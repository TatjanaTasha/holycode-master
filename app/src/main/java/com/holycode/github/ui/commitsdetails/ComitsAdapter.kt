package com.holycode.github.ui.commitsdetails

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.holycode.github.R
import com.holycode.github.domain.model.repo.BaseCommit
import com.holycode.github.formatISO8601UTC
import com.holycode.github.inflate
import kotlinx.android.synthetic.main.commits_single_view.view.*

class ComitsAdapter(private val commits: List<BaseCommit>) :
    RecyclerView.Adapter<ComitsAdapter.CommitsViewHolder>() {

    inner class CommitsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(baseCommit: BaseCommit) {

            itemView.commitMessage_txt.text = baseCommit.commit.message

            itemView.authorName_txt.text = baseCommit.commit.author.name
            itemView.authorEmail_txt.text = baseCommit.commit.author.email
            itemView.authorDate_txt.text = baseCommit.commit.author.date.formatISO8601UTC()

            itemView.committerName_txt.text = baseCommit.commit.committer.name
            itemView.committerEmail_txt.text = baseCommit.commit.committer.email
            itemView.committerDate_txt.text = baseCommit.commit.committer.date.formatISO8601UTC()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitsViewHolder {
        return CommitsViewHolder(parent.inflate(R.layout.commits_single_view))
    }

    override fun getItemCount(): Int = commits.size

    override fun onBindViewHolder(holder: CommitsViewHolder, position: Int) {
        holder.bind(commits[position])
    }

}
