package com.example.justsomeapplication.model.repository.repo

import com.example.justsomeapplication.model.Repo
import com.example.justsomeapplication.model.RepoSearchResult
import com.example.justsomeapplication.retrofit.GithubApi
import com.example.justsomeapplication.utils.Pagination
import com.example.justsomeapplication.utils.io
import io.reactivex.Single
import javax.inject.Inject

class RepoRepository @Inject constructor(private val api: GithubApi) : IRepoRepository {

    override fun loadUserRepos(name: String): Single<List<Repo>> {
        return api.loadUserRepos(name).io()
    }

    override fun searchUserRepos(
        name: String,
        searchString: String?,
        page: Int
    ): Single<RepoSearchResult> {
        val nonEmptySearchString = searchString ?: ""
        return api.searchUserRepos("$nonEmptySearchString+user:$name", page + 1)
            .map { response ->
                val pagination = Pagination(response)
                RepoSearchResult(response.body()!!.items, page, pagination.last + 1)
            }
            .io()
    }
}