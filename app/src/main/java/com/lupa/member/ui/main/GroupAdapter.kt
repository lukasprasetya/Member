package com.lupa.member.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lupa.member.database.Group
import com.lupa.member.database.Group2
import com.lupa.member.databinding.ItemGroupRowBinding
import com.lupa.member.helper.GroupDiffCallback
import com.lupa.member.ui.insert.MemberListActivity
import com.lupa.member.ui.insert.MemberListActivity.Companion.GROUP_ID

class GroupAdapter : RecyclerView.Adapter<GroupAdapter.MemberViewHolder>() {

    private val listGroupMember = ArrayList<Group2>()

    fun setListGroupMember(listGroupMember: List<Group2>) {
        val diffCallback = GroupDiffCallback(this.listGroupMember, listGroupMember)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listGroupMember.clear()
        this.listGroupMember.addAll(listGroupMember)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val binding = ItemGroupRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MemberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        holder.bind(listGroupMember[position])
    }

    override fun getItemCount(): Int {
        return listGroupMember.size
    }

    inner class MemberViewHolder(private val binding: ItemGroupRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(group: Group2) {
            binding.tvTitleList.text = group.groupName
            binding.tvDescList.text = group.groupNote
            binding.btnAddMemberList.setOnClickListener {
                val bundle = Bundle().apply {
                    putString(GROUP_ID,group.groupId.toString())
                }
                val intent = Intent(it.context, MemberListActivity::class.java)
                intent.putExtras(bundle)
                it.context.startActivity(intent)
            }
        }
    }
}

