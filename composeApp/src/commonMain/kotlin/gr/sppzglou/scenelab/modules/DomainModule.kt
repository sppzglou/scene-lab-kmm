package gr.sppzglou.scenelab.modules

import gr.sppzglou.scenelab.domain.cases.AddRemoveFavoriteUseCase
import gr.sppzglou.scenelab.domain.cases.GetFavoriteMoviesUseCase
import gr.sppzglou.scenelab.domain.cases.GetMovieDetailsUseCase
import gr.sppzglou.scenelab.domain.cases.GetPopularMoviesUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetPopularMoviesUseCase)
    factoryOf(::GetFavoriteMoviesUseCase)
    factoryOf(::GetMovieDetailsUseCase)
    factoryOf(::AddRemoveFavoriteUseCase)
}