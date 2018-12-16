package jorgecastilloprz.github.io.getapet.petgrid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import jorgecastilloprz.github.io.getapet.R
import jorgecastilloprz.github.io.getapet.petgrid.picasso.Tint

/**
 * Adapter used to show an asymmetric grid of products, with 2 items in the first column, and 1
 * item in the second column, and so on.
 */
class StaggeredProductCardRecyclerViewAdapter(
    private val pets: List<PetViewState>?,
    private val onItemSelected: ((PetViewState) -> Unit)? = null
) : RecyclerView.Adapter<StaggeredProductCardRecyclerViewAdapter.StaggeredProductCardViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return position % 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaggeredProductCardViewHolder {
        var layoutId = R.layout.staggered_pet_card_first
        if (viewType == 1) {
            layoutId = R.layout.staggered_pet_card_second
        } else if (viewType == 2) {
            layoutId = R.layout.staggered_pet_card_third
        }

        val layoutView = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return StaggeredProductCardViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: StaggeredProductCardViewHolder, position: Int) {
        if (pets != null && position < pets.size) {
            val pet = pets[position]
            holder.productTitle.text = "${pet.species} · ${pet.breed}"
            holder.productPrice.text = pet.name
            Picasso.get()
                .load(pet.url)
                .fit()
                .centerCrop()
                .transform(Tint(1f, ContextCompat.getColor(holder.itemView.context, R.color.colorPrimary)))
                .into(holder.productImage)
            
            holder.card.setOnClickListener { onItemSelected?.invoke(pet) }
            holder.productImage.setOnClickListener { onItemSelected?.invoke(pet) }
        }
    }

    override fun getItemCount(): Int {
        return pets?.size ?: 0
    }

    class StaggeredProductCardViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        var card: View = itemView.findViewById(R.id.card)
        var productImage: ImageView = itemView.findViewById(R.id.product_image)
        var productTitle: TextView = itemView.findViewById(R.id.product_title)
        var productPrice: TextView = itemView.findViewById(R.id.product_price)
    }
}
