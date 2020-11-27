package com.batdemir.github.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView

class CustomViewBinding {
    companion object {
        @JvmStatic
        @BindingAdapter("app:setImage")
        fun ShapeableImageView.bindImage(res: String?) {
            if (res.isNullOrEmpty())
                return
            Glide
                .with(this.context)
                .load(res)
                .into(this)
        }

        @JvmStatic
        @BindingAdapter("app:setAdapter")
        fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
            this.run {
                this.setHasFixedSize(true)
                this.adapter = adapter
            }
        }

        @JvmStatic
        @BindingAdapter("app:setAdapter")
        fun ViewPager2.bindViewPager2Adapter(adapter: RecyclerView.Adapter<*>) {
            this.run {
                this.adapter = adapter
            }
        }
    }
}