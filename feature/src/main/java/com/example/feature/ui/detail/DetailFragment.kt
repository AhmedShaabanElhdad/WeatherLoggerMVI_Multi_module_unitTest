package com.example.feature.ui.detail

import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.navArgs
import com.example.base.BaseFragment
import com.example.feature.R
import com.example.feature.R.*
import com.example.feature.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 * Detail Fragment
 */
@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private val args : DetailFragmentArgs by navArgs()


    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailBinding
        get() = FragmentDetailBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {




        args.weather?.let { model ->
            binding.weather  = model
            binding.executePendingBindings()
            binding.tvThermometer.setValueAndStartAnim(model.temp.toFloat())
        }
    }


}