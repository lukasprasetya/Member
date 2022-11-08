package com.lupa.member.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lupa.member.database.Group
import com.lupa.member.database.Member
import com.lupa.member.repository.Repository

class GroupViewModel(application: Application) : ViewModel() {

    private val mRepository = Repository(application)

    fun getAllGroupMemberList(): LiveData<List<Group>> = mRepository.getAllGroup()

    fun insertGroup(group: Group){
        mRepository.insertGroup(group)
    }

    fun deleteGroup(group: Group) {
        mRepository.deleteGroup(group)
    }

}