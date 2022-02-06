package com.angrywebbiana.datemate.data.local.sharedpref

import android.content.Context
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class SharedPrefModule @Inject constructor(
    @ApplicationContext context: Context
) {
    private var sharedPref = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    var login: Boolean
        get() = sharedPref.getBoolean("login", false)
        set(value) = sharedPref.edit().putBoolean("login", value).apply()

    var userSeq: Int?
        get() = sharedPref.getInt("userSeq", 0)
        set(value) = value?.let { sharedPref.edit().putInt("userSeq", it).apply() }!!

    var email: String?
        get() = sharedPref.getString("email", "")
        set(value) = sharedPref.edit().putString("email", value).apply()

    var token: String?
        get() = sharedPref.getString("token", "")
        set(value) = sharedPref.edit().putString("token", value).apply()

    var userName: String?
        get() = sharedPref.getString("userName", "")
        set(value) = sharedPref.edit().putString("userName", value).apply()
}