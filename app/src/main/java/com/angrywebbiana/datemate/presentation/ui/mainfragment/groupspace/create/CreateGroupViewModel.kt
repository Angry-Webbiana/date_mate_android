package com.angrywebbiana.datemate.presentation.ui.mainfragment.groupspace.create

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.angrywebbiana.datemate.data.repository.SharedPrefRepository
import com.angrywebbiana.datemate.domain.model.User
import com.angrywebbiana.datemate.domain.model.request.CreateGroup
import com.angrywebbiana.datemate.domain.model.request.CreateGroupRequest
import com.angrywebbiana.datemate.domain.model.response.CommonResponse
import com.angrywebbiana.datemate.domain.usecase.PostGroupCreateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

@HiltViewModel
class CreateGroupViewModel @Inject constructor(
    private val postGroupCreateUseCase: PostGroupCreateUseCase,
    private val sharedPrefRepository: SharedPrefRepository
): ViewModel() {

    private val _responseCodeLiveData = MutableLiveData<CommonResponse>()
    val responseCodeLiveData: LiveData<CommonResponse> get() = _responseCodeLiveData

    private val token by lazy {
        sharedPrefRepository.getToken()
    }

    fun createGroup(groupName: String, groupDesc: String) {
        if (token != null) {
            postGroupCreateUseCase.execute(CreateGroup(token!!, CreateGroupRequest(groupName, groupDesc)))
                .subscribe(object: SingleObserver<CommonResponse> {
                    override fun onSubscribe(d: Disposable?) {
                    }

                    override fun onSuccess(t: CommonResponse) {
                        _responseCodeLiveData.value = t
                    }

                    override fun onError(e: Throwable?) {
                        Log.e("TESTLOG", "[createGroup] onError: $e")
                    }
                })
        }
    }
}