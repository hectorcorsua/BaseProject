package com.hectorcorsua.example.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hectorcorsua.example.databinding.FragmentFavsExampleBinding
import com.hectorcorsua.example.viewmodel.FavsExampleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavsExampleFragment : Fragment() {
    private var _binding: FragmentFavsExampleBinding? = null
    private val binding get() = _binding!!

    private val favsViewModel: FavsExampleViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavsExampleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favsViewModel.onViewCreated()
        favsViewModel.favsLiveData.observe(viewLifecycleOwner) { examples ->
            with(binding.recyclerFavs) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = FavsExampleAdapter(examples)
            }
        }
        favsViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.pbLoading.isVisible = isLoading
        }
    }
}