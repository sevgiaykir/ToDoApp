package com.sa.todoapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sa.todoapp.entity.ToDoList
import kotlinx.coroutines.internal.synchronized

@Database(entities = [ToDoList::class], version = 1)
abstract class ToDoDB: RoomDatabase() {
    abstract fun toDoDao():ToDoDAO

    companion object{
        var INSTANCE:ToDoDB? =null

        fun databaseAccess(context: Context): ToDoDB? {

            if(INSTANCE==null){
                kotlin.synchronized(ToDoDB::class){
                    INSTANCE= Room.databaseBuilder(context.applicationContext,
                        ToDoDB::class.java,
                        "ToDoDB.sqlite").createFromAsset("ToDoDB.sqlite").build()
                }
            }
            return INSTANCE
        }
    }
}