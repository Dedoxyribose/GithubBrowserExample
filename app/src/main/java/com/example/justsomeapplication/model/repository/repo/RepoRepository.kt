package com.example.justsomeapplication.model.repository.repo

import com.example.justsomeapplication.model.Repo
import com.example.justsomeapplication.model.RepoSearchResult
import com.example.justsomeapplication.retrofit.GithubApi
import com.example.justsomeapplication.utils.Pagination
import com.example.justsomeapplication.utils.io
import io.reactivex.Single
import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
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
        val encodedSearchString = encodeValue(searchString ?: "")
        val encodedname = encodeValue(name)
        return api.searchUserRepos("$encodedSearchString+user:$encodedname", page + 1)
            .map { response ->
                val pagination = Pagination(response)
                RepoSearchResult(response.body()!!.items, page, pagination.last + 1)
            }
            .io()
    }

    private fun encodeValue(value: String): String? {
        return try {
            URLEncoder.encode(value, StandardCharsets.UTF_8.toString())
        } catch (ex: UnsupportedEncodingException) {
            ex.printStackTrace()
            return null
        }
    }
}