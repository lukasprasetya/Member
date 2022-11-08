package com.lupa.member.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.lupa.member.database.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Repository(application: Application) {
    private val mMemberDao: MemberDao
    private val mGroupDao: GroupDao

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = MemberRoomDatabase.getDatabase(application)
        mMemberDao = db.memberDao()
        mGroupDao = db.groupDao()
    }

    fun getAllMembers(groupId: Int): LiveData<List<Member>> = mMemberDao.getAllMembers(groupId)

    fun getAllCounts(groupId: Int): LiveData<Int> = mMemberDao.getCountMembers(groupId)

    fun getAllGroup(): LiveData<List<Group>> = mGroupDao.getAllGroups()

    fun insertMember(member: Member) {
        executorService.execute { mMemberDao.insertMember(member) }
    }

    fun deleteMember(member: Member) {
        executorService.execute { mMemberDao.deleteMember(member) }
    }

    fun insertGroup(group: Group) {
        executorService.execute { mGroupDao.insertGroup(group) }
    }

    fun deleteGroup(group: Group){
        executorService.execute { mGroupDao.deleteGroup(group) }
    }

}