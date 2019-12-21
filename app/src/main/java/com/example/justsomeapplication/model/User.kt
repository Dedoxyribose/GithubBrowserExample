package com.example.justsomeapplication.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login") val name: String,
    @SerializedName("followers") val followersCount: Int,
    @SerializedName("public_repos") val repositoryCount: Int,
    @SerializedName("avatar_url") val avatarUrl: String
)