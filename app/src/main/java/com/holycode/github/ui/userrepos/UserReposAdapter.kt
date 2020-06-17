package com.holycode.github.ui.userrepos

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.holycode.github.R
import com.holycode.github.domain.model.user.UserRepo
import com.holycode.github.inflate
import kotlinx.android.synthetic.main.repos_single_item.view.*

class UserReposAdapter(
    private val repos: MutableList<UserRepo>,
    private val onClick: (UserRepo) -> Unit
) : RecyclerView.Adapter<UserReposAdapter.UserReposViewHolder>() {

    inner class UserReposViewHolder(itemView: View, private val onClick: (UserRepo) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(userRepo: UserRepo) {
            itemView.repos_name_rv.text = itemView.context.getString(R.string.repo, userRepo.name)
            itemView.repos_open_issues_rv.text = itemView.context.getString(
                R.string.open_issues,
                userRepo.openIssuesCount.toString()
            )
            itemView.setOnClickListener { onClick(userRepo) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserReposViewHolder {
        return UserReposViewHolder(parent.inflate(R.layout.repos_single_item), onClick)
    }

    override fun getItemCount(): Int = repos.size

    override fun onBindViewHolder(holder: UserReposAdapter.UserReposViewHolder, position: Int) {
        holder.bind(repos[position])
    }


}