package com.example.testrepo.ui.fragment.home.detail

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.testrepo.R
import com.example.testrepo.databinding.FragmentUserDetailBinding
import com.example.testrepo.ui.fragment.base.BaseFragment
import com.example.testrepo.ui.model.user.UserModel
import com.example.testrepo.utils.extension.launchWhenStarted
import com.example.testrepo.utils.extension.loadImage
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val GITHUB_LINK = "github_link"
private const val USER_ID_ARG = "user_id"

class UserDetailFragment :
    BaseFragment<FragmentUserDetailBinding>(FragmentUserDetailBinding::inflate) {

    private val viewModel: UserDetailViewModel by viewModel()

    private val userId by lazy {
        arguments?.getInt(USER_ID_ARG)
    }

    private val clipboard by lazy {
        requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            setUpStatusBarPadding(appBar)

            imageCopy.setOnClickListener {
                val clip: ClipData =
                    ClipData.newPlainText(GITHUB_LINK, imageCopy.contentDescription)
                clipboard.setPrimaryClip(clip).also {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.common_link_coped),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        viewModel.apply {
            userId?.let { getUserById(it) }
            userInfo.onEach(::handleUserInfo).launchWhenStarted(viewLifecycleOwner)
        }
    }

    private fun handleUserInfo(model: UserModel) = with(binding) {
        userName.text = model.login
        model.avatarUrl?.let { userImage.loadImage(it) }
        forksValue.text = model.forks.toString()
        watchersValue.text = model.watchers.toString()
        languageImage.loadImage(model.language.image)
        description.text = model.description
        imageCopy.isVisible = !model.gitHubUrl.isNullOrBlank()
        imageCopy.contentDescription = model.gitHubUrl
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}