package com.example.justsomeapplication.ui.main.fragment

import com.example.justsomeapplication.ui.base.BasePresenter
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor() : BasePresenter<MainView>() {

    override fun onFirstStart() {
    }

    fun onButtonClick() {
        viewState.showMessage("Hello world!")
    }
}