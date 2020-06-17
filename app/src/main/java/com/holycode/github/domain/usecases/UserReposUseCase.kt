package com.holycode.github.domain.usecases

import com.holycode.github.data.repository.RepositoryImpl
import com.holycode.github.domain.model.user.UserRepo
import retrofit2.Response

class UserReposUseCase (private var repositoryImpl: RepositoryImpl){

    suspend fun getUserRepos(username: String): Result<List<UserRepo>?> =
        repositoryImpl.getUserRepos(username)
}