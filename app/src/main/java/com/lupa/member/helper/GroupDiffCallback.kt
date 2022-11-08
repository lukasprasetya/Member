package com.lupa.member.helper

import androidx.recyclerview.widget.DiffUtil
import com.lupa.member.database.Group
import com.lupa.member.database.Member

class GroupDiffCallback(
    private val mOldGroupMemberList: List<Group>,
    private val mNewGroupMemberList: List<Group>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return mOldGroupMemberList.size
    }

    override fun getNewListSize(): Int {
        return mNewGroupMemberList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldGroupMemberList[oldItemPosition].groupId == mNewGroupMemberList[newItemPosition].groupId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldGroupMemberList[oldItemPosition]
        val newEmployee = mNewGroupMemberList[newItemPosition]
        return oldEmployee.groupName == newEmployee.groupName && oldEmployee.groupNote == newEmployee.groupNote
    }
}