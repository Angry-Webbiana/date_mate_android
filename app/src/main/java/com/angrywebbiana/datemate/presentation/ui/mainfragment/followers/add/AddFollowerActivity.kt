package com.angrywebbiana.datemate.presentation.ui.mainfragment.followers.add

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.angrywebbiana.datemate.R
import com.angrywebbiana.datemate.data.repository.SharedPrefRepository
import com.angrywebbiana.datemate.databinding.ActivityAddFollowerBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class AddFollowerActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAddFollowerBinding
    private val addFollowerViewModel: AddFollowerViewModel by viewModels()

    @Inject
    lateinit var sharedPrefRepository: SharedPrefRepository

    private val imm by lazy {
        this.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    }

    private var targetUserSeq = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddFollowerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnSearchFollower.setOnClickListener {
            addFollowerViewModel.searchUser(binding.etSearchEmail.text.toString())
            binding.progressBarAddFollower.visibility = View.VISIBLE
        }

        binding.btnAddFollower.setOnClickListener {
            addFollowerViewModel.addFollower(targetUserSeq)
            binding.progressBarAddFollower.visibility = View.VISIBLE
        }

        binding.btnCancelAddFollower.setOnClickListener {
            targetUserSeq = -1
            binding.layoutSearchFollower.visibility = View.VISIBLE
            binding.layoutAddFollower.visibility = View.GONE
        }

        addFollowerViewModel.userProfileLiveData.observe(this, {
            if (it != null) {
                if (sharedPrefRepository.getUserSeq() == it.userSeq) {
                    Snackbar.make(binding.root, R.string.can_not_add_self, Snackbar.LENGTH_SHORT).show()
                } else {
                    targetUserSeq = it.userSeq
                    binding.tvAddFollowerId.text = it.userName
                    binding.tvAddFollowerEmail.text = it.email
                    binding.layoutSearchFollower.visibility = View.GONE
                    binding.layoutAddFollower.visibility = View.VISIBLE
                    imm.hideSoftInputFromWindow(binding.etSearchEmail.windowToken, 0)
                }
            } else {
                Snackbar.make(binding.root, R.string.is_not_found_user, Snackbar.LENGTH_SHORT).show()
            }
            binding.progressBarAddFollower.visibility = View.GONE
        })

        addFollowerViewModel.responseAddFollowerLiveData.observe(this, {
            if (it.responseCode == "SUCCESS") {
                Toast.makeText(this, R.string.add_follower_success, Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, R.string.add_follower_fail, Toast.LENGTH_SHORT).show()
            }
            binding.progressBarAddFollower.visibility = View.GONE
        })
    }
}