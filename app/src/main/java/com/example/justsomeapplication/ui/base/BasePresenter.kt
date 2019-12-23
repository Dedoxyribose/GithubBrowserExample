package com.example.justsomeapplication.ui.base

import moxy.MvpPresenter
import moxy.MvpView

abstract class BasePresenter<T : MvpView> : MvpPresenter<T>() {

    abstract fun onFirstStart()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        onFirstStart()
    }
}
