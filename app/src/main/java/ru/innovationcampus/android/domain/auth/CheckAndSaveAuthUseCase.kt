package ru.innovationcampus.android.domain.auth

import ru.innovationcampus.android.data.AuthRepository

class CheckAndSaveAuthUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        login: String,
        password: String,
    ): Result<Unit> {
        return TODO()
    }
}