package com.example.justsomeapplication.ui.user

import com.example.justsomeapplication.ui.user.fragment.UserFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {
    class UserScreen : SupportAppScreen() {
        override fun getFragment() = UserFragment()
    }
}