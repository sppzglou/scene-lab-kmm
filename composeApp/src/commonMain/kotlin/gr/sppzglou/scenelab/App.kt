package gr.sppzglou.scenelab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import gr.sppzglou.composebooster.AppHandler
import gr.sppzglou.composebooster.InitBackPress
import gr.sppzglou.composebooster.InitSizes
import gr.sppzglou.composebooster.InitSnackBar
import gr.sppzglou.composebooster.InitToast
import gr.sppzglou.composebooster.InitWebDialog
import gr.sppzglou.composebooster.bottomsheet.InitBottomSheet
import gr.sppzglou.scenelab.Clr.BackgroundColor
import gr.sppzglou.scenelab.Clr.ErrorColor
import gr.sppzglou.scenelab.Clr.PrimaryColor
import gr.sppzglou.scenelab.modules.appModule
import gr.sppzglou.scenelab.modules.dataModule
import gr.sppzglou.scenelab.modules.domainModule
import gr.sppzglou.scenelab.modules.presentationModule
import gr.sppzglou.scenelab.presentation.screens.SplashScreen
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import scenelab.composeapp.generated.resources.Res
import scenelab.composeapp.generated.resources.back
import scenelab.composeapp.generated.resources.back_effect


@Composable
fun App() {
    KoinApplication(application = {
        modules(
            appModule,
            dataModule,
            domainModule,
            presentationModule
        )
    }) {
        /**
         * Initialize environment properties and views using my custom library //easyCompose
         * */

        InitSizes {
            InitToast(BackgroundColor, PrimaryColor) {
                val handler = koinInject<AppHandler>()
                val error by handler.error.collectAsStateWithLifecycle()

                InitSnackBar(
                    error = error,
                    txtColor = BackgroundColor,
                    bgColor = ErrorColor,
                    clearError = handler::clearError
                ) {
                    Box(Modifier.fillMaxSize().background(BackgroundColor)) {
                        Navigator(SplashScreen()) {
                            InitBackPress(
                                Res.drawable.back_effect,
                                Res.drawable.back,
                                PrimaryColor,
                                BackgroundColor
                            ) {
                                InitWebDialog(BackgroundColor, PrimaryColor) {
                                    InitBottomSheet {
                                        SlideTransition(it)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}