package com.example.justsomeapplication.business.user

import com.example.justsomeapplication.model.User
import com.example.justsomeapplication.model.repository.user.IUserRepository
import io.reactivex.Single
import javax.inject.Inject

class UserInteractor @Inject constructor(private val userRepository: IUserRepository) :
    IUserInteractor {
    override fun loadUser(name: String): Single<User> = userRepository.loadUser(name)
}