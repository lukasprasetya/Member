package com.lupa.member.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lupa.member.database.Member
import com.lupa.member.databinding.ItemListRowBinding
import com.lupa.member.helper.MemberDiffCallback
import com.lupa.member.ui.insert.AddMemberActivity

class MemberAdapter : RecyclerView.Adapter<MemberAdapter.MemberViewHolder>() {

    private val listGroupMember = ArrayList<Member>()

    fun setListGroupMember(listGroupMember: List<Member>) {
        val diffCallback = MemberDiffCallback(this.listGroupMember, listGroupMember)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listGroupMember.clear()
        this.listGroupMember.addAll(listGroupMember)
        diffResult.dispatchUpdatesTo(this)
    }

    private val selectionIds = ArrayList<Member>()

    fun getSelection(): List<Member> {
        return selectionIds
    }

    fun resetSelection() {
        selectionIds.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val binding = ItemListRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MemberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        holder.bind(listGroupMember[position])
    }

    override fun getItemCount(): Int {
        return listGroupMember.size
    }

    inner class MemberViewHolder(private val binding: ItemListRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        /*fun bind(member: Member) {
            with(binding) {
                tvTitleList.text = member.group
                tvDescList.text = member.note
                btnAddMemberList.setOnClickListener {
                    val intent = Intent(it.context, AddMemberActivity::class.java)
                    intent.putExtra(AddMemberActivity.EXTRA_MEMBERS, member)
                    it.context.startActivity(intent)
                }
            }
        }*/
        fun bind(member: Member) {
            binding.tvTitleList.text = member.group
            binding.tvDescList.text = member.note
            binding.btnAddMemberList.setOnClickListener {
                val intent = Intent(it.context, AddMemberActivity::class.java)
                it.context.startActivity(intent)
            }
        }

    }
}

