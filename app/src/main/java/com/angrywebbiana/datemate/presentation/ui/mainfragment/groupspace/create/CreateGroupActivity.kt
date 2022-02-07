package com.angrywebbiana.datemate.presentation.ui.mainfragment.groupspace.create

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.angrywebbiana.datemate.R
import com.angrywebbiana.datemate.data.repository.SharedPrefRepository
import com.angrywebbiana.datemate.databinding.ActivityCreateGroupBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CreateGroupActivity: AppCompatActivity() {

    private lateinit var binding: ActivityCreateGroupBinding
    private val createGroupViewModel: CreateGroupViewModel by viewModels()

    @Inject
    lateinit var sharedPrefRepository: SharedPrefRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateGroupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnCreateGroup.setOnClickListener {
            if (binding.etGroupName.text.toString().isEmpty() || binding.etGroupName.text.toString().isBlank()) {
                Snackbar.make(binding.root, R.string.group_name_explain, Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            createGroupViewModel.createGroup(binding.etGroupName.text.toString(), binding.etGroupDesc.text.toString())
            binding.progressBarCreateGroup.visibility = View.VISIBLE
        }

        createGroupViewModel.responseCodeLiveData.observe(this, {
            if (it.responseCode == "SUCCESS") {
                Toast.makeText(this, R.string.create_group_success, Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, R.string.create_group_fail, Toast.LENGTH_SHORT).show()
            }
            binding.progressBarCreateGroup.visibility = View.GONE
        })
    }
}