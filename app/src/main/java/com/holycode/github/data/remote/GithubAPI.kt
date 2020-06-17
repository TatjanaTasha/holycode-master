package com.holycode.github.data.remote

import com.holycode.github.domain.model.repo.BaseCommit
import com.holycode.github.domain.model.user.UserDetails
import com.holycode.github.domain.model.user.UserRepo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubAPI {

    @GET("users/{username}")
     fun getUserDetails(@Path("username") username: String): Call<UserDetails?>

    @GET("users/{username}/repos")
    fun getUserRepos(@Path("username") username: String): Call<List<UserRepo>?>

    @GET("repos/{owner}/{name}/commits")
     fun getCommitDetails(
        @Path("owner") owner: String,
        @Path("name") repoName: String
    ): Call<List<BaseCommit>?>

}