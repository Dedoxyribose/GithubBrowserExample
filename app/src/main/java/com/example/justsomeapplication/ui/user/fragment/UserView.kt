package com.example.justsomeapplication.ui.user.fragment

import androidx.annotation.StringRes
import com.example.justsomeapplication.model.User
import com.example.justsomeapplication.ui.base.BaseMvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface UserView : BaseMvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setNameError(@StringRes error: Int?)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setUser(user: User?)

}
