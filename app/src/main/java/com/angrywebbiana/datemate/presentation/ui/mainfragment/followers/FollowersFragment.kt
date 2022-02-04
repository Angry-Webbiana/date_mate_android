package com.angrywebbiana.datemate.presentation.ui.mainfragment.followers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.angrywebbiana.datemate.databinding.FragmentFollowersBinding

class FollowersFragment : Fragment() {

    private lateinit var followersViewModel: FollowersViewModel

    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        followersViewModel =
            ViewModelProvider(this).get(FollowersViewModel::class.java)

        _binding = FragmentFollowersBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}