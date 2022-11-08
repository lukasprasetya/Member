package com.lupa.member.ui.insert

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lupa.member.database.Member
import com.lupa.member.databinding.ActivityAddMemberDialogBinding
import com.lupa.member.databinding.ActivityMemberListBinding
import com.lupa.member.dialog.AddMemberDialog
import com.lupa.member.ui.ViewModelFactory

class MemberListActivity : AppCompatActivity(), AddMemberDialog.DialogListener {

    companion object {
        const val GROUP_ID = "GROUP_ID"
    }

    private val binding: ActivityMemberListBinding by lazy {
        ActivityMemberListBinding.inflate(layoutInflater)
    }

    private var groupId : String? = ""

    private lateinit var adapter: MemberAdapter
    private val viewModel: MemberViewModel by lazy {
        obtainViewModel(this@MemberListActivity)
    }

    private var _dialogAddBinding: ActivityAddMemberDialogBinding? = null
    private val bindingAddMemberDialog get() = _dialogAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        adapter = MemberAdapter()

        binding.rvMember.layoutManager = LinearLayoutManager(this)
        binding.rvMember.setHasFixedSize(true)
        binding.rvMember.adapter = adapter

        intent.extras.let {
            groupId = it?.getString(GROUP_ID)
        }
        if (groupId != null) {
            viewModel.getAllListMember(groupId!!.toInt()).observe(this) {
                adapter.setListMember(it)
            }
        } else {
            Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show()
        }

        binding.fabAdd.setOnClickListener {
            AddMemberDialog().show(supportFragmentManager, "List member")
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): MemberViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[MemberViewModel::class.java]
    }

    override fun processDialog(memberName: String) {
        if (groupId != null) {
            val data = Member(groupId = groupId!!.toInt(), memberName = memberName)
            viewModel.insertMember(data)
        }else{
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show()
        }
    }
}