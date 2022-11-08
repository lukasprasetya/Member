package com.lupa.member.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface GroupDao {
    @Query("SELECT * FROM tb_group ORDER BY group_id DESC")
    fun getAllGroups(): LiveData<List<Group>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGroup(group: Group): Long

}
