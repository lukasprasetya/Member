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

class AddMemberDialog : DialogFragment() {

    private lateinit var binding: ActivityAddMemberDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(requireContext())
        binding = ActivityAddMemberDialogBinding.inflate(inflater, null, false)

        val builder = AlertDialog.Builder(requireContext()).apply {
            setView(binding.root)

            binding.btnMemberAdd.setOnClickListener {
                if (binding.edtNameMemberAdd.text.isEmpty()) {
                    showMessage(R.string.text_not_blank)
                } else {
                    val memberName = binding.edtNameMemberAdd.text.toString()
                    val listener = requireActivity() as DialogListener
                    listener.processDialog(memberName)
                    dismiss()
                }
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
            memberName = binding.edtNameMemberAdd.text.toString(),
        )
    }

    private fun showMessage(messageResId: Int) {
        Toast.makeText(requireContext(), messageResId, Toast.LENGTH_LONG).apply {
            setGravity(Gravity.CENTER, 0, 0)
            show()
        }
    }

    interface DialogListener {
        fun processDialog(memberName: String)
    }
}