package com.angrybab.datemate.data.local

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
}