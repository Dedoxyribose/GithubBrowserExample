package com.example.justsomeapplication.model.repository.repo

import com.example.justsomeapplication.model.Repo
import com.example.justsomeapplication.model.RepoSearchResult
import io.reactivex.Single

//какая ирония
interface IRepoRepository {
    fun loadUserRepos(name: String): Single<List<Repo>>

    fun searchUserRepos(name: String, searchString: String?, page: Int): Single<RepoSearchResult>
}