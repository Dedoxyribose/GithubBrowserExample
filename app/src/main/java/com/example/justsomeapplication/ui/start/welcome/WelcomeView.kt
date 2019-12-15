package com.example.justsomeapplication.ui.start.welcome

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface WelcomeView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showMessage(text: String)
}
