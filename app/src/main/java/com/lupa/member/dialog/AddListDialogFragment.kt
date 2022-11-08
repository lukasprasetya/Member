package com.lupa.member.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.lupa.member.database.Member
import com.lupa.member.databinding.ActivityDialogBinding
import com.lupa.member.ui.ViewModelFactory
import com.lupa.member.ui.main.MemberActivity
import com.lupa.member.ui.main.MemberAddUpdateViewModel

class AddListDialogFragment(
    private val title: String,
    private val onButtonClick: (AddListDialogFragment, String) -> Unit
) : DialogFragment() {

    private lateinit var binding: ActivityDialogBinding


    companion object {
        const val EXTRA_MEMBER = "EXTRA_MEMBER"
        const val ALERT_DIALOG_DELETE = 10
    }

    private var isEdit = false
    private var member: Member? = null

    private lateinit var memberAddUpdateViewModel: MemberAddUpdateViewModel
    private var _dialogBinding: ActivityDialogBinding? = null
    private val bindingDialog get() = _dialogBinding

 /*   private var listener: OnNameSubmitedListener? = null
    private var listenerNote: OnNoteSubmitedListener? = null*/

    override fun onResume() {
        super.onResume()
        val params: WindowManager.LayoutParams? = dialog?.window?.attributes
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        params?.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        // request a window without the title
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityDialogBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            tvTitleDialog.text = title
            btnAddListDialog.setOnClickListener {
               /* onButtonClick(this@AddListDialogFragment, edtListName.text.toString())
                onButtonClick(this@AddListDialogFragment, edtListNote.text.toString())*/
             /*   val name = edtListName.text.toString().trim()
                val note = edtListNote.text.toString().trim()
                if (name.isEmpty()){
                    listener?.onNameSubmitedListener(name)
                    Toast.makeText(context, "Please Input Group Name", Toast.LENGTH_SHORT).show()
                }else if (note.isEmpty()){
                    listenerNote?.onNoteSubmitedListener(note)
                    Toast.makeText(context, "Please Input Notes", Toast.LENGTH_SHORT).show()
                }*/

                /*member = intent.getParcelableExtra(MemberActivity.EXTRA_MEMBER)*/
                /* if (member != null) {
                     isEdit = true
                 } else {
                     member = Member()
                 }*/

            /*    if (member != null) {
                    member?.let { member ->
                        bindingDialog?.edtListName?.setText(member.group)
                        bindingDialog?.edtListNote?.setText(member.note)
                    }
                }

                _dialogBinding = ActivityDialogBinding.inflate(layoutInflater)


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
                        }
                    }
                }

                memberAddUpdateViewModel = obtainViewModel(DialogFragment())*/
            }
            }
        }

 /*   private fun obtainViewModel(fragment: DialogFragment): MemberAddUpdateViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(fragment, factory)[MemberAddUpdateViewModel::class.java]
    }*/
    }

/*
interface OnNameSubmitedListener{
    fun onNameSubmitedListener(name:String)
}

interface OnNoteSubmitedListener{
    fun onNoteSubmitedListener(note:String)
}*/
