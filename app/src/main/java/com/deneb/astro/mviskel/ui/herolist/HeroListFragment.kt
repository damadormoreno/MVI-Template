package com.deneb.astro.mviskel.ui.herolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.deneb.astro.mviskel.R
import com.deneb.astro.mviskel.data.model.Hero
import kotlinx.android.synthetic.main.fragment_hero_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

@ExperimentalCoroutinesApi
class HeroListFragment : Fragment() {

    private val heroListViewModel: HeroListViewModel by inject()
    private val adapter = HeroListAdapter()
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hero_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        observeViewModel()
        initListeners()
        fetchHeros()
    }

    private fun initListeners() {
        adapter.clickListener = { hero ->
            heroListViewModel.clickHero(hero)
        }
    }

    private fun fetchHeros() {
        lifecycleScope.launch {
            heroListViewModel.herolistIntent.send(HeroListIntent.FetchHeros)
        }
    }

    private fun setupUI() {
        progressBar = requireView().findViewById(R.id.progressBar)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.run {
            addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
        }
        recyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        heroListViewModel.state
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)

        heroListViewModel.navigateToDetail.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {hero ->
                val bundle = Bundle()
                bundle.putSerializable("hero", hero)
                requireView().findNavController()
                    .navigate(R.id.heroDetailFragment, bundle)
            }
        })
    }

    private fun handleState(state: HeroListState) {
        when (state) {
            is HeroListState.Idle -> {
            }
            is HeroListState.Loading -> {
                progressBar.visibility = View.VISIBLE
            }
            is HeroListState.Heroes -> {
                progressBar.visibility = View.GONE
                renderList(state.heroes)
            }
            is HeroListState.Error -> {
                progressBar.visibility = View.GONE
                Toast.makeText(context, state.error, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun renderList(heroes: List<Hero>) {
        heroes.let { listOfHeros -> listOfHeros.let { adapter.collection = it.orEmpty() } }
        adapter.notifyDataSetChanged()
    }
}