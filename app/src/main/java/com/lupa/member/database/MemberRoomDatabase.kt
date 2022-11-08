package com.lupa.member.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Member::class, Group::class], version = 1)
abstract class MemberRoomDatabase : RoomDatabase() {
    abstract fun memberDao(): MemberDao
    abstract fun groupDao(): GroupDao

    companion object {
        @Volatile
        private var INSTANCE: MemberRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): MemberRoomDatabase {
            if (INSTANCE == null) {
                synchronized(MemberRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MemberRoomDatabase::class.java,
                        "member_database"
                    ).build()
                }
            }
            return INSTANCE as MemberRoomDatabase
        }
    }
}
