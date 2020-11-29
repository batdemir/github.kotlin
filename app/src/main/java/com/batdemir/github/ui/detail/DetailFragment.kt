package com.batdemir.github.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.batdemir.github.R
import com.batdemir.github.databinding.FragmentDetailBinding
import com.batdemir.github.ui.BaseFragmentActions
import com.batdemir.github.ui.MainActivity
import javax.inject.Inject

class DetailFragment : Fragment(), BaseFragmentActions {
    @Inject
    lateinit var viewModel: DetailViewModel

    private val args: DetailFragmentArgs by navArgs()

    private var binding: FragmentDetailBinding? = null

    override fun onAttach(context: Context) {
        (requireActivity() as MainActivity).homeComponent?.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupDefinition(container, savedInstanceState)
        setupData()
        setupListener()
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun setupDefinition(parent: ViewGroup?, savedInstanceState: Bundle?) {
        binding = DataBindingUtil.inflate<FragmentDetailBinding>(
            layoutInflater,
            R.layout.fragment_detail,
            parent,
            false
        ).apply {
            this.lifecycleOwner = viewLifecycleOwner
            this.viewModel = viewModel
        }
        args.model!!.apply { viewModel.start(this) }
    }

    override fun setupData() {
        viewModel.item.observe(viewLifecycleOwner, {
            binding!!.model = it
            binding!!.executePendingBindings()
        })
    }

    override fun setupListener() {
        binding!!.rootFragmentDetail.setOnRefreshListener {
            setupData()
            binding!!.rootFragmentDetail.isRefreshing = false
        }
        binding!!.buttonStar.setOnClickListener {
            val result = viewModel.updateModel()
            Toast.makeText(
                requireContext(),
                requireContext().getString(if (result) R.string.stared_repo else R.string.un_stared_repo),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}