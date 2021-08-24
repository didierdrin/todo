package com.example.todo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())

        rvTodoItem.adapter = todoAdapter
        rvTodoItem.layoutManager = LinearLayoutManager(this)

        btnAddItem.setOnClickListener {
            val todoTitle = etTodoItemTitle.text.toString()
            if (todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                etTodoItemTitle.text.clear()
            }
        }

        btnDeleteItem.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }
}