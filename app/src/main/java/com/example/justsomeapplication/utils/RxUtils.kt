package com.example.justsomeapplication.utils

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T : Any> Single<T>.io(): Single<T> {
    return this.subscribeOn(Schedulers.io())
}

fun <T : Any> Single<T>.mainThread(): Single<T> {
    return this.observeOn(AndroidSchedulers.mainThread())
}

fun <T : Any> Single<T>.computation(): Single<T> {
    return this.observeOn(Schedulers.computation())
}