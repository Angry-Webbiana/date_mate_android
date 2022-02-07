package com.angrywebbiana.datemate.domain.usecase

import com.angrywebbiana.datemate.data.repository.RemoteRepository
import com.angrywebbiana.datemate.domain.model.request.GroupInfo
import com.angrywebbiana.datemate.domain.model.response.GroupInfoResponse
import com.angrywebbiana.datemate.domain.usecase.common.SingleUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GetGroupInfoUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository
): SingleUseCase<GroupInfo, GroupInfoResponse>() {
    override fun implement(param: GroupInfo): Single<GroupInfoResponse> {
        return remoteRepository.getGroupInfo(param)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}