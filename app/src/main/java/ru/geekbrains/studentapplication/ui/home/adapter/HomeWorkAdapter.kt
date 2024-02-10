package ru.geekbrains.studentapplication.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.studentapplication.R
import ru.geekbrains.studentapplication.domain.models.HomeWork

class HomeWorkAdapter(private val delegate: Delegate?) :
    RecyclerView.Adapter<HomeWorkViewHolder?>() {

    interface Delegate {
        /**
         * Событие наступает при выборе
         * дз из списка.
         * @param homeWork Урок
         */
        fun onHomeWorkItemPicked(homeWork: HomeWork)
    }

    private val data = ArrayList<HomeWork>()

    fun setItems(newList: ArrayList<HomeWork>) {
        val result = DiffUtil.calculateDiff(DiffUtilCallback(data, newList))
        result.dispatchUpdatesTo(this)
        data.clear()
        data.addAll(newList)
    }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeWorkViewHolder =
        HomeWorkViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.home_work_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: HomeWorkViewHolder, position: Int) =
        holder.bind(data[position], delegate)

    inner class DiffUtilCallback(
        private var oldItems: ArrayList<HomeWork>,
        private var newItems: ArrayList<HomeWork>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldItems.size

        override fun getNewListSize(): Int = newItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition].id == newItems[newItemPosition].id

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition] == newItems[newItemPosition]
    }
}