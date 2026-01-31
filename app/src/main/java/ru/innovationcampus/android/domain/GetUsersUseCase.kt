package ru.innovationcampus.android.domain

import ru.innovationcampus.android.data.UserRepository
import ru.innovationcampus.android.domain.entities.UserEntity

class GetUsersUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Result<List<UserEntity>> {
        return userRepository.getUsers()
    }
}