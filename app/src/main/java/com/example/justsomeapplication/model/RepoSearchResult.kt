package com.example.justsomeapplication.model

data class RepoSearchResult(
    val repoList: List<Repo>,
    val page: Int,
    val totalPageCount: Int
)