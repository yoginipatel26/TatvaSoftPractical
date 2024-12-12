package com.example.yoginipatelpractical.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.yoginipatelpractical.models.Item
import com.example.yoginipatelpractical.models.Converters

@Database(entities = [Item::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase(){
 abstract fun userDao() : UserDao

 companion object {
     @Volatile
     private var instance: AppDatabase? = null

     //     @Volatile
//     private val INSTANCE : AppDatabase? = null
//     fun getInstance(context: Context) : AppDatabase{
//         return INSTANCE ?: synchronized(this,
//             Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "").build()
//             )
//
//     }
     @Synchronized
     fun getInstance(ctx: Context): AppDatabase {
         if (instance == null)
             instance = Room.databaseBuilder(
                 ctx.applicationContext, AppDatabase::class.java,
                 "note_database"
             )
                 .fallbackToDestructiveMigration()
                 .addCallback(roomCallback)
                 .build()

         return instance!!
     }

     private val roomCallback = object : Callback() {
         override fun onCreate(db: SupportSQLiteDatabase) {
             super.onCreate(db)
//            populateDatabase(instance!!)
         }
     }
 }
}