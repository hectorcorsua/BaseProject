package com.hectorcorsua.example.view

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.hectorcorsua.example.databinding.FragmentDetailBinding
import com.hectorcorsua.example.model.Example
import com.hectorcorsua.example.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    companion object {

        const val EXTRA = "example"

        @JvmStatic
        fun newInstance(example: Example): DetailFragment {
            val fragment = DetailFragment()
            val arg = Bundle()
            arg.putParcelable(EXTRA, example)
            fragment.arguments = arg
            return fragment
        }
    }

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val detailViewModel: DetailViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val film = getFilmFromArguments()
        showContent(film)
        detailViewModel.onViewCreated(film)
    }

    private fun showContent(example: Example?) {
        example?.let {
            binding.tvTitle.text = it.name
            binding.tvDescription.text = it.description
            showIsFavorite(it)
            binding.ivFav.setOnClickListener {
                binding.ivFav.setColorFilter(Color.parseColor("#FFDAA95E"))
                detailViewModel.onClickFavorite(example)
            }
        }
    }

    private fun showIsFavorite(example: Example) {
        binding.tvDescription.text = example.description //This line show model use in this method
        detailViewModel.isFavorite.observe(viewLifecycleOwner) { isFavorite ->
            if (isFavorite) {
                binding.ivFav.setColorFilter(Color.parseColor("#FFDAA95E"))
            } else {
                binding.ivFav.setColorFilter(Color.parseColor("#000000"))
            }
        }
    }

    private fun getFilmFromArguments(): Example? =
        arguments?.let {
            it.getParcelable(EXTRA) as? Example
        }

    private fun getImageSrc(name: String, context: Context?): Drawable? {
        return context?.let {
            val resources: Resources = context.resources
            val resourceId: Int = resources.getIdentifier(
                name, "drawable",
                context.packageName
            )
            ContextCompat.getDrawable(context, resourceId)
        }
    }
}