package com.angrybab.datemate.presentation.ui.login

import androidx.lifecycle.ViewModel
import com.angrybab.datemate.data.repository.SharedPrefRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sharedPrefRepository: SharedPrefRepository
): ViewModel() {
}