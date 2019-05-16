//package com.autchariya.kiklik.makeuptutorial4.database
//
//import android.arch.persistence.room.Database
//import android.arch.persistence.room.Room
//import android.arch.persistence.room.RoomDatabase
//import android.content.Context
//import com.autchariya.kiklik.makeuptutorial4.dao.UserDao
//import com.autchariya.kiklik.makeuptutorial4.entity.User
//
//
//@Database(entities = arrayOf(User::class), version = 1)
//
//abstract class AppDatabase : RoomDatabase() {
//
//    abstract fun userDao(): UserDao
//
//
//    companion object {
//
//        private var INSTANCE: AppDatabase? = null
//
//        fun getAppDatabase(context: Context): AppDatabase? {
//            if (INSTANCE == null) {
//                INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "user-database")
//                        // allow queries on the main thread.
//                        // Don't do this on a real app! See PersistenceBasicSample for an example.
//                        .allowMainThreadQueries()
//                        .build()
//            }
//            return INSTANCE
//        }
//
//        fun destroyInstance() {
//            INSTANCE = null
//        }
//    }
//}
