package jorgecastilloprz.github.io.getapet.petdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.squareup.picasso.Picasso
import jorgecastilloprz.github.io.getapet.R
import jorgecastilloprz.github.io.getapet.petgrid.picasso.Tint
import kotlinx.android.synthetic.main.activity_pet_detail.*

class PetDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_detail)
        loadHeaderPicture()
    }

    private fun loadHeaderPicture() {
        Picasso.get()
            .load("https://images.pexels.com/photos/374825/pexels-photo-374825.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500")
            .transform(Tint(1f, ContextCompat.getColor(this, R.color.colorPrimary)))
            .fit()
            .centerCrop()
            .into(details_header)
    }

    companion object {
        const val EXTRA_PET_ID: String = "EXTRA_PET_ID"
    }
}
