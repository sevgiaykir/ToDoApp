package com.sa.todoapp.room

import androidx.room.*
import com.sa.todoapp.entity.ToDoList

@Dao
interface ToDoDAO {
    @Query("SELECT * FROM todo")
    suspend fun allTasks(): List<ToDoList>

    @Query("SELECT * FROM todo WHERE todo_task like '%' || :searchWord || '%'")
    suspend fun searchTask(searchWord:String): List<ToDoList>

    @Insert
    suspend fun addTask(task:ToDoList)

    @Update
    suspend fun updateTask(task:ToDoList)

    @Delete
    suspend fun deleteTask(task:ToDoList)
}