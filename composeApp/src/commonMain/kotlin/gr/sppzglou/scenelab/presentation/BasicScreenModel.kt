package gr.sppzglou.scenelab.presentation

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import gr.sppzglou.composebooster.AppHandler
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BasicScreenModel : ScreenModel, KoinComponent {
    val handler: AppHandler by inject()


    fun launch(loader: Boolean = false, block: suspend () -> Unit) {
        screenModelScope.launch {
            handler.request(loader, block = block)
        }
    }

    fun <T> modelFlow(loader: Boolean = true, block: suspend () -> T) = flow {
        handler.request(loader) {
            emit(block())
        }
    }

    fun <T> modelFlow(init: T, loader: Boolean = true, block: suspend () -> T) = flow {
        handler.request(loader) {
            emit(block())
        }
    }.stateIn(
        screenModelScope,
        SharingStarted.WhileSubscribed(5000),
        init
    )
}