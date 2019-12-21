package com.example.justsomeapplication.ui.main

import com.example.justsomeapplication.ui.main.fragment.UserFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {
    class UserScreen : SupportAppScreen() {
        override fun getFragment() = UserFragment()
    }
}