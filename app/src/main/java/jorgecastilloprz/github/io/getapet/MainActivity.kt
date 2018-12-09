package jorgecastilloprz.github.io.getapet

import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import jorgecastilloprz.github.io.getapet.listener.NavigationIconClickListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.backdrop_menu.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBar()
        setupContentBackground()
    }

    private fun setupActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.setNavigationOnClickListener(
            NavigationIconClickListener(
                this,
                content,
                backdropMenu,
                AccelerateDecelerateInterpolator(),
                getDrawable(R.drawable.ic_dehaze_black_24dp),
                getDrawable(R.drawable.shr_close_menu)
            )
        )
    }

    private fun setupContentBackground() {
        content.background = getDrawable(R.drawable.shr_product_grid_background_shape)
    }
}
