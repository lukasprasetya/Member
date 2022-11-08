package com.lupa.member.ui.main

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lupa.member.R
import com.lupa.member.database.Group
import com.lupa.member.database.Group2
import com.lupa.member.database.Member
import com.lupa.member.databinding.ActivityGroupListBinding
import com.lupa.member.databinding.ItemGroupRowBinding
import com.lupa.member.dialog.GroupListDialog
import com.lupa.member.ui.ViewModelFactory
import com.lupa.member.ui.insert.MemberListActivity
import com.lupa.member.ui.insert.MemberViewModel

class GroupListActivity : AppCompatActivity(), GroupListDialog.DialogListener {

    private val binding: ActivityGroupListBinding by lazy {
        ActivityGroupListBinding.inflate(layoutInflater)
    }

    private lateinit var adapter: GroupAdapter
    /* private var actionMode: ActionMode? = null*/

    private val viewModel: GroupViewModel by lazy {
        obtainViewModel(this@GroupListActivity)
    }

    private val viewModelMember: MemberViewModel by lazy {
        obtainViewModelMember(this@GroupListActivity)
    }

    private val bindingItemListRowBinding: ItemGroupRowBinding by lazy {
        ItemGroupRowBinding.inflate(layoutInflater)
    }

    companion object {
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
    }

    private var member: Member? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnAddList.setOnClickListener {
            GroupListDialog().show(supportFragmentManager, "Group List Dialog")
        }

        adapter = GroupAdapter()

        binding.rvGroupMemberList.layoutManager = LinearLayoutManager(this)
        binding.rvGroupMemberList.setHasFixedSize(true)
        binding.rvGroupMemberList.adapter = adapter

        bindingItemListRowBinding.btnAddMemberList.setOnClickListener { view ->
            if (view.id == R.id.btn_add_member_list) {
                val intent = Intent(this@GroupListActivity, MemberListActivity::class.java)
                startActivity(intent)
                Log.d(TAG, "onCreate: masuk")
                Toast.makeText(this, "masuk harusnya", Toast.LENGTH_SHORT).show()
            }
        }

        bindingItemListRowBinding.btnDeleteMemberList.setOnClickListener {
            showAlertDialog(ALERT_DIALOG_DELETE)
        }

        viewModel.getAllGroupMemberList().observe(this) {
            adapter.setListGroupMember(it)
          /*  countMember(it)*/

        }

    }

   /* private fun countMember(it: List<Group>) {
        val listGroup2 = mutableListOf<Group2>()
        it.forEach { data ->
            viewModelMember.getAllCountMember(data.groupId!!).observe(this) { count ->
                val group2 =
                    Group2(
                        groupId = data.groupId,
                        groupName = data.groupName,
                        groupNote = data.groupNote,
                        groupCount = count
                    )
                listGroup2.add(group2)
            }
        }
        adapter.setListGroupMember(listGroup2)
    }*/

    private fun obtainViewModel(activity: AppCompatActivity): GroupViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[GroupViewModel::class.java]
    }

    private fun obtainViewModelMember(activity: AppCompatActivity): MemberViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[MemberViewModel::class.java]
    }

    override fun onBackPressed() {
        showAlertDialog(ALERT_DIALOG_CLOSE)
    }

    private fun showAlertDialog(type: Int) {
        val isDialogClose = type == ALERT_DIALOG_CLOSE
        val dialogTitle: String
        val dialogMessage: String
        if (isDialogClose) {
            dialogTitle = "Batal"
            dialogMessage = "Ingin membatalkan perubahan?"
        } else {
            dialogMessage = "Yakin Ingin menghapus?"
            dialogTitle = "Hapus"
        }
        val alertDialogBuilder = AlertDialog.Builder(this)
        with(alertDialogBuilder) {
            setTitle(dialogTitle)
            setMessage(dialogMessage)
            setCancelable(false)
            setPositiveButton("Ya") { _, _ ->
                if (!isDialogClose) {
                    viewModel.delete(member as Member)
                    showToast("Terhapus")
                }
                finish()
            }
            setNegativeButton("Tidak") { dialog, _ ->
                dialog.cancel()
            }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun processDialog(group: Group) {
        viewModel.insertGroup(group)
    }

    /*private fun setOnClickListeners() {
        binding.btnAddList.setOnClickListener {
            AddListDialogFragment("Add List") { dialog, value ->

                _dialogBinding?.btnAddListDialog?.setOnClickListener {
                    val name = _dialogBinding?.edtListName?.text.toString().trim()
                    val note = _dialogBinding?.edtListNote?.text.toString().trim()

                    when {
                        name.isEmpty() -> {
                            _dialogBinding?.edtListName?.error = "Field can not be blank"
                        }
                        note.isEmpty() -> {
                            _dialogBinding?.edtListNote?.error = "Field can not be blank"
                        }
                        else -> {
                            member.let { member ->
                                member?.group = name
                                member?.note = note
                            }
                            finish()
                        }
                    }
                }
                Toast.makeText(this, value, Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }.show(supportFragmentManager, null)
        }
    }*/
}