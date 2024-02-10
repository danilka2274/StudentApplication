package ru.geekbrains.studentapplication.ui.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.geekbrains.studentapplication.R
import ru.geekbrains.studentapplication.databinding.FacultativeItemBinding
import ru.geekbrains.studentapplication.domain.models.Facultative
import ru.geekbrains.studentapplication.utils.click
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class FacultativeViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    private val viewBinding: FacultativeItemBinding by viewBinding()

    fun bind(lesson: Facultative, delegate: LessonAdapter.Delegate?) {
        with(viewBinding) {
            title.text = lesson.title
            teacher.text = String
                .format(TEACHER_STRING_TEMPLATE, lesson.teacher)
            Glide.with(icon)
                .load(lesson.icon)
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transition(DrawableTransitionOptions().crossFade(DELAY))
                .placeholder(R.drawable.ic_no_image)
                .error(R.drawable.ic_no_image)
                .into(icon)
            root.click { delegate?.onLessonItemPicked(lesson) }
        }
    }

    companion object {
        const val TEACHER_STRING_TEMPLATE = "Учитель: %s"
        const val DELAY = 800
    }
}