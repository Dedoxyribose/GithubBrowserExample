package com.example.justsomeapplication.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.annotation.LayoutRes

import moxy.MvpAppCompatFragment

abstract class BaseFragment : MvpAppCompatFragment() {

    @get:LayoutRes
    protected abstract val layoutResource: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutResource, container, false)
    }
}
