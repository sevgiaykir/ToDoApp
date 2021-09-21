package com.sa.todoapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName="todo")
data class ToDoList(@PrimaryKey(autoGenerate = true)
                    @ColumnInfo(name = "todo_id") @NotNull var todo_id:Int,
                    @ColumnInfo(name = "todo_task") @NotNull var todo_task:String):Serializable {

}