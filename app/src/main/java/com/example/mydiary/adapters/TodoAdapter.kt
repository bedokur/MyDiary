package com.example.mydiary.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mydiary.R
import com.example.mydiary.models.TodoModel
import com.example.mydiary.ui.detailsActivity.Navigator
import java.text.SimpleDateFormat
import java.util.Locale

class TodoAdapter(val listener: Navigator) :
    ListAdapter<TodoModel, TodoAdapter.TodoViewHolder>(DiffUtilAdapterCallback()) {

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val todoTime: TextView = itemView.findViewById(R.id.todo_time)
        private val todoName: TextView = itemView.findViewById(R.id.todo_name)

        private val stringFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())

        fun bind(item: TodoModel) {
            todoTime.text =
                "${stringFormatter.format(item.date_start)} - ${stringFormatter.format(item.date_finish)}"
            todoName.text = item.name
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val item = currentList[position]
                listener.navigate(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_rv_element, parent, false)
        return TodoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DiffUtilAdapterCallback : DiffUtil.ItemCallback<TodoModel>() {
    override fun areItemsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
        return oldItem == newItem
    }
}