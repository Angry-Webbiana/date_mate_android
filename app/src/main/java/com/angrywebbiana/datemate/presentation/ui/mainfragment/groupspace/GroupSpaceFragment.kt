package com.angrywebbiana.datemate.presentation.ui.mainfragment.groupspace

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.angrywebbiana.datemate.R
import com.angrywebbiana.datemate.databinding.FragmentGroupSpaceBinding
import com.angrywebbiana.datemate.presentation.ui.mainfragment.followers.add.AddFollowerActivity
import com.angrywebbiana.datemate.presentation.ui.mainfragment.groupspace.adapter.GroupAdapter
import com.angrywebbiana.datemate.presentation.ui.mainfragment.groupspace.create.CreateGroupActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroupSpaceFragment : Fragment() {

    private val groupSpaceViewModel: GroupSpaceViewModel by viewModels()

    private var _binding: FragmentGroupSpaceBinding? = null
    private val binding get() = _binding!!

    private val groupAdapter by lazy {
        GroupAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGroupSpaceBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        binding.rvGroup.also {
            it.layoutManager = LinearLayoutManager(this.context)
            it.adapter = groupAdapter
        }

        groupSpaceViewModel.groupListLiveData.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                groupAdapter.submitList(it)
                binding.tvIsNotExistGroup.visibility = View.GONE
            } else {
                binding.tvIsNotExistGroup.visibility = View.VISIBLE
            }
            binding.progressBarGroup.visibility = View.GONE
        })

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.tvIsNotExistGroup.visibility = View.GONE
        binding.progressBarGroup.visibility = View.VISIBLE
        groupSpaceViewModel.requestGroupList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.group_action_bar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_add_group -> {
                startActivity(Intent(this.requireContext(), CreateGroupActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}