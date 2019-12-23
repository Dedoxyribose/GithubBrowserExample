package com.example.justsomeapplication.business.user

import com.example.justsomeapplication.model.Repo
import com.example.justsomeapplication.model.RepoSearchResult
import com.example.justsomeapplication.model.User
import com.example.justsomeapplication.model.repository.repo.IRepoRepository
import com.example.justsomeapplication.model.repository.user.IUserRepository
import com.example.justsomeapplication.utils.computation
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

private const val MAX_REPO_COUNT_FOR_LOCAL_SEARCH = 50

class UserInteractor @Inject constructor(
    private val userRepository: IUserRepository,
    private val repoRepository: IRepoRepository
) : IUserInteractor {

    private var user: User? = null

    private var allRepoList: List<Repo>? = null

    override fun loadUser(name: String): Single<User> {
        return userRepository.loadUser(name)
            .doOnSubscribe { allRepoList = null }
            .doOnSuccess { user = it }
    }

    override fun searchUserRepos(searchString: String?, page: Int): Single<RepoSearchResult> {
        val user = user as User
        return if (user.repositoryCount <= MAX_REPO_COUNT_FOR_LOCAL_SEARCH) {
            loadAllUserRepos(user)
                .localSearch(searchString)
                .map { RepoSearchResult(it, 1, 1) }
        } else {
            searchRemote(user, searchString, page)
        }
    }

    private fun loadAllUserRepos(user: User): Single<List<Repo>> {
        return if (allRepoList == null) {
            repoRepository.loadUserRepos(user.name)
                .doOnSuccess { allRepoList = it }
        } else Single.just(allRepoList)
    }

    private fun Single<List<Repo>>.localSearch(searchString: String?): Single<List<Repo>> {
        return if (searchString != null) {
            this.computation()
                .flattenAsObservable { list -> list }
                .filter { repo -> repo.name.prepare().contains(searchString.prepare()) }
                .toList()
        } else this
    }

    private fun searchRemote(
        user: User,
        searchString: String?,
        page: Int
    ): Single<RepoSearchResult> {
        return repoRepository.searchUserRepos(user.name, searchString, page)
    }

    private fun String.prepare(): String {
        return this.toLowerCase(Locale.getDefault())
    }

}