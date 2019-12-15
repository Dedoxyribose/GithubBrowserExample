package com.example.justsomeapplication.ui.start

import com.example.justsomeapplication.ui.start.welcome.WelcomeFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {
    class WelcomeScreen : SupportAppScreen() {
        override fun getFragment() = WelcomeFragment()
    }
}