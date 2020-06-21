package com.deneb.astro.mviskel.ui.herolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.deneb.astro.mviskel.R
import com.deneb.astro.mviskel.data.model.Hero
import kotlinx.android.synthetic.main.item_layout.view.*

class HeroListAdapter(
    private val heroes: ArrayList<Hero>
) : RecyclerView.Adapter<HeroListAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(hero: Hero) {
            itemView.textViewHeroName.text = hero.name
            itemView.textViewClass.text = hero.role
            Glide.with(itemView.imageViewAvatar.context)
                .load(hero.assets.icon)
                .into(itemView.imageViewAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = heroes.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(heroes[position])

    fun addData(list: List<Hero>) {
        heroes.addAll(list)
    }

}