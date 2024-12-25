package gr.sppzglou.scenelab.domain.cases

import gr.sppzglou.scenelab.domain.Repository

class AddRemoveFavoriteUseCase(
    private val repo: Repository
) {
    suspend operator fun invoke(id: Int, isFav: Boolean) {
        if (isFav) repo.addFavorite(id)
        else repo.removeFavorite(id)
    }
}