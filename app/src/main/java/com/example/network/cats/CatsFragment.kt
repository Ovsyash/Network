package com.example.network.cats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.network.R
import com.example.network.data.cats.CatsItem
import com.example.network.databinding.CatsFragmentBinding
import com.example.network.databinding.CatsItemBinding

class CatsFragment : Fragment() {

    private lateinit var binding: CatsFragmentBinding
    private lateinit var catsRecyclerView: RecyclerView
    private lateinit var catsViewModel: CatsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retainInstance = true

        catsViewModel = ViewModelProviders.of(this)[CatsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = CatsFragmentBinding.inflate(inflater)
        catsRecyclerView = binding.catsRecyclerView
        catsRecyclerView.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        catsViewModel.catsItemLiveData.observe(
            viewLifecycleOwner
        ) { catsItems ->
            catsRecyclerView.adapter = CatsAdapter(catsItems)
        }
    }

    private inner class CatsHolder(item: View) : RecyclerView.ViewHolder(item) {

        val bindingClass = CatsItemBinding.bind(item)
        private lateinit var catsItem: CatsItem

        fun bind(cats: CatsItem) = with(bindingClass) {
            labelName.text = cats.id

            Glide.with(imageCat)
                .load(cats.url)
                .into(imageCat)
        }

        fun bindNewsItem(item: CatsItem) {
            catsItem = item
        }
    }

    private inner class CatsAdapter(private val catsItems: List<CatsItem>)
        : RecyclerView.Adapter<CatsHolder>() {

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): CatsHolder {
            val view = layoutInflater.inflate(
                R.layout.cats_item,
                parent,
                false)
            return CatsHolder(view)
        }

        override fun onBindViewHolder(holder: CatsHolder, position: Int) {
            holder.bind(catsItems[position])
            holder.bindNewsItem(catsItems[position])
        }

        override fun getItemCount(): Int = catsItems.size
    }

    companion object {
        fun newInstance() = CatsFragment()
    }
}
