package com.lupa.member.ui.main

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ActionMode
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lupa.member.R
import com.lupa.member.database.Member
import com.lupa.member.database.MemberRoomDatabase
import com.lupa.member.databinding.ActivityDialogBinding
import com.lupa.member.databinding.ActivityMemberBinding
import com.lupa.member.databinding.ItemListRowBinding
import com.lupa.member.dialog.AddListDialogFragment
import com.lupa.member.dialog.GroupListDialog
import com.lupa.member.ui.ViewModelFactory
import com.lupa.member.ui.insert.AddMemberActivity

class MemberActivity : AppCompatActivity(), GroupListDialog.DialogListener {

    private val binding: ActivityMemberBinding by lazy {
        ActivityMemberBinding.inflate(layoutInflater)
    }

    private lateinit var adapter: MemberAdapter
   /* private var actionMode: ActionMode? = null*/

    private val viewModel: MemberAddUpdateViewModel by lazy {
       obtainViewModel(this@MemberActivity)
    }

    private val bindingItemListRowBinding: ItemListRowBinding by lazy {
        ItemListRowBinding.inflate(layoutInflater)
    }

    companion object {
       // const val EXTRA_MEMBER = "ESXTRA_MEMBER"
       const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
    }

   /* private var isEdit = false*/
    private var member: Member? = null

   /* private lateinit var memberAddUpdateViewModel: MemberAddUpdateViewModel
    private var _dialogBinding: ActivityDialogBinding? = null
    private val bindingDialog get() = _dialogBinding*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()
       // setOnClickListeners()

        /*val mainViewModel = obtainViewModel(this@MemberActivity)
        mainViewModel.getAllGroupMemberList().observe(this) { groupMemberList ->
            if (groupMemberList != null) {
                adapter.setListGroupMember(groupMemberList)
            }
        }*/

        binding.btnAddList.setOnClickListener {
            GroupListDialog().show(supportFragmentManager,"Group List Dialog")
        }

        adapter = MemberAdapter()

        binding.rvGroupMemberList.layoutManager = LinearLayoutManager(this)
        binding.rvGroupMemberList.setHasFixedSize(true)
        binding.rvGroupMemberList.adapter = adapter

        bindingItemListRowBinding.btnAddMemberList.setOnClickListener { view ->
            if (view.id == R.id.btn_add_member_list){
                val intent= Intent(this@MemberActivity,AddMemberActivity::class.java)
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
            Log.d(TAG, "onCreate: list")
        }


        //member = intent.getParcelableExtra(EXTRA_MEMBER)
        /* if (member != null) {
             isEdit = true
         } else {
             member = Member()
         }*/

       /* if (member != null) {
            member?.let { member ->
                bindingDialog?.edtListName?.setText(member.group)
                bindingDialog?.edtListNote?.setText(member.note)
            }
        }*/

     /*   _dialogBinding = ActivityDialogBinding.inflate(layoutInflater)

        memberAddUpdateViewModel = obtainViewModel(this@MemberActivity)*/


    }

    private fun obtainViewModel(activity: AppCompatActivity): MemberAddUpdateViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[MemberAddUpdateViewModel::class.java]
    }

    override fun processDialog(member: Member) {
        viewModel.insert(member)
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