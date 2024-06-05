package com.example.lifecycles.lifecycle

import android.content.Context
import android.os.SystemClock
import android.util.AttributeSet
import android.widget.Chronometer
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class MyChronometer : Chronometer, DefaultLifecycleObserver {

    private var mElapsedTime = 0L

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        base = SystemClock.elapsedRealtime() - mElapsedTime
        start()
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        mElapsedTime = SystemClock.elapsedRealtime() - base
        stop()
    }
}