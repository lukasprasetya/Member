package com.lupa.member.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lupa.member.R
import com.lupa.member.database.Group
import com.lupa.member.databinding.ActivityGroupListBinding
import com.lupa.member.databinding.ItemGroupRowBinding
import com.lupa.member.dialog.GroupListDialog
import com.lupa.member.ui.ViewModelFactory
import com.lupa.member.ui.insert.MemberListActivity

class GroupListActivity : AppCompatActivity(), GroupListDialog.DialogListener,
    GroupAdapter.OnCLickListener {

    private val binding: ActivityGroupListBinding by lazy {
        ActivityGroupListBinding.inflate(layoutInflater)
    }

    private lateinit var adapter: GroupAdapter

    private val viewModel: GroupViewModel by lazy {
        obtainViewModel(this@GroupListActivity)
    }

    private val bindingItemListRowBinding: ItemGroupRowBinding by lazy {
        ItemGroupRowBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnAddList.setOnClickListener {
            GroupListDialog().show(supportFragmentManager, "Group List Dialog")
        }

        adapter = GroupAdapter(this)

        binding.rvGroupMemberList.layoutManager = LinearLayoutManager(this)
        binding.rvGroupMemberList.setHasFixedSize(true)
        binding.rvGroupMemberList.adapter = adapter

        bindingItemListRowBinding.btnAddMemberList.setOnClickListener { view ->
            if (view.id == R.id.btn_add_member_list) {
                val intent = Intent(this@GroupListActivity, MemberListActivity::class.java)
                startActivity(intent)
            }
        }
        viewModel.getAllGroupMemberList().observe(this) {
            adapter.setListGroupMember(it)
            /*  countMember(it)*/
        }

    }

    private fun obtainViewModel(activity: AppCompatActivity): GroupViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[GroupViewModel::class.java]
    }

    override fun processDialog(group: Group) {
        viewModel.insertGroup(group)
    }

    override fun onDeleteClickListener(group: Group) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.text_delete_dialog))
            .setMessage("Are you sure want to delete ${group.groupName} ?")
            .setPositiveButton(getString(R.string.text_yes_dialog)) { dialog, _ ->
                viewModel.deleteGroup(group)
                Toast.makeText(this, "${group.groupName} Successfully Deleted", Toast.LENGTH_SHORT)
                    .show()
                dialog.dismiss()
            }
            .setNegativeButton(getString(R.string.text_no_dialog)) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
}