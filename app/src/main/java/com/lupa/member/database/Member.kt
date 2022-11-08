package com.lupa.member.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tb_member")
data class Member(
    @PrimaryKey(autoGenerate = false)
    var id: Int? = null,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "note")
    var note: String,

    @ColumnInfo(name = "group")
    var group: String
) : Parcelable
