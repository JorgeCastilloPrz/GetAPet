package jorgecastilloprz.github.io.getapet.listener

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.view.animation.Interpolator
import android.widget.ImageView
import androidx.core.view.forEachIndexed
import jorgecastilloprz.github.io.getapet.R

/**
 * [android.view.View.OnClickListener] used to translate the product grid sheet downward on
 * the Y-axis when the navigation icon in the toolbar is pressed.
 */
class NavigationIconClickListener @JvmOverloads internal constructor(
    private val toolbar: View,
    private val sheet: View,
    private val sheetOverlay: View,
    private val backdropMenu: ViewGroup,
    private val interpolator: Interpolator? = null,
    private val openIcon: Drawable? = null,
    private val closeIcon: Drawable? = null
) : View.OnClickListener {

    private var lastAnimation: AnimatorSet? = null
    private var backdropShown = false

    override fun onClick(view: View) {
        backdropShown = !backdropShown

        // Cancel the existing animations
        lastAnimation?.removeAllListeners()
        lastAnimation?.end()
        lastAnimation?.cancel()

        updateIcon(view)

        lastAnimation = AnimatorSet()
        val translateY =
            backdropMenu.height - toolbar.height - toolbar.context.resources.getDimensionPixelSize(R.dimen.shr_product_grid_reveal_height)

        val animators = mutableListOf<Animator>()
        val translationAnim =
            ObjectAnimator.ofFloat(sheet, "translationY", (if (backdropShown) translateY else 0).toFloat())
        translationAnim.duration = if (backdropShown) 300 else 200
        if (interpolator != null) {
            translationAnim.interpolator = interpolator
        }
        animators.add(translationAnim)

        val sheetOverlayFadeAnim = ObjectAnimator.ofFloat(sheetOverlay, "alpha", if (backdropShown) 0.7f else 0f)
        animators.add(sheetOverlayFadeAnim)

        backdropMenu.forEachIndexed { index, currentView ->
            val fadeAnim = ObjectAnimator.ofFloat(currentView, "alpha", if (backdropShown) 1f else 0f)
            fadeAnim.startDelay = index * 30L
            animators.add(fadeAnim)
        }

        lastAnimation?.playTogether(animators)
        lastAnimation?.start()
    }

    private fun updateIcon(view: View) {
        if (openIcon != null && closeIcon != null) {
            if (view !is ImageView) {
                throw IllegalArgumentException("updateIcon() must be called on an ImageView")
            }
            if (backdropShown) {
                view.setImageDrawable(closeIcon)
            } else {
                view.setImageDrawable(openIcon)
            }
        }
    }
}
