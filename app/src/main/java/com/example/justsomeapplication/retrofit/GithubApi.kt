package com.example.justsomeapplication.retrofit

import com.example.justsomeapplication.model.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("/users/{username}")
    fun loadUser(@Path("username") name: String): Single<User>
}