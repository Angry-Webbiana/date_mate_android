package com.angrywebbiana.datemate.presentation.ui.mainfragment.groupspace

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.angrywebbiana.datemate.data.repository.SharedPrefRepository
import com.angrywebbiana.datemate.domain.model.response.GroupList
import com.angrywebbiana.datemate.domain.model.response.GroupListResponse
import com.angrywebbiana.datemate.domain.usecase.GetGroupListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

@HiltViewModel
class GroupSpaceViewModel @Inject constructor(
    private val getGroupListUseCase: GetGroupListUseCase,
    private val sharedPrefRepository: SharedPrefRepository
): ViewModel() {

    private val _groupListLiveData = MutableLiveData<List<GroupList>>()
    val groupListLiveData: LiveData<List<GroupList>> get() = _groupListLiveData

    private val token by lazy {
        sharedPrefRepository.getToken()
    }

    fun requestGroupList() {
        if (token != null) {
            getGroupListUseCase.execute(token!!)
                .subscribe(object: SingleObserver<GroupListResponse> {
                    override fun onSubscribe(d: Disposable?) {
                    }

                    override fun onSuccess(t: GroupListResponse) {
                        if (t.responseCode == "SUCCESS") {
                            _groupListLiveData.value = t.message.groupList
                        } else {
                            Log.e("TESTLOG", "[requestFollowersList] Fail responseCode: ${t.responseCode}, errMsg: ${t.errMsg}")
                        }
                    }

                    override fun onError(e: Throwable?) {
                        Log.e("TESTLOG", "[requestGroupList] onError: $e")
                    }
                })
        }
    }
}