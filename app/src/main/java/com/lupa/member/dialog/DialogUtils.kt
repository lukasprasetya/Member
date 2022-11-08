package com.lupa.member.dialog

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lupa.member.databinding.ActivityDialogBinding


object DialogUtils {

   /* private var listener: OnNameSubmitedListener? = null
    private var listenerNote: OnNoteSubmitedListener? = null

    fun showInputNameDialog(
        context: Context,
        title: String,
        onButtonClick: (AlertDialog?, String) -> (Unit)
    ) {
        var dialog: AlertDialog? = null
        val dialogBinding =
            ActivityDialogBinding.inflate((context as AppCompatActivity).layoutInflater).apply {
                tvTitleDialog.text = title

                btnAddListDialog.setOnClickListener {
                    val name = edtListName.text.toString().trim()
                    val note = edtListNote.text.toString().trim()
                    if (name.isEmpty()){
                        listener?.onNameSubmitedListener(name)
                        Toast.makeText(context, "Please Input Group Name", Toast.LENGTH_SHORT).show()
                    }else if (note.isEmpty()){
                        listenerNote?.onNoteSubmitedListener(note)
                        Toast.makeText(context, "Please Input Notes", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        dialog = AlertDialog.Builder(context)
            .setCancelable(false)
            .setView(dialogBinding.root)
            .create()
        dialog.show()
    }
}

interface OnNameSubmitedListener{
    fun onNameSubmitedListener(name:String)
}

interface OnNoteSubmitedListener{
    fun onNoteSubmitedListener(note:String)*/
}