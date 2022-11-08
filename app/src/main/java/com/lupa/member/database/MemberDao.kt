package com.lupa.member.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface MemberDao {
    @Query("SELECT * FROM tb_member WHERE group_id = :groupId ORDER BY member_id  DESC")
    fun getAllMembers(groupId: Int): LiveData<List<Member>>

    @Query("SELECT COUNT(group_id) FROM tb_member WHERE group_id = :groupId")
    fun getCountMembers(groupId: Int): LiveData<Int>
    /* @Query("SELECT * FROM tb_member WHERE member_id = :id")
     suspend fun getAllMembersById(id: Int): Member*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMember(member: Member): Long

    @Delete
    fun deleteMember(member: Member): Int

}
