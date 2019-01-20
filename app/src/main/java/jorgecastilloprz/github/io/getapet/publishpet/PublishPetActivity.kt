package jorgecastilloprz.github.io.getapet.publishpet

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import jorgecastilloprz.github.io.getapet.R
import kotlinx.android.synthetic.main.activity_publish_pet.*

class PublishPetActivity : FragmentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_publish_pet)
    setupToolbar()
  }

  private fun setupToolbar() {
    val iconRes = R.drawable.ic_close_black_24dp_dark
    toolbar.setNavigationIcon(iconRes)
  }

  companion object {

    fun launch(source: AppCompatActivity) {
      source.startActivity(Intent(source, PublishPetActivity::class.java))
    }
  }
}
