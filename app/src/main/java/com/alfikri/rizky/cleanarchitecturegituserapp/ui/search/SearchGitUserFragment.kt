package com.alfikri.rizky.cleanarchitecturegituserapp.ui.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfikri.rizky.cleanarchitecturegituserapp.databinding.FragmentSearchBinding
import com.alfikri.rizky.cleanarchitecturegituserapp.di.components.UserComponent
import com.alfikri.rizky.cleanarchitecturegituserapp.entity.GitUserSearchEntity
import com.alfikri.rizky.cleanarchitecturegituserapp.presenter.GitUserSearchPresenter
import com.alfikri.rizky.cleanarchitecturegituserapp.ui.BaseFragment
import com.alfikri.rizky.cleanarchitecturegituserapp.ui.adapter.GitUserListAdapter
import com.alfikri.rizky.cleanarchitecturegituserapp.ui.detail.DetailGitUserActivity
import com.alfikri.rizky.cleanarchitecturegituserapp.ui.home.MainActivity
import com.alfikri.rizky.cleanarchitecturegituserapp.utils.invisible
import com.alfikri.rizky.cleanarchitecturegituserapp.utils.visible
import com.alfikri.rizky.cleanarchitecturegituserapp.view.GitUserSearchView
import javax.inject.Inject


class SearchGitUserFragment : BaseFragment(), GitUserSearchView {

    @Inject
    lateinit var gitUserListAdapter: GitUserListAdapter

    @Inject
    lateinit var gitUserSearchPresenter: GitUserSearchPresenter

    private var requestQuery = ""
    private lateinit var binding: FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).getComponent().inject(this@SearchGitUserFragment)
//        this@SearchGitUserFragment.getComponent(UserComponent::class.java)
//            ?.inject(this@SearchGitUserFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestQuery = arguments?.getString("query") ?: ""
        gitUserSearchPresenter.setView(this)
        gitUserSearchPresenter.initialize(requestQuery)
        initSearchView()
        initRecyclerView()
        onClickToDetail()
    }

    private fun initRecyclerView() {
        binding.rvGitUser.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = gitUserListAdapter
        }
    }

    private fun onClickToDetail() {
        gitUserListAdapter.onAdapterClick {
            gitUserSearchPresenter.onUserClickGitUser(it.login)
        }
    }

    private fun initSearchView() {
        val searchManager =
            requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager

        binding.searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
            onActionViewExpanded()
            clearFocus()
            setOnQueryTextListener(listener)
        }
    }

    private val listener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            query?.let { gitUserSearchPresenter.initialize(it) }
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }
    }


    override fun renderGitUserSearch(listGit: GitUserSearchEntity) {
        gitUserListAdapter.setData(listGit.gitUserModels)
    }

    override fun viewGitUserDetail(username: String) {
        val intent = Intent(context, DetailGitUserActivity::class.java)
        intent.putExtra("username", username)
        startActivity(intent)
    }

    override fun showLoading() {
        binding.progressBar.visible()
    }

    override fun hideLoading() {
        binding.progressBar.invisible()
    }

    override fun showRetry() {
        binding.rootRetry.visible()
    }

    override fun hideRetry() {
        binding.rootRetry.invisible()
    }

    override fun showError(errorMessage: String) {
        showToastMessage(errorMessage)
    }

    override fun getContext(): Context {
        return activity!!.applicationContext
    }
    override fun onDestroy() {
        super.onDestroy()
        gitUserSearchPresenter.onDestroy()
    }
}
