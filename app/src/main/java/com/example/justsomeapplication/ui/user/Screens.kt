package com.example.justsomeapplication.ui.user

import com.example.justsomeapplication.ui.user.repolist.RepoListFragment
import com.example.justsomeapplication.ui.user.userinfo.UserFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {
    class UserScreen : SupportAppScreen() {
        override fun getFragment() = UserFragment()
    }

    class RepoListScreen : SupportAppScreen() {
        override fun getFragment() = RepoListFragment()
    }
}