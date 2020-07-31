package com.deneb.astro.mviskel.ui.herolist

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.deneb.astro.mviskel.R
import com.deneb.astro.mviskel.core.extensions.inflate
import com.deneb.astro.mviskel.data.model.Hero
import kotlinx.android.synthetic.main.item_layout.view.*
import kotlin.properties.Delegates

class HeroListAdapter : RecyclerView.Adapter<HeroListAdapter.ViewHolder>() {

    internal var collection: List<Hero> by Delegates.observable(emptyList()) {
            _, _, _ -> notifyDataSetChanged()
    }

    internal var clickListener: (Hero) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_layout))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(collection[position], clickListener)
    }

    override fun getItemCount() = collection.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(hero: Hero, clickListener: (Hero) -> Unit) {
            itemView.textViewHeroName.text = hero.name
            itemView.textViewClass.text = hero.role
            Glide.with(itemView.imageViewAvatar.context)
                .load(hero.assets.icon)
                .into(itemView.imageViewAvatar)
            itemView.setOnClickListener { clickListener(hero) }
        }
    }
}