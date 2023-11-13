package com.cuibaby.eat

import android.content.Context
import android.widget.Toast

fun Context.showToast(resId: Int) {
    Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show()
}