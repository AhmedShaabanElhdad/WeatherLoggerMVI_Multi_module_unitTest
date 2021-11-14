package com.example.feature.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.base.BaseFragment
import com.example.feature.R
import com.example.feature.databinding.FragmentMainBinding
import com.example.feature.model.WeatherUiModel
import com.example.feature.ui.contract.MainContract
import com.example.feature.ui.vm.MainViewModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


/**
 * Main Fragment
 */
@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel : MainViewModel by viewModels()
    private val adapter : WeatherAdapter by lazy {
        WeatherAdapter { weather ->
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment(weather)
            findNavController().navigate(action)
        }
    }

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
        binding.rvWeather.adapter = adapter


        initObservers()
        setHasOptionsMenu(true)
        // Fetch only once
        if (viewModel.currentState.weatherState is MainContract.WeatherState.Idle) {
            viewModel.setEvent(MainContract.Event.OnFetchWeather)
        }
    }

    /**
     * Initialize Observers
     */
    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (val state = it.weatherState) {
                        is MainContract.WeatherState.Idle -> {
                            binding.loadingPb.isVisible = false
                        }
                        is MainContract.WeatherState.Loading -> {
                            binding.loadingPb.isVisible = true
                        }
                        is MainContract.WeatherState.Success -> {
                            val data = state.weatherList
                            adapter.submitList(data)
                            initChart(data)
                            binding.loadingPb.isVisible = false
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effect.collect {
                    when (it) {
                        is MainContract.Effect.ShowError -> {
                            val msg = it.message
                        }
                    }
                }
            }
        }
    }







    private var barEntriesArrayList: ArrayList<BarEntry> = ArrayList()
    private fun getBarEntries(data:List<WeatherUiModel>) {
        for (index in data.indices){
            barEntriesArrayList.add(BarEntry(index.toFloat(), data[index].tempVal))
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_list -> {
                binding.rvWeather.visibility = View.VISIBLE
                binding.idBarChart.visibility = View.GONE
                true
            }
            R.id.action_chart -> {
                binding.rvWeather.visibility = View.GONE
                binding.idBarChart.visibility = View.VISIBLE
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
    }


    // variable for our bar chart
    var barChart: BarChart? = null

    // variable for our bar data.
    var barData: BarData? = null

    // variable for our bar data set.
    var barDataSet: BarDataSet? = null

    fun initChart(weatherList: List<WeatherUiModel>) {


        getBarEntries(weatherList)
        barDataSet = BarDataSet(barEntriesArrayList, "Cairo Temperature")
        barData = BarData(barDataSet)
        binding.idBarChart.data = barData
        barDataSet!!.setColors(*ColorTemplate.MATERIAL_COLORS)
        barDataSet!!.valueTextColor = Color.BLACK
        barDataSet!!.valueTextSize = 16f
        binding.idBarChart.description.isEnabled = false
    }

}