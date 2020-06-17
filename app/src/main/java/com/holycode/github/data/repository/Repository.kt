package com.holycode.github.data.repository

import com.holycode.github.domain.model.repo.BaseCommit
import com.holycode.github.domain.model.user.UserDetails
import com.holycode.github.domain.model.user.UserRepo

interface Repository {

    suspend fun getUserDetails(username: String): Result<UserDetails?>

    suspend fun getUserRepos(username: String): Result<List<UserRepo>?>

    suspend fun getCommitDetails(owner: String, repoName: String): Result<List<BaseCommit>?>

}