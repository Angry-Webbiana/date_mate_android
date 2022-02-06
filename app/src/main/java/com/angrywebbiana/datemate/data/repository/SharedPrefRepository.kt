package com.angrywebbiana.datemate.data.repository

import com.angrywebbiana.datemate.data.local.sharedpref.SharedPrefModule
import javax.inject.Inject

class SharedPrefRepository @Inject constructor(
    private val sharedPrefModule: SharedPrefModule
) {

    fun isLogin(): Boolean = sharedPrefModule.login
    fun setLogin(value: Boolean) {
        sharedPrefModule.login = value
    }

    fun getUserSeq(): String? = sharedPrefModule.userSeq
    fun setUserSeq(value: String) {
        sharedPrefModule.userSeq = value
    }

    fun getEmail(): String? = sharedPrefModule.email
    fun setEmail(value: String) {
        sharedPrefModule.email = value
    }

    fun getToken(): String? = sharedPrefModule.token
    fun setToken(value: String) {
        sharedPrefModule.token = value
    }

    fun getUserName(): String? = sharedPrefModule.userName
    fun setUserName(value: String) {
        sharedPrefModule.userName = value
    }
}