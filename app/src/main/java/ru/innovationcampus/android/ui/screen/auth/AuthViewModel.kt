package ru.innovationcampus.android.ui.screen.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.innovationcampus.android.domain.auth.CheckAndSaveAuthUseCase
import ru.innovationcampus.android.domain.auth.CheckAuthFormatUseCase

class AuthViewModel : ViewModel() {
    private val checkAuthFormatUseCase by lazy { CheckAuthFormatUseCase() }
    private val checkAndSaveAuthCodeUseCase by lazy { CheckAndSaveAuthUseCase() }
    private val _uiState = MutableStateFlow<AuthState>(
        AuthState.Data(
            isEnabledSend = false,
            error = null
        )
    )
    val uiState: StateFlow<AuthState> = _uiState.asStateFlow()

    fun onIntent(intent: AuthIntent) {
        when (intent) {
            is AuthIntent.Send -> {
                viewModelScope.launch {
                    checkAndSaveAuthCodeUseCase.invoke(intent.login, intent.password).fold(
                        onSuccess = {
                            // TODO: open list screen
                        },
                        onFailure = { error ->
                            updateStateIfData { oldState ->
                                oldState.copy(
                                    error = error.message
                                )
                            }
                        }
                    )
                }
            }
            is AuthIntent.TextInput -> {
                updateStateIfData { oldState ->
                    oldState.copy(
                        isEnabledSend = checkAuthFormatUseCase.invoke(
                            intent.login,
                            intent.password
                        ),
                        error = null
                    )
                }
            }
        }
    }

    private fun updateStateIfData(lambda: (AuthState.Data) -> AuthState) {
        _uiState.update { state ->
            (state as? AuthState.Data)?.let { lambda.invoke(it) } ?: state
        }

    }
}