package jorgecastilloprz.github.io.getapet.petdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import jorgecastilloprz.github.io.getapet.R

class PetDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_detail)
    }

    companion object {
        const val EXTRA_PET_ID: String = "EXTRA_PET_ID"
    }
}
