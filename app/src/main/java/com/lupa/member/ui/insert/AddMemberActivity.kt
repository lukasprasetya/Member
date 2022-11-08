package com.lupa.member.ui.insert

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lupa.member.R
import com.lupa.member.database.Member
import com.lupa.member.databinding.ActivityAddMemberBinding
import com.lupa.member.databinding.ActivityAddMemberDialogBinding
import com.lupa.member.databinding.ItemListRowBinding
import com.lupa.member.dialog.AddMemberDialog
import com.lupa.member.ui.ViewModelFactory
import com.lupa.member.ui.main.MemberAdapter
import com.lupa.member.ui.main.MemberAddUpdateViewModel

class AddMemberActivity : AppCompatActivity(), AddMemberDialog.DialogListener {

    private val binding: ActivityAddMemberBinding by lazy {
        ActivityAddMemberBinding.inflate(layoutInflater)
    }

    private val listGroupMemberBinding: ItemListRowBinding by lazy {
        ItemListRowBinding.inflate(layoutInflater)
    }

    private lateinit var adapter: AddMemberAdapter
    private val viewModel: AddMemberViewModel by lazy {
        obtainViewModel(this@AddMemberActivity)
    }

    private var _dialogAddBinding: ActivityAddMemberDialogBinding? = null
    private val bindingAddMemberDialog get() = _dialogAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        adapter = AddMemberAdapter()

        binding.rvMember.layoutManager = LinearLayoutManager(this)
        binding.rvMember.setHasFixedSize(true)
        binding.rvMember.adapter = adapter

        viewModel.getAllListMember().observe(this) {
            adapter.setListMember(it)
        }

        binding.fabAdd.setOnClickListener {
            AddMemberDialog().show(supportFragmentManager,"List member")
        }

    }

    private fun obtainViewModel(activity: AppCompatActivity): AddMemberViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[AddMemberViewModel::class.java]
    }

    override fun processDialog(member: Member) {
        viewModel.insert(member)
    }
}