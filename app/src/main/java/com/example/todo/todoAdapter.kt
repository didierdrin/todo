package com.example.todo

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.DialogTitle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import kotlinx.android.synthetic.main.itemcontents.view.*

class TodoAdapter (
    private val todos: MutableList<Todo>
        ) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
            class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.itemcontents,
                parent,
                false
            )
        )
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos() {
        todos.removeAll {todo -> todo.isChecked}
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isCheckBox: Boolean) {
        if(isCheckBox) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.itemView.apply {
            tvTodoItemTitle.text = curTodo.title
            TodoCheckBox.isChecked = curTodo.isChecked
            toggleStrikeThrough(tvTodoItemTitle, curTodo.isChecked)
            TodoCheckBox.setOnCheckedChangeListener{ _, isChecked ->
                toggleStrikeThrough(tvTodoItemTitle, isChecked)
                curTodo.isChecked  = !curTodo.isChecked
            }

        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
        }