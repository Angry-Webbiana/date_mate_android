package com.angrywebbiana.datemate.presentation.ui.mainfragment.followers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.angrywebbiana.datemate.databinding.FragmentFollowersBinding
import com.angrywebbiana.datemate.presentation.ui.mainfragment.followers.adapter.FollowersAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowersFragment : Fragment() {

    private val followersViewModel: FollowersViewModel by viewModels()

    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding!!

    private val followersAdapter by lazy {
        FollowersAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowersBinding.inflate(inflater, container, false)

        binding.rvFollowers.also {
            it.layoutManager = LinearLayoutManager(this.context)
            it.adapter = followersAdapter
        }

        followersViewModel.requestFollowersList()
        followersViewModel.followersListLiveData.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                followersAdapter.submitList(it)
                binding.tvIsNotExistFollowers.visibility = View.GONE
            } else {
                binding.tvIsNotExistFollowers.visibility = View.VISIBLE
            }
            binding.progressBarFollowers.visibility = View.GONE
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}