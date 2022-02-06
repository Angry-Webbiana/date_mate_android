package com.angrywebbiana.datemate.presentation.ui.mainfragment.followers.add

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.angrywebbiana.datemate.data.repository.SharedPrefRepository
import com.angrywebbiana.datemate.domain.model.User
import com.angrywebbiana.datemate.domain.model.request.AddFollower
import com.angrywebbiana.datemate.domain.model.request.TargetUserSeq
import com.angrywebbiana.datemate.domain.model.request.UserProfile
import com.angrywebbiana.datemate.domain.model.response.CommonResponse
import com.angrywebbiana.datemate.domain.model.response.UserProfileResponse
import com.angrywebbiana.datemate.domain.usecase.GetUserProfileUseCase
import com.angrywebbiana.datemate.domain.usecase.PostAddFollowerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

@HiltViewModel
class AddFollowerViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val postAddFollowerUseCase: PostAddFollowerUseCase,
    private val sharedPrefRepository: SharedPrefRepository
): ViewModel() {
    private val _userProfileLiveData = MutableLiveData<User?>()
    val userProfileLiveData: LiveData<User?> get() = _userProfileLiveData

    private val _responseAddFollowerLiveData = MutableLiveData<CommonResponse>()
    val responseAddFollowerLiveData: LiveData<CommonResponse> get() = _responseAddFollowerLiveData

    private val token by lazy {
        sharedPrefRepository.getToken()
    }

    fun searchUser(email: String) {
        if (token != null) {
            getUserProfileUseCase.execute(UserProfile(token!!, email))
                .subscribe(object: SingleObserver<UserProfileResponse> {
                    override fun onSubscribe(d: Disposable?) {
                    }

                    override fun onSuccess(t: UserProfileResponse) {
                        if (t.responseCode == "SUCCESS") {
                            _userProfileLiveData.value = t.message.user
                        } else {
                            _userProfileLiveData.value = null
                        }
                    }

                    override fun onError(e: Throwable?) {
                        _userProfileLiveData.value = null
                        Log.e("TESTLOG", "[searchUser] onError: $e")
                    }
                })
        }
    }

    fun addFollower(targetUserSeq: Int) {
        if (token != null) {
            postAddFollowerUseCase.execute(AddFollower(token!!, TargetUserSeq(targetUserSeq)))
                .subscribe(object: SingleObserver<CommonResponse> {
                    override fun onSubscribe(d: Disposable?) {
                    }

                    override fun onSuccess(t: CommonResponse) {
                        _responseAddFollowerLiveData.value = t
                    }

                    override fun onError(e: Throwable?) {
                        Log.e("TESTLOG", "[addFollower] onError: $e")
                    }
                })
        }
    }
}