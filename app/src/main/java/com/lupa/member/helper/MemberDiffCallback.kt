package com.lupa.member.helper

import androidx.recyclerview.widget.DiffUtil
import com.lupa.member.database.Member

class MemberDiffCallback(
    private val mOldGroupMemberList: List<Member>,
    private val mNewGroupMemberList: List<Member>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return mOldGroupMemberList.size
    }

    override fun getNewListSize(): Int {
        return mNewGroupMemberList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldGroupMemberList[oldItemPosition].memberId == mNewGroupMemberList[newItemPosition].memberId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldGroupMemberList[oldItemPosition]
        val newEmployee = mNewGroupMemberList[newItemPosition]
        return oldEmployee.memberName == newEmployee.memberName
    }
}