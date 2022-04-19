package com.hectorcorsua.example.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hectorcorsua.example.databinding.FragmentExampleListBinding
import com.hectorcorsua.example.viewmodel.ExamplesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExampleListFragment : Fragment() {
    private var _binding: FragmentExampleListBinding? = null
    private val binding get() = _binding!!

    private val examplesViewModel : ExamplesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExampleListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        examplesViewModel.getNews()
        examplesViewModel.examplesLiveData.observe(viewLifecycleOwner) { films ->
            with(binding.recyclerExamples) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = ExamplesAdapter(films) {
                    val intentDetail = Intent(context, DetailActivity::class.java)
                    intentDetail.putExtra(DetailFragment.EXTRA, it)
                    startActivity(intentDetail)
                }
            }
        }
        examplesViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.pbLoading.isVisible = isLoading
        }
    }
}