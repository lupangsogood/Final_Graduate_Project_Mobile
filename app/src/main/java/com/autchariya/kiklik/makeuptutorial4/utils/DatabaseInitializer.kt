//package com.autchariya.kiklik.makeuptutorial4.utils
//
//import android.os.AsyncTask
//import android.util.Log
//import com.autchariya.kiklik.makeuptutorial4.database.AppDatabase
//import com.autchariya.kiklik.makeuptutorial4.entity.User
//
//
//object DatabaseInitializer {
//
//    private val TAG = DatabaseInitializer::class.java.name
//
//    fun populateAsync(db: AppDatabase) {
//        val task = PopulateDbAsync(db)
//        task.execute()
//    }
//
//    fun populate Sync(db: AppDatabase) {
//        populateWithTestData(db)
//    }
//
//    private fun addUser(db: AppDatabase, user: User): User {
//        db.userDao().insertAll(user)
//        return user
//    }
//
//    private fun populateWithTestData(db: AppDatabase) {
//        val user = User()
//
//        user.firstName = "xfhxdtjh"
//        user.lastName = "kik"
//        user.age = 23
//        addUser(db, user)
//
//        val userList = db.userDao().all
//        Log.d(DatabaseInitializer.TAG, "Rows Count: " + userList.size)
//    }
//
//    private class PopulateDbAsync internal constructor(private val mDb: AppDatabase) : AsyncTask<Void, Void, Void>() {
//
//        override fun doInBackground(vararg params: Void): Void? {
//            populateWithTestData(mDb)
//            return null
//        }
//
//    }
//}
