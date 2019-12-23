package com.example.justsomeapplication.ui.user.userinfo

import com.example.justsomeapplication.R
import com.example.justsomeapplication.business.user.IUserInteractor
import com.example.justsomeapplication.di.user.userinfo.UserFragmentScope
import com.example.justsomeapplication.ui.base.BasePresenter
import com.example.justsomeapplication.ui.user.Screens
import com.example.justsomeapplication.utils.ErrorHelper
import com.example.justsomeapplication.utils.mainThread
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@UserFragmentScope
@InjectViewState
class UserPresenter @Inject constructor(
    private val userInteractor: IUserInteractor,
    private val errorHelper: ErrorHelper,
    private val router: Router
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
            viewState.apply {
                hideKeyboard()
                setUser(null)
                setNothingFoundVisibility(false)
            }
            disposable.add(userInteractor.loadUser(typedName)
                .mainThread()
                .doOnSubscribe { viewState.showLoading() }
                .doFinally { viewState.hideLoading() }
                .subscribe(
                    viewState::setUser
                ) { throwable ->
                    if (throwable is ErrorHelper.NotFoundException)
                        viewState.setNothingFoundVisibility(true)
                    else viewState.showError(errorHelper.handleError(throwable))
                })
        }
    }

    fun onReposClick() {
        router.navigateTo(Screens.RepoListScreen())
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