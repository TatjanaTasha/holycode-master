package com.holycode.github

import com.holycode.github.data.remote.model.APIError
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

inline fun <reified T> Call<T>.enqueue(crossinline result: (Result<T?>) -> Unit) {
    enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            when (response.code()) {

                in 200..299 -> result(Result.success(response.body()))

                401 -> result(Result.failure(Error("Unauthenticated")))

                in 400..499 -> {
                    result(Result.failure(Error("Client error")))
                }

                in 500..599 -> result(Result.failure(Error("Server error")))

                else -> result(Result.failure(APIError(-1, response.message())))
            }
        }

        override fun onFailure(call: Call<T>, e: Throwable) {
            when (e) {
                is IOException -> result(Result.failure(Error("Network error")))
                else -> result(Result.failure(APIError(-1, e.localizedMessage ?: "Unknown error")))
            }
        }
    })
}