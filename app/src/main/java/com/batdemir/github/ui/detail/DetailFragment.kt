package com.batdemir.github.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.batdemir.github.R
import com.batdemir.github.databinding.FragmentDetailBinding
import com.batdemir.github.ui.BaseFragmentActions
import com.batdemir.github.ui.MainActivity
import javax.inject.Inject

class DetailFragment : Fragment(), BaseFragmentActions {
    @Inject
    lateinit var viewModel: DetailViewModel

    private val binding: FragmentDetailBinding by lazy {
        DataBindingUtil.inflate<FragmentDetailBinding>(
            layoutInflater,
            R.layout.fragment_detail,
            null,
            false
        ).apply {
            this.lifecycleOwner = viewLifecycleOwner
            this.viewModel = viewModel
        }
    }

    override fun onAttach(context: Context) {
        (requireActivity() as MainActivity).homeComponent?.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupDefinition()
        setupData()
        setupListener()
        return binding.root
    }

    override fun setupDefinition() {
        //TODO("Not yet implemented")
    }

    override fun setupData() {
        //TODO("Not yet implemented")
    }

    override fun setupListener() {
        //TODO("Not yet implemented")
    }
}