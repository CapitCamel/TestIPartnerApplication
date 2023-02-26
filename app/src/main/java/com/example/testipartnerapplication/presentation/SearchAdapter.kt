package com.example.testipartnerapplication.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testipartnerapplication.data.network.DrugResponseItem
import com.example.testipartnerapplication.databinding.ItemBinding

class SearchAdapter: PagingDataAdapter<DrugResponseItem, SearchViewHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<DrugResponseItem>() {
        override fun areItemsTheSame(oldItem: DrugResponseItem, newItem: DrugResponseItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: DrugResponseItem, newItem: DrugResponseItem): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): SearchViewHolder {
        return SearchViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context))).apply {
            itemView.setOnClickListener {
                var navController: NavController? = null
                navController = Navigation.findNavController(itemView)
                navController.navigate(SearchFragmentDirections.actionSearchFragmentToDetailFragment(
                    getItem(adapterPosition)!!.id
                ))
            }
        }
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = getItem(position)

        if (item != null) {
            holder.bind(item)
        }
    }
}

class SearchViewHolder(private val binding: ItemBinding):
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: DrugResponseItem) {
        Glide.with(binding.root.context)
            .load("http://shans.d2.i-partner.ru${item.image}")
            .into(binding.ivPhoto)

        binding.tvName.text = item.name
        binding.tvDescription.text = item.description

    }
}