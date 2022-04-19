package com.hectorcorsua.example.view

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hectorcorsua.example.R
import com.hectorcorsua.example.databinding.ItemExampleBinding
import com.hectorcorsua.example.model.Example

class ExamplesAdapter(
    private val examples: List<Example>,
    private val onClickFilm: (Example) -> Unit
): RecyclerView.Adapter<ExamplesAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = examples[position]
        holder.bind(item, onClickFilm)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_example, parent, false))
    }

    override fun getItemCount() = examples.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemExampleBinding.bind(view)

        fun bind(item: Example, onClickFilm: (Example) -> Unit) {
            binding.tvTitle.text = item.name
            binding.ivCover.setImageDrawable(getImageSrc(item.image, itemView.context))
            binding.cardFilm.setOnClickListener { onClickFilm(item) }
        }

        private fun getImageSrc(name: String, context: Context): Drawable {
            val resources: Resources = context.resources
            val resourceId: Int = resources.getIdentifier(
                name, "drawable",
                context.packageName
            )
            return ContextCompat.getDrawable(context,resourceId)!!
        }
    }
}