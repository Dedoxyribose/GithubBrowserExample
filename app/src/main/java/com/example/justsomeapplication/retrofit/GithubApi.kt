package com.example.justsomeapplication.retrofit

import com.example.justsomeapplication.model.Repo
import com.example.justsomeapplication.model.RepoSearchServerResult
import com.example.justsomeapplication.model.User
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("/users/{username}")
    fun loadUser(@Path("username") name: String): Single<Response<User>>

    @GET("/users/{username}/repos")
    fun loadUserRepos(@Path("username") name: String): Single<List<Repo>>

    @GET("/search/repositories")
    fun searchUserRepos(
        @Query(
            "q",
            encoded = true
        ) conditions: String,
        @Query("page") page: Int
    ): Single<Response<RepoSearchServerResult>>
}