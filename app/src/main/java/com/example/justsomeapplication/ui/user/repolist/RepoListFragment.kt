package com.example.justsomeapplication.ui.user.repolist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.justsomeapplication.R
import com.example.justsomeapplication.model.Repo
import com.example.justsomeapplication.ui.base.BaseFragment
import com.example.justsomeapplication.utils.setTextChangeListener
import dagger.Lazy
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.f_repo_list.*
import kotlinx.android.synthetic.main.i_repo.view.*
import moxy.ktx.moxyPresenter
import java.util.*
import javax.inject.Inject

class RepoListFragment : BaseFragment(), RepoListView {

    @Inject
    lateinit var presenterProvider: Lazy<RepoListPresenter>

    private val presenter by moxyPresenter { presenterProvider.get() }
    private val adapter = RepoAdapter()
    private lateinit var layoutManager: LinearLayoutManager

    override val layoutResource: Int
        get() = R.layout.f_repo_list
    override val title: String
        get() = getString(R.string.f_repo_list)

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bSearch.setOnClickListener { presenter.onSearchClick() }

        etName.setTextChangeListener(presenter::onNameChange)

        this.layoutManager = LinearLayoutManager(context)
        rvData.setHasFixedSize(true)
        rvData.layoutManager = layoutManager
        rvData.adapter = adapter
        rvData.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (layoutManager.findLastVisibleItemPosition() >= adapter.dataSet.size - 2)
                    presenter.onScrollCloseToBottom()
            }
        })
    }

    override fun setData(data: List<Repo>) {
        adapter.dataSet = data
    }

    override fun setShowEmptyText(show: Boolean) {
        tvNothingFound.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showLoading() {
        if (adapter.dataSet.isEmpty())
            progressBar.visibility = View.VISIBLE
        else adapter.isLoading = true
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
        adapter.isLoading = false
    }

    class RepoAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        companion object {
            private const val REPO_ITEM_TYPE = 0
            private const val LOADING_ITEM_TYPE = 1
        }

        var dataSet: List<Repo> = Collections.emptyList()
            set(value) {
                field = value
                notifyDataSetChanged()
            }
        var isLoading = false
            set(value) {
                field = value
                notifyDataSetChanged()
            }

        class RepoViewHolder(container: View) : RecyclerView.ViewHolder(container) {
            fun bindRepo(repo: Repo) {
                itemView.tvName.text = repo.name
                itemView.tvLanguage.text = repo.language
            }
        }

        class LoadingViewHolder(container: View) : RecyclerView.ViewHolder(container)

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): RecyclerView.ViewHolder {
            return if (viewType == REPO_ITEM_TYPE) {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.i_repo, parent, false)
                RepoViewHolder(view)
            } else {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.i_loading, parent, false)
                LoadingViewHolder(view)
            }
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (holder is RepoViewHolder)
                holder.bindRepo(dataSet[position])
        }

        override fun getItemViewType(position: Int): Int {
            return if (isLoading && position == dataSet.size) LOADING_ITEM_TYPE
            else REPO_ITEM_TYPE
        }

        override fun getItemCount() = dataSet.size + if (isLoading) 1 else 0
    }

}
