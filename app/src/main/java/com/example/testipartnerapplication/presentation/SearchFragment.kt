package com.example.testipartnerapplication.presentation

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testipartnerapplication.MainActivity
import com.example.testipartnerapplication.R
import com.example.testipartnerapplication.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val viewModel by viewModels<SearchViewModel>()

    private val rvAdapter = SearchAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        val rvLayoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.apply {
            layoutManager = rvLayoutManager
            adapter = rvAdapter
        }

        viewModel.data.observe(viewLifecycleOwner, Observer {
            if (it == null) binding.textSearch.visibility = View.VISIBLE
            else binding.textSearch.visibility = View.GONE
            rvAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        })
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query!=null){
                    binding.recyclerView.scrollToPosition(0)
                    viewModel.search(query)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }
}