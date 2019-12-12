package com.example.justsomeapplication.ui.main.fragment

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface MainView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showMessage(text: String)
}
