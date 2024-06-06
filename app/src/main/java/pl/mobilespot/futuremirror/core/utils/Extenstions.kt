package pl.mobilespot.futuremirror.core.utils

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.view.animation.AccelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

fun Activity.adaptSplashScreen() {
    installSplashScreen().setOnExitAnimationListener { splashScreen ->
        val alpha = ObjectAnimator.ofFloat(
            splashScreen.view,
            android.view.View.ALPHA,
            1f,
            0f
        )
        alpha.interpolator = AccelerateInterpolator()
        alpha.duration = 500L
        AnimatorSet().apply {
            play(alpha)
            doOnEnd {
                splashScreen.remove()
            }
            start()
        }
    }
}
