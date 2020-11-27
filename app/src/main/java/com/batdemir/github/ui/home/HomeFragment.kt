package com.batdemir.github.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.batdemir.github.R
import com.batdemir.github.data.entities.RepositoryModel
import com.batdemir.github.databinding.FragmentHomeBinding
import com.batdemir.github.ui.BaseFragmentActions
import com.batdemir.github.ui.MainActivity
import com.batdemir.github.ui.adapter.RepositoryAdapter
import com.batdemir.github.utils.Resource
import javax.inject.Inject

class HomeFragment : Fragment(), BaseFragmentActions {
    @Inject
    lateinit var viewModel: HomeViewModel

    private val binding: FragmentHomeBinding by lazy {
        DataBindingUtil.inflate<FragmentHomeBinding>(
            layoutInflater,
            R.layout.fragment_home,
            null,
            false
        ).apply {
            this.lifecycleOwner = viewLifecycleOwner
            this.viewModel = viewModel
        }
    }

    private var repositoryAdapter: RepositoryAdapter? = null

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
        repositoryAdapter =
            RepositoryAdapter(object : RepositoryAdapter.ItemListener {
                override fun onClick(model: RepositoryModel) {
                    //TODO("Not yet implemented")
                }
            })
        binding.adapter = repositoryAdapter
    }

    override fun setupData() {
        //TODO("Not yet implemented")
    }

    override fun setupListener() {
        binding.inputLayoutUser.setEndIconOnClickListener {
            viewModel.getRepoListByUser(binding.inputLayoutUser.editText!!.text.toString())
                .observe(viewLifecycleOwner, {
                    when (it.status) {
                        Resource.Status.SUCCESS -> {
                            binding.progressBar.visibility = View.GONE
                            if (it.data.isNullOrEmpty())
                                return@observe
                            it.data.let(repositoryAdapter!!::submitList)
                        }
                        Resource.Status.ERROR ->
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        Resource.Status.LOADING -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                    }
                })
        }
    }
}