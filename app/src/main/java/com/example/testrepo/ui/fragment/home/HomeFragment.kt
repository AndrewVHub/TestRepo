package com.example.testrepo.ui.fragment.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.testrepo.R
import com.example.testrepo.databinding.FragmentHomeBinding
import com.example.testrepo.ui.fragment.base.BaseFragment
import com.example.testrepo.ui.fragment.home.adapter.UsersAdapter
import com.example.testrepo.ui.model.user.UserModel
import com.example.testrepo.utils.ScrollCustom
import com.example.testrepo.utils.decorator.LinearLayoutItemDecorator
import com.example.testrepo.utils.extension.launchWhenStarted
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModel()

    private val usersAdapter by lazy {
        UsersAdapter(viewModel::navigateToDetailsUser)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            setUpStatusBarPadding(appBar)
            val itemDecorator = LinearLayoutItemDecorator(
                top = resources.getDimension(R.dimen.home_fragment_content_top).toInt(),
                bottom = resources.getDimension(R.dimen.home_fragment_content_bottom).toInt(),
                left = resources.getDimension(R.dimen.home_fragment_content_left).toInt(),
                right = resources.getDimension(R.dimen.home_fragment_content_right).toInt(),
                divider = resources.getDimension(R.dimen.home_fragment_content_divider).toInt()
            )
            recyclerUsers.apply {
                adapter = usersAdapter
                addItemDecoration(itemDecorator)
                addOnScrollListener(ScrollCustom(iconScrollTop))
            }
            iconScrollTop.setOnClickListener {
                recyclerUsers.smoothScrollToPosition(0)
            }

            swipeRefresh.setOnRefreshListener {
                viewModel.getUsersFlow()
            }
        }

        viewModel.apply {
            navigate.onEach { findNavController().navigate(it) }
                .launchWhenStarted(viewLifecycleOwner)

            userRepos.onEach(::handleUsersInformation).launchWhenStarted(viewLifecycleOwner)

            loading.onEach { binding.swipeRefresh.isRefreshing = it }
                .launchWhenStarted(viewLifecycleOwner)

            error.onEach {
                Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_SHORT).show()
            }.launchWhenStarted(viewLifecycleOwner)
        }
    }

    private fun handleUsersInformation(list: List<UserModel>) {
        if (list.isNotEmpty()) {
            usersAdapter.submitList(list)
            binding.emptyDataText.isVisible = false
        } else {
            binding.emptyDataText.isVisible = true
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}