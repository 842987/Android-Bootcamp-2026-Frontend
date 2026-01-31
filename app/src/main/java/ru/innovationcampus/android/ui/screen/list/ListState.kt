package ru.innovationcampus.android.ui.screen.list

import ru.innovationcampus.android.domain.entities.UserEntity

sealed interface ListState {
    data class Error(val reason: String): ListState
    data object Loading: ListState
    data class Content(
        val users: List<UserEntity>
    ): ListState
}