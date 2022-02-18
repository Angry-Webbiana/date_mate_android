package com.angrywebbiana.datemate.presentation.ui.mainfragment.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.angrywebbiana.datemate.data.repository.SharedPrefRepository
import com.angrywebbiana.datemate.databinding.FragmentSettingsBinding
import com.angrywebbiana.datemate.presentation.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment: Fragment() {
    private val settingsViewModel: SettingsViewModel by viewModels()

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var sharedPrefRepository: SharedPrefRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        binding.btnLogout.setOnClickListener {
            sharedPrefRepository.setLogin(false)
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            activity?.finish()
        }

        binding.btnWithdrawal.setOnClickListener {
            sharedPrefRepository.setLogin(false)
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            activity?.finish()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}