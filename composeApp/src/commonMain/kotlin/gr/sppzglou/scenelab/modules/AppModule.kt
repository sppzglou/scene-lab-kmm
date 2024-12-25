package gr.sppzglou.scenelab.modules

import gr.sppzglou.composebooster.AppHandler
import org.koin.dsl.module

val appModule = module {
    single<AppHandler> {
        AppHandler()
    }
}