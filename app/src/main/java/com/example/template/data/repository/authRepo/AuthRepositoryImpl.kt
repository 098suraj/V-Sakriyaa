package com.example.template.data.repository.authRepo


import com.example.template.data.presistance.SettingsStore
import com.example.template.data.repository.AuthService
import com.example.template.model.VerifyOtp
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.flow.MutableStateFlow
import org.json.JSONObject
import timber.log.Timber
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private  val authService: AuthService,
    private val store: SettingsStore
) : AuthRepository {
    private val TAG = "OTP"

    var verificationOtp: String = ""
    var resentToken: PhoneAuthProvider.ForceResendingToken? = null
    override var signUpState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState.NotInitialized)
        private set

    override suspend fun authenticate() {
        try {
            signUpState.value = LoginState.NotInitialized
            var response = authService.logIn()
            if (response.isSuccessful) {
                response.body().let {
                    it?.let {
                        signUpState.value= LoginState.Loading("Code Sent")
                       Timber.tag("LoginState:${it.message}")
                    }
                }
            } else {
                try {
                    var jObjError = JSONObject(response.errorBody()!!.string())
                    signUpState.value =
                        LoginState.Error(jObjError.getJSONObject("error").toString())
                } catch (e: Exception) {
                    Timber.tag("LoginState").d(e)
                    signUpState.value = LoginState.Error(e.localizedMessage)

                }
            }
        } catch (e: Exception) {
            Timber.tag("LoginState").d(e)
            signUpState.value = LoginState.Error(e.localizedMessage)
        }


    }

    override suspend fun verifyOtp(code: String) {
       try {
           val response=authService.verifyOtp(VerifyOtp(code.toInt()))
           if (response.isSuccessful){
               response.body().let {
                   if (it != null) {
                       if (it.msg=="correct OTP"){
                           store.setUserLoggedInStatus(true)
                          signUpState.value= LoginState.Success("Login Successful")
                       }else{
                           signUpState.value= LoginState.Error(it.msg)
                       }
                   }
               }
           }else{
               try {
                   var jObjError = JSONObject(response.errorBody()!!.string())
                   signUpState.value = LoginState.Error(jObjError.getJSONObject("error").toString())
                   Timber.tag("VerifyOtp").d(jObjError.getJSONObject("error").toString())
               } catch (e: Exception) {
                   Timber.tag("VerifyOtp").d(e)
                   signUpState.value = LoginState.Error(e.localizedMessage)
               }
           }
       } catch (e: Exception) {
           Timber.tag("VerifyOtp").d(e)
           signUpState.value = LoginState.Error(e.localizedMessage)
       }
    }
}