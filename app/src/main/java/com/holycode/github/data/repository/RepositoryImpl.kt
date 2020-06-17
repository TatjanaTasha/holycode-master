package com.holycode.github.data.repository

import com.holycode.github.data.remote.Retrofit
import com.holycode.github.domain.model.repo.BaseCommit
import com.holycode.github.domain.model.user.UserDetails
import com.holycode.github.domain.model.user.UserRepo
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import com.holycode.github.enqueue

class RepositoryImpl : Repository {

    private val retrofit = Retrofit.github

    override suspend fun getUserDetails(username: String): Result<UserDetails?> {
        return suspendCancellableCoroutine { continuation ->
            retrofit.getUserDetails(username).enqueue { result ->
                continuation.resume(result)
            }
        }
    }


    override suspend fun getUserRepos(username: String): Result<List<UserRepo>?> {
        return suspendCancellableCoroutine { continuation ->
            retrofit.getUserRepos(username).enqueue { result ->
                continuation.resume(result)
            }
        }
    }

    override suspend fun getCommitDetails(
        owner: String,
        repoName: String
    ): Result<List<BaseCommit>?> {
        return suspendCancellableCoroutine { continuation ->
            retrofit.getCommitDetails(owner, repoName).enqueue { result ->
                continuation.resume(result)
            }
        }
    }

}
