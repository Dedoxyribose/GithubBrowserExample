package com.example.justsomeapplication.model.repository.user

import com.example.justsomeapplication.model.User
import io.reactivex.Single

interface IUserRepository {
    fun loadUser(name: String): Single<User>
}