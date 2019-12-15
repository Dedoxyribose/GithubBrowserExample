package com.example.justsomeapplication.ui.start.welcome

import com.example.justsomeapplication.ui.base.BasePresenter
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class WelcomePresenter @Inject constructor() : BasePresenter<WelcomeView>() {

    override fun onFirstStart() {
    }

    fun onButtonClick() {
        viewState.showMessage("Hello world!")
    }
}