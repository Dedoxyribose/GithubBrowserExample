package com.example.justsomeapplication.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import com.example.justsomeapplication.R
import com.google.android.material.snackbar.Snackbar
import moxy.MvpAppCompatFragment


abstract class BaseFragment : MvpAppCompatFragment(), BaseMvpView {

    @get:LayoutRes
    protected abstract val layoutResource: Int

    protected abstract val title: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutResource, container, false)
    }

    private fun updateTitle() {
        if (activity is BaseActivity) {
            (activity as BaseActivity).setTitle(title)
        }
    }

    override fun onResume() {
        super.onResume()
        updateTitle()
    }

    override fun showError(error: String) {
        val snackBar = Snackbar.make(view!!, error, 3000)
        val snackBarView = snackBar.view
        snackBarView.setBackgroundColor(ContextCompat.getColor(context!!, R.color.errorBack))
        snackBar.show()
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun hideKeyboard() {
        if (view != null) {
            try {
                val imm: InputMethodManager? =
                    context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
                imm?.hideSoftInputFromWindow(view!!.windowToken, 0)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
