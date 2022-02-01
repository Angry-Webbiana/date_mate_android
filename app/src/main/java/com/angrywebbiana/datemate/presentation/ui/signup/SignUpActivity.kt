package com.angrywebbiana.datemate.presentation.ui.signup

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.angrywebbiana.datemate.R
import com.angrywebbiana.datemate.databinding.ActivitySignupBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.etSignUpPw.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(p0: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.etSignUpPw.text.toString() != binding.etSignUpPwConfirm.text.toString()) {
                    if (binding.etSignUpPwConfirm.text.toString().isNotBlank() || binding.etSignUpPwConfirm.text.toString().isNotEmpty()) {
                        binding.tvSignUpPwConfirmExplain.text = getString(R.string.sign_up_pw_confirm_fail_explain)
                        binding.tvSignUpPwConfirmExplain.visibility = View.VISIBLE
                    } else {
                        binding.tvSignUpPwConfirmExplain.visibility = View.GONE
                    }
                } else {
                    if (binding.etSignUpPwConfirm.text.toString().isNotBlank() || binding.etSignUpPwConfirm.text.toString().isNotEmpty()) {
                        binding.tvSignUpPwConfirmExplain.text = getString(R.string.sign_up_pw_confirm_success_explain)
                        binding.tvSignUpPwConfirmExplain.visibility = View.VISIBLE
                    } else {
                        binding.tvSignUpPwConfirmExplain.visibility = View.GONE
                    }
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.etSignUpPwConfirm.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(p0: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.etSignUpPw.text.toString() != binding.etSignUpPwConfirm.text.toString()) {
                    if (binding.etSignUpPwConfirm.text.toString().isNotBlank() || binding.etSignUpPwConfirm.text.toString().isNotEmpty()) {
                        binding.tvSignUpPwConfirmExplain.text = getString(R.string.sign_up_pw_confirm_fail_explain)
                        binding.tvSignUpPwConfirmExplain.visibility = View.VISIBLE
                    } else {
                        binding.tvSignUpPwConfirmExplain.visibility = View.GONE
                    }
                } else {
                    if (binding.etSignUpPwConfirm.text.toString().isNotBlank() || binding.etSignUpPwConfirm.text.toString().isNotEmpty()) {
                        binding.tvSignUpPwConfirmExplain.text = getString(R.string.sign_up_pw_confirm_success_explain)
                        binding.tvSignUpPwConfirmExplain.visibility = View.VISIBLE
                    } else {
                        binding.tvSignUpPwConfirmExplain.visibility = View.GONE
                    }
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.btnSignUpSubmit.setOnClickListener {
            if (binding.etSignUpPw.text.toString() != binding.etSignUpPwConfirm.text.toString()) {
                Snackbar.make(binding.root, R.string.sign_up_pw_confirm_fail_explain, Snackbar.LENGTH_SHORT).show()
            } else {
                signUpViewModel.submitSignUp(
                    binding.etSignUpId.text.toString(),
                    binding.etSignUpNickname.text.toString(),
                    binding.etSignUpPw.text.toString()
                )
                binding.signupProgressBar.visibility = View.VISIBLE
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                )
            }
        }

        signUpViewModel.isSubmitSuccessLiveData.observe(this, {
            binding.signupProgressBar.visibility = View.GONE
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            if (it.equals(0)) {
                Toast.makeText(this, R.string.sign_up_success, Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
            }
        })
    }
}