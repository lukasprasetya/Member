package com.lupa.member.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Member::class], version = 1)
abstract class MemberRoomDatabase : RoomDatabase() {
    abstract fun memberDao(): MemberDao

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
/*  companion object {
      private const val DB_NAME = "RANDOMAPP.db"

      @Volatile
      private var INSTANCE: MemberRoomDatabase? = null
      fun getInstance(context: Context): MemberRoomDatabase {
          return INSTANCE ?: synchronized(this) {

              val instance = Room.databaseBuilder(
                  context.applicationContext,
                  MemberRoomDatabase::class.java,
                  DB_NAME
              )
                  .fallbackToDestructiveMigration()
                  .addCallback(DatabaseSeederCallback(context))
                  .build()
              INSTANCE = instance
              instance
          }
      }

      class DatabaseSeederCallback(private val context: Context): RoomDatabase.Callback() {
          private val job = Job()
          private val scope = CoroutineScope(Dispatchers.IO + job)

          override fun onCreate(db: SupportSQLiteDatabase) {
              super.onCreate(db)
              scope.launch {
                  MemberRoomDatabase.getInstance(context).memberDao().insertMembers(prepopulateNotes())
              }
          }

          private fun prepopulateNotes(): List<Member> {
              return mutableListOf(
                  Member(name = "Member 1", note = "lorem ipsum", group = "group 1"),
                  Member(name = "Member 2", note = "lorem ipsum", group = "group 2"),
                  Member(name = "Member 3", note = "lorem ipsum", group = "group 3")
              )
          }
      }
  }*/
