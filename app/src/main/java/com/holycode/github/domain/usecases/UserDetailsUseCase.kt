package com.holycode.github.domain.usecases

import com.holycode.github.data.repository.RepositoryImpl
import com.holycode.github.domain.model.user.UserDetails
import retrofit2.Response

class UserDetailsUseCase(private var repositoryImpl: RepositoryImpl){

    suspend fun getUserDetails(username: String): Result<UserDetails?> =
        repositoryImpl.getUserDetails(username)
}