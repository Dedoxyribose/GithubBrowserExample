package com.example.justsomeapplication.ui.start.welcome

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.justsomeapplication.R
import com.example.justsomeapplication.ui.base.BaseFragment
import dagger.Lazy
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.f_welcome.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class WelcomeFragment : BaseFragment(), WelcomeView {

    @Inject
    lateinit var presenterProvider: Lazy<WelcomePresenter>

    private val presenter by moxyPresenter { presenterProvider.get() }

    override val layoutResource: Int
        get() = R.layout.f_welcome
    override val title: String
        get() = ""

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bButton.text = "Привет!"
        bButton.setOnClickListener { presenter.onButtonClick() }
    }

    override fun showMessage(text: String) {
        Toast.makeText(activity, text, Toast.LENGTH_LONG).show()
    }
}
