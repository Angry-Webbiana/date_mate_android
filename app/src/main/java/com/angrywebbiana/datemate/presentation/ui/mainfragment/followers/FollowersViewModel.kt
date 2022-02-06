package com.angrywebbiana.datemate.presentation.ui.mainfragment.followers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.angrywebbiana.datemate.data.repository.SharedPrefRepository
import com.angrywebbiana.datemate.domain.model.User
import com.angrywebbiana.datemate.domain.model.response.FollowersResponse
import com.angrywebbiana.datemate.domain.usecase.GetFollowersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

@HiltViewModel
class FollowersViewModel @Inject constructor(
    private val getFollowersUseCase: GetFollowersUseCase,
    private val sharedPrefRepository: SharedPrefRepository
) : ViewModel() {

    private val _followersListLiveData = MutableLiveData<List<User>>()
    val followersListLiveData: LiveData<List<User>> get() = _followersListLiveData

    private val token by lazy {
        sharedPrefRepository.getToken()
    }

    fun requestFollowersList() {
        if (token != null) {
            getFollowersUseCase.execute(token!!)
                .subscribe(object: SingleObserver<FollowersResponse> {
                    override fun onSubscribe(d: Disposable?) {
                    }

                    override fun onSuccess(t: FollowersResponse) {
                        if (t.responseCode == "SUCCESS") {
                            _followersListLiveData.value = t.message.userList
                        } else {
                            Log.e("TESTLOG", "[requestFollowersList] Fail responseCode: ${t.responseCode}, errMsg: ${t.errMsg}")
                        }
                    }

                    override fun onError(e: Throwable?) {
                        Log.e("TESTLOG", "[requestFollowersList] onError: $e")
                    }
                })
        }
    }
}