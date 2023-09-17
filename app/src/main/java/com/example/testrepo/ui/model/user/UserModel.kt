package com.example.testrepo.ui.model.user

import androidx.recyclerview.widget.DiffUtil

data class UserModel(
    val id: Int,
    val login: String?,
    val avatarUrl: String?,
    val gitHubUrl: String?,
    val description: String?,
    val watchers: Int?,
    val forks: Int?,
    val language: UserLanguageParams
) {
    companion object DIFF : DiffUtil.ItemCallback<UserModel>() {
        override fun areItemsTheSame(
            oldItem: UserModel,
            newItem: UserModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: UserModel,
            newItem: UserModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}
