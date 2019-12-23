package com.example.justsomeapplication.ui.user.repolist

import com.example.justsomeapplication.business.user.IUserInteractor
import com.example.justsomeapplication.di.user.repo.RepoListFragmentScope
import com.example.justsomeapplication.model.Repo
import com.example.justsomeapplication.ui.base.BasePresenter
import com.example.justsomeapplication.utils.ErrorHelper
import com.example.justsomeapplication.utils.mainThread
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import javax.inject.Inject

@RepoListFragmentScope
@InjectViewState
class RepoListPresenter @Inject constructor(
    private val userInteractor: IUserInteractor,
    private val errorHelper: ErrorHelper
) : BasePresenter<RepoListView>() {

    private val disposable: CompositeDisposable = CompositeDisposable()
    private var typedName: String? = null
    private var typedNameChanged = false
    private var data: MutableList<Repo> = ArrayList()
    private var page = 0
    private var pageCount = 0
    private var loadingInProgress = false
    private var hasLoaded = false

    override fun onFirstStart() {
        load(0)
    }

    private fun load(page: Int) {
        if (page == 0) {
            clearDataSet()
        }
        disposable.add(userInteractor.searchUserRepos(typedName, page)
            .mainThread()
            .doOnSubscribe {
                viewState.showLoading()
                loadingInProgress = true
            }
            .doFinally {
                viewState.hideLoading()
                loadingInProgress = false
            }
            .subscribe({ repoSearchResult ->
                hasLoaded = true
                this.page = repoSearchResult.page
                this.pageCount = repoSearchResult.totalPageCount
                this.data.addAll(repoSearchResult.repoList)
                showData()
            }
            ) { throwable -> viewState.showError(errorHelper.handleError(throwable)) })
    }

    private fun clearDataSet() {
        page = 0
        pageCount = 0
        data.clear()

    }

    private fun showData() {
        viewState.setData(data)
        viewState.setShowEmptyText(data.isEmpty() && hasLoaded)
    }

    fun onNameChange(value: String) {
        typedName = value
        typedNameChanged = true
    }

    fun onSearchClick() {
        viewState.hideKeyboard()
        disposable.clear()
        clearDataSet()
        load(0)
    }

    fun onScrollCloseToBottom() {
        if (!loadingInProgress && page < pageCount - 1)
            load(page + 1)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}