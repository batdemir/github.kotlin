package com.batdemir.github.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.batdemir.github.R

fun Activity.move(
    to: Class<*>,
    isKeepHistory: Boolean,
    bundle: Bundle?
) {
    val intent = Intent(this, to)
    if (bundle != null)
        intent.putExtras(bundle)
    this.startActivity(intent)
    this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    if (!isKeepHistory)
        finish()
}