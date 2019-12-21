package com.example.justsomeapplication.ui.base

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface BaseMvpView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showLoading()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun hideLoading()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showError(error: String)
}