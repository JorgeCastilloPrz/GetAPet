package jorgecastilloprz.github.io.getapet.petgrid

data class PetViewState(
    val petId: String,
    val name: String,
    val species: String,
    val breed: String,
    val url: String,
    var darkImage: Boolean = false
)
