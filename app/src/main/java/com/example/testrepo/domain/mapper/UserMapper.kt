package com.example.testrepo.domain.mapper

import com.example.testrepo.data.network.model.ItemUserResponse
import com.example.testrepo.ui.model.user.UserModel
import com.example.testrepo.ui.model.user.getLanguageTitleAndImage

fun ItemUserResponse.toModel() = UserModel(
    id = id,
    login = owner.login,
    avatarUrl = owner.avatarUrl,
    description = description,
    forks = forks,
    gitHubUrl = htmlUrl,
    language = getLanguageTitleAndImage(language),
    watchers = watchers
)