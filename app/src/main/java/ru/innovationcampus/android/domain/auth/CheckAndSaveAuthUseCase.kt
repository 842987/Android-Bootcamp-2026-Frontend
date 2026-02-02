package ru.innovationcampus.android.domain.auth

class CheckAndSaveAuthUseCase() {
    suspend operator fun invoke(
        login: String,
        password: String,
    ): Result<Unit> {
        return TODO()
    }
}