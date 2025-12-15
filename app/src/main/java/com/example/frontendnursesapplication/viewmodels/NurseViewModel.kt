package com.example.frontendnursesapplication.viewmodels

import com.example.frontendnursesapplication.entities.LoginUiState
import com.example.frontendnursesapplication.entities.Nurse
import com.example.frontendnursesapplication.entities.NurseUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NurseViewModel {
    private val _uiState = MutableStateFlow(NurseUiState())
    // Evitem que sigui modificable desde fora el AppViewModel
    val uiState: StateFlow<NurseUiState> get()= _uiState.asStateFlow()
    // en el init inicialitzem els valors

    private val _loginState = MutableStateFlow(LoginUiState())
    val loginState: StateFlow<LoginUiState> get() = _loginState.asStateFlow()

    init {
        _uiState.value = NurseUiState(getHardcodedNurses())
        _loginState.value = LoginUiState("","")
    }
    private fun getHardcodedNurses() = listOf(
        Nurse("Juan", "Perez", "juan@mail.com", "juan123", "1234"),
        Nurse("Pepe", "Lopez", "pepe@mail.com", "pepe45", "abcd"),
        Nurse("Maria", "Gomez", "mariag@mail.com", "mariag", "pass")
    )

    // actualitzem els valors creant una nova instancia de _uiState


    fun updateNurse(updatedNurse: Nurse) {
        _uiState.update { state ->
            state.copy(
                nurses = state.nurses.map { nurse ->
                    if (nurse.email == updatedNurse.email) updatedNurse else nurse
                }
            )
        }
    }


    fun getNurses(): List<Nurse> {
        return _uiState.value.nurses
    }


    fun onEmailChange(email: String){
        _loginState.update { it.copy(email = email) }
    }

    fun onPasswordChange(password: String){

        _loginState.update { it.copy(password = password) }
    }

    fun login(){
        val nurses = getHardcodedNurses()
        val email = loginState.value.email
        val password = loginState.value.password

        val nurse = nurses.find { it.email == email && it.password == password }

        if (nurse != null){
            _loginState.update { it.copy(errorMessage = false, success = true) }
        }
        else{
            _loginState.update { it.copy(errorMessage = true) }
        }
    }
}