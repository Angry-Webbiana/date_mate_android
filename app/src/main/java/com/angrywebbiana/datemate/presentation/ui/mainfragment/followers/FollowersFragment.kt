package com.angrywebbiana.datemate.presentation.ui.mainfragment.followers

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.angrywebbiana.datemate.R
import com.angrywebbiana.datemate.databinding.FragmentFollowersBinding
import com.angrywebbiana.datemate.presentation.ui.mainfragment.followers.adapter.FollowersAdapter
import com.angrywebbiana.datemate.presentation.ui.mainfragment.followers.add.AddFollowerActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowersFragment : Fragment() {

    private val followersViewModel: FollowersViewModel by viewModels()

    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding!!

    private val followersAdapter by lazy {
        FollowersAdapter(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowersBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        binding.rvFollowers.also {
            it.layoutManager = LinearLayoutManager(this.context)
            it.adapter = followersAdapter
        }

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

    override fun onResume() {
        super.onResume()
        binding.tvIsNotExistFollowers.visibility = View.GONE
        binding.progressBarFollowers.visibility = View.VISIBLE
        followersViewModel.requestFollowersList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.followers_action_bar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_add_friend -> {
                startActivity(Intent(this.requireContext(), AddFollowerActivity::class.java))
            }

            R.id.action_delete_friend -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }
}