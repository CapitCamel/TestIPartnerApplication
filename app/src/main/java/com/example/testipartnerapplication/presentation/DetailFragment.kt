package com.example.testipartnerapplication.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.testipartnerapplication.MainActivity
import com.example.testipartnerapplication.R
import com.example.testipartnerapplication.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()
    private val viewModel by viewModels<DetailViewModel>()
    var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailBinding
                .inflate(inflater, container, false)

        viewModel.getDrug(args.id)
        viewModel.drug.observe(viewLifecycleOwner, Observer {
            binding.apply {
                tvName.text = it.name
                tvDescription.text = it.description
                Glide.with(requireContext()).load("http://shans.d2.i-partner.ru${it.image}").into(binding.ivPhoto)
            }

        })

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)

        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                navController?.navigate(R.id.action_detailFragment_to_searchFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

//    override fun onContextItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            android.R.id.home -> {
//                navController?.navigate(R.id.action_detailFragment_to_searchFragment)
//                return true
//            }
//        }
//        return super.onContextItemSelected(item)
//    }

}