//package com.autchariya.kiklik.makeuptutorial4.dao
//
//import android.arch.persistence.room.Dao
//import android.arch.persistence.room.Delete
//import android.arch.persistence.room.Insert
//import android.arch.persistence.room.Query
//import com.autchariya.kiklik.makeuptutorial4.entity.User
//
//@Dao
//interface UserDao {
//
//    @get:Query("SELECT * FROM user")
//    val all: List<User>
//
//    @Query("SELECT * FROM user where first_name LIKE  :firstName AND last_name LIKE :lastName")
//    fun findByName(firstName: String, lastName: String): User
//
//    @Query("SELECT COUNT(*) from user")
//    fun countUsers(): Int
//
//    @Insert
//    fun insertAll(vararg users: User)
//
//    @Delete
//    fun delete(user: User)
//
//
//}