package com.example.justsomeapplication.ui.user.fragment

import com.example.justsomeapplication.R
import com.example.justsomeapplication.business.user.IUserInteractor
import com.example.justsomeapplication.ui.base.BasePresenter
import com.example.justsomeapplication.utils.ErrorHelper
import com.example.justsomeapplication.utils.mainThread
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class UserPresenter @Inject constructor(
    private val userInteractor: IUserInteractor,
    private val errorHelper: ErrorHelper
) : BasePresenter<UserView>() {

    private val disposable: CompositeDisposable = CompositeDisposable()
    private var typedName: String = ""

    override fun onFirstStart() {
    }

    fun onNameChange(value: String) {
        typedName = value
        viewState.setNameError(null)
    }

    fun onSearchClick() {
        if (checkNameValidWithError()) {
            viewState.setUser(null)
            disposable.add(userInteractor.loadUser(typedName)
                .mainThread()
                .doOnSubscribe { viewState.showLoading() }
                .doFinally { viewState.hideLoading() }
                .subscribe(
                    viewState::setUser
                ) { throwable -> viewState.showError(errorHelper.handleError(throwable)) })
        }
    }

    private fun checkNameValidWithError(): Boolean {
        return if (typedName.isEmpty()) {
            viewState.setNameError(R.string.name_error)
            false
        } else true
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}