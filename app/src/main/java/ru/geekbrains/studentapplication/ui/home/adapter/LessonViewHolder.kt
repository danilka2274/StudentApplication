package ru.geekbrains.studentapplication.ui.home.adapter

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.geekbrains.studentapplication.R
import ru.geekbrains.studentapplication.databinding.LessonItemBinding
import ru.geekbrains.studentapplication.domain.models.Lesson
import ru.geekbrains.studentapplication.utils.click
import ru.geekbrains.studentapplication.utils.setStartDrawableCircleImageFromUri
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


class LessonViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    private val viewBinding: LessonItemBinding by viewBinding()

    fun bind(lesson: Lesson, delegate: LessonAdapter.Delegate?) {
        with(viewBinding) {
            title.text = lesson.title
            root.click { delegate?.onLessonItemPicked(lesson) }
            openInArea.isVisible = lesson.useRemote
            scheduler.text = lesson.scheduler.toString()
            scheduler.setStartDrawableCircleImageFromUri(R.drawable.ic_time)
            Glide.with(icon)
                .load(lesson.icon)
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transition(DrawableTransitionOptions().crossFade(DELAY))
                .placeholder(R.drawable.ic_no_image)
                .error(R.drawable.ic_no_image)
                .into(icon)
        }
    }

    companion object {
        const val DELAY = 800
    }
}