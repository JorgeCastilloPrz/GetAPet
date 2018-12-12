package jorgecastilloprz.github.io.getapet

import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import jorgecastilloprz.github.io.getapet.listener.NavigationIconClickListener
import jorgecastilloprz.github.io.getapet.petgrid.PetGridItemDecoration
import jorgecastilloprz.github.io.getapet.petgrid.PetViewState
import jorgecastilloprz.github.io.getapet.petgrid.StaggeredProductCardRecyclerViewAdapter
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
        supportActionBar?.setDisplayShowTitleEnabled(false)
        setupBackdrop()
    }

    private fun setupBackdrop() {
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

    private fun setupPetGrid() {
        petGrid.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position % 3 == 2) 2 else 1
            }
        }
        petGrid.layoutManager = gridLayoutManager
        val adapter = StaggeredProductCardRecyclerViewAdapter(mockPets())
        petGrid.adapter = adapter
        val largePadding = resources.getDimensionPixelSize(R.dimen.shr_staggered_product_grid_spacing_large)
        val smallPadding = resources.getDimensionPixelSize(R.dimen.shr_staggered_product_grid_spacing_small)
        petGrid.addItemDecoration(PetGridItemDecoration(largePadding, smallPadding))
    }

    private fun mockPets(): List<PetViewState> = listOf(
        PetViewState("Lidel", 99.0, "https://www.terranovacnc.com/wp-content/uploads/2017/07/brot-350x350.jpg"),
        PetViewState("Lidel", 99.0, "https://images.pexels.com/photos/1249208/pexels-photo-1249208.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
        PetViewState("Lidel", 99.0, "https://images.pexels.com/photos/374825/pexels-photo-374825.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
        PetViewState("Lidel", 99.0, "https://images.pexels.com/photos/96428/pexels-photo-96428.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
        PetViewState("Lidel", 99.0, "https://images.pexels.com/photos/69371/pexels-photo-69371.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
        PetViewState("Lidel", 99.0, "https://images.pexels.com/photos/38008/pexels-photo-38008.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
        PetViewState("Lidel", 99.0, "https://images.pexels.com/photos/161462/pets-kids-dogs-animal-161462.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
        PetViewState("Lidel", 99.0, "https://images.pexels.com/photos/4933/lake-animals-dogs-pets.jpg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
        PetViewState("Lidel", 99.0, "https://images.pexels.com/photos/1483185/pexels-photo-1483185.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
        PetViewState("Lidel", 99.0, "https://images.pexels.com/photos/160846/french-bulldog-summer-smile-joy-160846.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
        PetViewState("Lidel", 99.0, "https://images.pexels.com/photos/723130/pexels-photo-723130.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
        PetViewState("Lidel", 99.0, "https://images.pexels.com/photos/326875/pexels-photo-326875.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500")
    )
}
