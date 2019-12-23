package com.example.justsomeapplication.business.user

import com.example.justsomeapplication.model.RepoSearchResult
import com.example.justsomeapplication.model.User
import io.reactivex.Single

interface IUserInteractor {
    fun loadUser(name: String): Single<User>

    fun searchUserRepos(searchString: String?, page: Int): Single<RepoSearchResult>
}