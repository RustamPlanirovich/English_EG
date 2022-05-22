package com.shainurov.englisheg.presentation.utils

sealed class LoginUIState {
    object Success : LoginUIState()
    data class Error(val message: String) : LoginUIState()
    data class Loading(val time: String) : LoginUIState()
    object Empty : LoginUIState()
}