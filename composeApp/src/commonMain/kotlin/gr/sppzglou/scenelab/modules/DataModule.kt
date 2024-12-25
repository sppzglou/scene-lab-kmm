package gr.sppzglou.scenelab.modules

import gr.sppzglou.composebooster.log
import gr.sppzglou.scenelab.ApiKey
import gr.sppzglou.scenelab.ApiUrl
import gr.sppzglou.scenelab.data.RepositoryImpl
import gr.sppzglou.scenelab.data.local.AppDatabase
import gr.sppzglou.scenelab.data.local.LocalDataSource
import gr.sppzglou.scenelab.data.local.appDatabase
import gr.sppzglou.scenelab.data.remote.Api
import gr.sppzglou.scenelab.data.remote.RemoteDataSource
import gr.sppzglou.scenelab.domain.Repository
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    single<AppDatabase> { appDatabase() }
    singleOf(::Api)
    singleOf(::LocalDataSource)
    singleOf(::RemoteDataSource)
    singleOf(::RepositoryImpl) { bind<Repository>() }
}

class Client {
    fun create(defaultUrl: Boolean = true) = HttpClient {
        installUrl(defaultUrl)
        installJson()
        installLogs()
        install(HttpTimeout) {
            requestTimeoutMillis = 15_000
        }
    }
}


fun HttpClientConfig<*>.installUrl(defaultUrl: Boolean) {
    defaultRequest {
        header("Content-Type", "application/json")
        if (defaultUrl) {
            url(ApiUrl)
            url {
                parameters.append("api_key", ApiKey)
            }
        }
    }
}

fun HttpClientConfig<*>.installJson() {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
            explicitNulls = false
        })
    }
}

fun HttpClientConfig<*>.installLogs() {
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
        logger = object : Logger {
            override fun log(message: String) {
                log("KtorClient", message)
            }
        }
    }
}