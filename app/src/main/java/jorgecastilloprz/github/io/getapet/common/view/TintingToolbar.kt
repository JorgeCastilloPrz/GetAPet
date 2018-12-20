package jorgecastilloprz.github.io.getapet.common.view

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.Keep
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import jorgecastilloprz.github.io.getapet.R

class TintingToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.toolbarStyle
) : Toolbar(context, attrs, defStyleAttr) {
    @get:Keep
    @set:Keep
    var iconTint: Int = ContextCompat.getColor(context, R.color.colorSecondary)
        set(value) {
            if (value != field) {
                navigationIcon = navigationIcon?.let {
                    it.setTint(value)
                    it.mutate()
                }
                overflowIcon = overflowIcon?.let {
                    it.setTint(value)
                    it.mutate()
                }
            }
            field = value
        }
}
