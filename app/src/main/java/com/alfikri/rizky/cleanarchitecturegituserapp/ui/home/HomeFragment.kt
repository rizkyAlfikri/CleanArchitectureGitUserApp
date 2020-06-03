package com.alfikri.rizky.cleanarchitecturegituserapp.ui.home

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.alfikri.rizky.cleanarchitecturegituserapp.databinding.FragmentHomeBinding
import com.alfikri.rizky.cleanarchitecturegituserapp.ui.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSearchView()
        onClickButton()
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
            val toSearchFragment = HomeFragmentDirections.actionNavigationHomeToNavigationSearch()
            toSearchFragment.arguments.putString("query", binding.searchView.query.toString())
            binding.root.findNavController().navigate(toSearchFragment)
            query?.let { showToastMessage(it) }
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }
    }

    private fun onClickButton() {
        binding.btnSearch.setOnClickListener {
            val toSearchFragment = HomeFragmentDirections.actionNavigationHomeToNavigationSearch()
            toSearchFragment.arguments.putString("query", binding.searchView.query.toString())
            showToastMessage(binding.searchView.query.toString())
            it.findNavController().navigate(toSearchFragment)
        }
    }

}
