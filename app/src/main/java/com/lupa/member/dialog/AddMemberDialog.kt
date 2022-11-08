package com.lupa.member.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.lupa.member.R
import com.lupa.member.database.Member
import com.lupa.member.databinding.ActivityAddMemberDialogBinding
import com.lupa.member.databinding.ActivityDialogBinding
import com.lupa.member.databinding.ItemListRowBinding

class AddMemberDialog : DialogFragment() {

    private lateinit var binding: ActivityAddMemberDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(requireContext())
        binding = ActivityAddMemberDialogBinding.inflate(inflater, null, false)

        val builder = AlertDialog.Builder(requireContext()).apply {
            setView(binding.root)

            /*setPositiveButton("Add") { _, _ ->
                val member = getData() ?: return@setPositiveButton
                val listener = requireActivity() as DialogListener
                listener.processDialog(member)
            }*/
            binding.btnMemberAdd.setOnClickListener {
                val member = getData() ?: return@setOnClickListener
                val listener = requireActivity() as DialogListener
                listener.processDialog(member)
                dismiss()
            }
        }
        return builder.create()
    }

    private fun getData(): Member? {

        if (binding.edtNameMemberAdd.text.isEmpty()) {
            showMessage(R.string.text_not_blank)
            return null
        }
        return Member(
            name = binding.edtNameMemberAdd.text.toString(),
            group = toString(),
            note = toString()
        )
    }

    private fun showMessage(messageResId: Int) {
        Toast.makeText(requireContext(), messageResId, Toast.LENGTH_LONG).apply {
            setGravity(Gravity.CENTER, 0, 0)
            show()
        }
    }

    interface DialogListener {
        fun processDialog(member: Member)
    }
}