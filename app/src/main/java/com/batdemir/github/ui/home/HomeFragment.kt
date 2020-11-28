package com.batdemir.github.ui.home

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.batdemir.github.R
import com.batdemir.github.data.entities.RepositoryModel
import com.batdemir.github.databinding.FragmentHomeBinding
import com.batdemir.github.ui.BaseFragmentActions
import com.batdemir.github.ui.MainActivity
import com.batdemir.github.ui.adapter.RepositoryAdapter
import com.batdemir.github.utils.Resource
import com.batdemir.github.utils.hideKeyboard
import javax.inject.Inject

class HomeFragment : Fragment(), BaseFragmentActions {
    @Inject
    lateinit var viewModel: HomeViewModel

    private var binding: FragmentHomeBinding? = null

    private var repositoryAdapter: RepositoryAdapter? = null

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
        binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            layoutInflater,
            R.layout.fragment_home,
            parent,
            false
        ).apply {
            this.lifecycleOwner = viewLifecycleOwner
            this.viewModel = viewModel
        }
        if (repositoryAdapter == null) {
            repositoryAdapter =
                RepositoryAdapter(object : RepositoryAdapter.ItemListener {
                    override fun onClick(model: RepositoryModel) {
                        CURRENT_USER = ""
                        findNavController().navigate(
                            HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                                model.name!!,
                                model
                            )
                        )
                    }
                })
        }
        binding!!.adapter = repositoryAdapter
    }

    override fun setupData() {
        if (CURRENT_USER.isNotEmpty()) {
            hideKeyboard()
            viewModel.getRepoListByUser(CURRENT_USER)
                .observe(viewLifecycleOwner, {
                    when (it.status) {
                        Resource.Status.SUCCESS -> {
                            binding!!.progressBar.visibility = View.GONE
                            if (it.data.isNullOrEmpty())
                                return@observe
                            it.data.let(repositoryAdapter!!::submitList)
                        }
                        Resource.Status.ERROR -> {
                            binding!!.progressBar.visibility = View.GONE
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        }
                        Resource.Status.LOADING -> {
                            binding!!.progressBar.visibility = View.VISIBLE
                        }
                    }
                })
        }
    }

    override fun setupListener() {
        binding!!.inputLayoutUser.editText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Not implemented
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Not implemented
            }

            override fun afterTextChanged(p0: Editable?) {
                CURRENT_USER = p0.toString()
            }
        })
        binding!!.inputLayoutUser.editText!!.setOnEditorActionListener { _, p1, p2 ->
            if (p1 == EditorInfo.IME_ACTION_SEARCH
                || p1 == EditorInfo.IME_ACTION_DONE
                || p2.action == KeyEvent.ACTION_DOWN
                && p2.keyCode == KeyEvent.KEYCODE_ENTER
            ) {
                setupData()
            }
            false
        }
        binding!!.inputLayoutUser.setEndIconOnClickListener {
            setupData()
        }
        binding!!.rootFragmentHome.setOnRefreshListener {
            setupData()
            binding!!.rootFragmentHome.isRefreshing = false
        }
    }

    companion object {
        private var CURRENT_USER: String = ""
    }
}