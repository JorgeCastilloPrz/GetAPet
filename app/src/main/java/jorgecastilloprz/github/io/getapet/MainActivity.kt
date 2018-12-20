package jorgecastilloprz.github.io.getapet

import android.content.Intent
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import jorgecastilloprz.github.io.getapet.listener.NavigationIconClickListener
import jorgecastilloprz.github.io.getapet.petdetail.PetDetailActivity
import jorgecastilloprz.github.io.getapet.petdetail.PetDetailActivity.Companion.EXTRA_PET_ID
import jorgecastilloprz.github.io.getapet.petdetail.PetDetailActivity.Companion.EXTRA_PET_IS_DARK
import jorgecastilloprz.github.io.getapet.petdetail.PetDetailActivity.Companion.EXTRA_PET_PICTURE_URL
import jorgecastilloprz.github.io.getapet.petgrid.PetGridItemDecoration
import jorgecastilloprz.github.io.getapet.petgrid.PetViewState
import jorgecastilloprz.github.io.getapet.petgrid.StaggeredProductAdapter
import jorgecastilloprz.github.io.getapet.petgrid.generateMockPets
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.backdrop_menu.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBar()
        setupContentBackground()
        setupPetGrid()
    }

    private fun setupActionBar() {
        setSupportActionBar(toolbar)
        setupBackdrop()
    }

    private fun setupBackdrop() {
        toolbar.setNavigationOnClickListener(
            NavigationIconClickListener(
                toolbar = toolbar,
                sheet = content,
                sheetOverlay = contentSheetOverlay,
                backdropMenu = backdropMenu,
                interpolator = AccelerateDecelerateInterpolator(),
                openIcon = getDrawable(R.drawable.ic_drawer),
                closeIcon = getDrawable(R.drawable.shr_close_menu)
            )
        )
    }

    private fun setupContentBackground() {
        content.background = getDrawable(R.drawable.bg_backdrop)
    }

    private fun setupPetGrid() {
        petGrid.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position % 3 == 2) 2 else 1
            }
        }
        petGrid.layoutManager = gridLayoutManager
        val adapter = StaggeredProductAdapter(generateMockPets(), onGridItemSelected(), onFavItemClick())
        petGrid.adapter = adapter
        val largePadding = resources.getDimensionPixelSize(R.dimen.shr_staggered_product_grid_spacing_large)
        val smallPadding = resources.getDimensionPixelSize(R.dimen.shr_staggered_product_grid_spacing_small)
        petGrid.addItemDecoration(PetGridItemDecoration(largePadding, smallPadding))
    }

    private fun onGridItemSelected(): ((PetViewState) -> Unit) = { petState ->
        val intent = Intent(this, PetDetailActivity::class.java)
        intent.putExtra(EXTRA_PET_ID, petState.petId)
        intent.putExtra(EXTRA_PET_PICTURE_URL, petState.url)
        intent.putExtra(EXTRA_PET_IS_DARK, petState.darkImage)
        startActivity(intent)
    }

    private fun onFavItemClick(): ((PetViewState) -> Unit) = { petState ->
    }
}
