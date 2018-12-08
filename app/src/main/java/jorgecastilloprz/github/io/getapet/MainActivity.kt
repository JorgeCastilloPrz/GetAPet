package jorgecastilloprz.github.io.getapet

import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import jorgecastilloprz.github.io.getapet.listener.NavigationIconClickListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBar()
        setupContentBackground()
    }

    private fun setupActionBar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener(
            NavigationIconClickListener(
                this,
                content,
                AccelerateDecelerateInterpolator()
            )
        )
    }

    private fun setupContentBackground() {
        content.background = getDrawable(R.drawable.shr_product_grid_background_shape)
    }
}
