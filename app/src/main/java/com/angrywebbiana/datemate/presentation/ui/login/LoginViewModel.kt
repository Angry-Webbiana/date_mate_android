package com.angrywebbiana.datemate.presentation.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.angrywebbiana.datemate.data.repository.SharedPrefRepository
import com.angrywebbiana.datemate.domain.model.request.Login
import com.angrywebbiana.datemate.domain.model.response.LoginResponse
import com.angrywebbiana.datemate.domain.usecase.PostLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sharedPrefRepository: SharedPrefRepository,
    private val postLoginUseCase: PostLoginUseCase
): ViewModel() {
    private val _isLoginSuccessLiveData = MutableLiveData<Boolean>()
    val isLoginSuccessLiveData: LiveData<Boolean> get() = _isLoginSuccessLiveData

    fun requestLogin(id: String, pw: String) {
        postLoginUseCase.execute(Login(id, pw))
            .subscribe(object: SingleObserver<LoginResponse> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onSuccess(t: LoginResponse?) {
                    if (t?.responseCode == "SUCCESS") {
                        sharedPrefRepository.setLogin(value = true)
                        sharedPrefRepository.setEmail(id)
                        sharedPrefRepository.setToken(t.message.token)
                        // TODO UserName 저장 필요
                        _isLoginSuccessLiveData.value = true
                    } else {
                        sharedPrefRepository.setLogin(value = false)
                        _isLoginSuccessLiveData.value = false
                        Log.d("TESTLOG", "[requestLogin] Login Fail: ${t?.errMessage}")
                    }
                }

                override fun onError(e: Throwable?) {

                }
            })
    }
}