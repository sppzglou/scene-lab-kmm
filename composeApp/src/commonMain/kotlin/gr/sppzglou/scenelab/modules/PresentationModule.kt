package gr.sppzglou.scenelab.modules

import gr.sppzglou.scenelab.presentation.vm.FavoritesListScreenModel
import gr.sppzglou.scenelab.presentation.vm.MovieScreenModel
import gr.sppzglou.scenelab.presentation.vm.MoviesListScreenModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val presentationModule = module {
    factoryOf(::MoviesListScreenModel)
    factoryOf(::FavoritesListScreenModel)
    factoryOf(::MovieScreenModel)
}