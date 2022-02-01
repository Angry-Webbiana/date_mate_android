package com.angrywebbiana.datemate.presentation.ui.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.angrywebbiana.datemate.R
import com.angrywebbiana.datemate.domain.model.request.SignUp
import com.angrywebbiana.datemate.domain.usecase.PostSignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val postSignUpUseCase: PostSignUpUseCase
): ViewModel() {
    private val _isSubmitSuccessLiveData = MutableLiveData<Int>()
    val isSubmitSuccessLiveData: LiveData<Int> get() = _isSubmitSuccessLiveData

    private val _signUpFailTextLiveData = MutableLiveData<Int>()
    val signUpFailTextLiveData: LiveData<Int> get() = _signUpFailTextLiveData

    fun submitSignUp(email: String, userName: String, pw: String) {
        postSignUpUseCase.execute(SignUp(email, userName, pw))
            .subscribe(object: SingleObserver<Any> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onSuccess(t: Any?) {
                    _isSubmitSuccessLiveData.value = 0
                }

                override fun onError(e: Throwable?) {
                    _isSubmitSuccessLiveData.value = R.string.sign_up_fali
                    Log.e("TESTLOG", "[submitSignUp] onError: $e")
                }
            })
    }
}