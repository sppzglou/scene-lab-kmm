package gr.sppzglou.scenelab

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform