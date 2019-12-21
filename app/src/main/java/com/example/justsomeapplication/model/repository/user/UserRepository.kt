package com.example.justsomeapplication.model.repository.user

import com.example.justsomeapplication.model.User
import com.example.justsomeapplication.retrofit.GithubApi
import com.example.justsomeapplication.utils.io
import io.reactivex.Single
import javax.inject.Inject

class UserRepository @Inject constructor(private val api: GithubApi) : IUserRepository {

    override fun loadUser(name: String): Single<User> =
        api.loadUser(name).io()

}