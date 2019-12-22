package com.example.justsomeapplication.ui.user.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.justsomeapplication.R
import com.example.justsomeapplication.model.User
import com.example.justsomeapplication.ui.base.BaseFragment
import com.example.justsomeapplication.utils.setError
import com.example.justsomeapplication.utils.setTextChangeListener
import dagger.Lazy
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.f_user.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject


class UserFragment : BaseFragment(), UserView {

    @Inject
    lateinit var presenterProvider: Lazy<UserPresenter>

    private val presenter by moxyPresenter { presenterProvider.get() }

    override val layoutResource: Int
        get() = R.layout.f_user
    override val title: String
        get() = getString(R.string.f_user_title)

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bSearch.setOnClickListener { presenter.onSearchClick() }

        etName.setTextChangeListener(presenter::onNameChange)
    }

    override fun setNameError(@StringRes error: Int?) {
        etName.setError(error)
    }

    override fun setUser(user: User?) {
        clData.visibility = if (user != null) View.VISIBLE else View.GONE
        user?.let {
            tvName.text = user.name
            tvFollowersCount.text = user.followersCount.toString()
            tvRepoCount.text = user.repositoryCount.toString()
            loadAvatar(user.avatarUrl)
        }
    }

    private fun loadAvatar(avatarUrl: String) {
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transform(CenterCrop(), RoundedCorners(48))
        Glide.with(this)
            .load(avatarUrl)
            .apply(requestOptions)
            .into(ivAvatar)
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }
}
