package com.example.testrepo.ui.fragment.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testrepo.databinding.ItemUserBinding
import com.example.testrepo.ui.model.user.UserModel
import com.example.testrepo.utils.extension.getString
import com.example.testrepo.utils.extension.ifHasPosition
import com.example.testrepo.utils.extension.loadImage

class UsersAdapter(private val onItemClicked: (userId: Int) -> Unit) :
    ListAdapter<UserModel, UsersAdapter.UsersViewHolder>(
        UserModel.DIFF
    ) {

    inner class UsersViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                ifHasPosition {
                    onItemClicked.invoke(getItem(it).id)
                }
            }
        }

        fun bind(item: UserModel) = with(binding) {
            hashtag.text = "#${getString(item.language.hashtag)}"
            imageBox.backgroundTintList =
                ContextCompat.getColorStateList(imageBox.context, item.language.colorBox)
            userName.text = item.login
            image.loadImage(item.language.image)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UsersAdapter.UsersViewHolder {
        return UsersViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: UsersAdapter.UsersViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }
}