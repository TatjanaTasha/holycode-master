package com.holycode.github.domain.usecases

import com.holycode.github.data.repository.RepositoryImpl
import com.holycode.github.domain.model.repo.BaseCommit
import retrofit2.Response

class CommitsUseCase (private var repositoryImpl: RepositoryImpl){

    suspend fun getUserCommits(owner: String,repo: String): Result<List<BaseCommit>?> =
        repositoryImpl.getCommitDetails(owner,repo)
}