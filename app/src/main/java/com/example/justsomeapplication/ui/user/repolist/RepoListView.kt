package com.example.justsomeapplication.ui.user.repolist

import com.example.justsomeapplication.model.Repo
import com.example.justsomeapplication.ui.base.BaseMvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface RepoListView : BaseMvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setData(data: List<Repo>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setShowEmptyText(show: Boolean)
}
