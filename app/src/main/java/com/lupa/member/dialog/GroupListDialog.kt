package com.lupa.member.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.lupa.member.R
import com.lupa.member.database.Group
import com.lupa.member.database.Member
import com.lupa.member.databinding.ActivityDialogBinding

class GroupListDialog : DialogFragment() {

    private lateinit var binding: ActivityDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(requireContext())
        binding = ActivityDialogBinding.inflate(inflater, null, false)

        val builder = AlertDialog.Builder(requireContext()).apply {
            setView(binding.root)

            binding.btnAddListDialog.setOnClickListener {
                val group= getData() ?: return@setOnClickListener
                val listener = requireActivity() as DialogListener
                listener.processDialog(group)
                dismiss()
            }
        }
        return builder.create()
    }

    private fun getData(): Group? {

        if (binding.edtListName.text.isEmpty()) {
            showMessage(R.string.text_not_blank)
            return null
        }
        if (binding.edtListNote.text.isEmpty()) {
            showMessage(R.string.text_not_blank)
            return null
        }
        return Group(
            groupName = binding.edtListName.text.toString(),
            groupNote = binding.edtListNote.text.toString()
        )
    }

    private fun showMessage(messageResId: Int) {
        Toast.makeText(requireContext(), messageResId, Toast.LENGTH_LONG).apply {
            setGravity(Gravity.CENTER, 0, 0)
            show()
        }
    }
    interface DialogListener {
        fun processDialog(group: Group)

    }
}

