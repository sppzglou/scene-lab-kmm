package gr.sppzglou.scenelab

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import gr.sppzglou.composebooster.collect
import gr.sppzglou.composebooster.rememberDataStore


enum class ThemeMode {
    Dark,
    Light,
    System
}

object Clr {
    const val THEME_MODE = "THEME_MODE"

    private val colorPalette = mapOf(
        C.PrimaryColor to (Color(0xFFFF4C4C) to Color(0xFFFF4C4C)),
        C.BackgroundColor to (Color(0xFFE8E8E8) to Color(0xFF121212)),
        C.SecondaryColor to (Color(0xFFFF9999) to Color(0xFF2C2C2C)),
        C.AccentColor to (Color(0xFFFFFFFF) to Color(0xFFFF6A6A)),
        C.TextColor1 to (Color(0xFF2C2C2C) to Color(0xFFF7F7F7)),
        C.TextColor2 to (Color(0xFF707070) to Color(0xFFB0B0B0)),
        C.ErrorColor to (Color(0xFFFF5252) to Color(0xFFCF6679)),
        C.AcceptColor to (Color(0xFF4CAF50) to Color(0xFF388E3C)),
        C.SheetColor to (Color(0xFFE8E8E8) to Color(0xFF2C2C2C))
    )

    val PrimaryColor: Color
        @Composable
        get() = clr(C.PrimaryColor)

    val BackgroundColor: Color
        @Composable
        get() = clr(C.BackgroundColor)

    val SecondaryColor: Color
        @Composable
        get() = clr(C.SecondaryColor)

    val AccentColor: Color
        @Composable
        get() = clr(C.AccentColor)

    val TextColor1: Color
        @Composable
        get() = clr(C.TextColor1)

    val TextColor2: Color
        @Composable
        get() = clr(C.TextColor2)

    val ErrorColor: Color
        @Composable
        get() = clr(C.ErrorColor)

    val AcceptColor: Color
        @Composable
        get() = clr(C.AcceptColor)

    val SheetColor: Color
        @Composable
        get() = clr(C.SheetColor)


    @Composable
    private fun clr(color: C): Color {
        val dataStore = rememberDataStore()
        val theme by dataStore.collect(THEME_MODE, ThemeMode.System.ordinal) {
            ThemeMode.entries[it]
        }
        return getColorForTheme(color, theme)
    }


    @Composable
    private fun getColorForTheme(color: C, theme: ThemeMode): Color {
        val (lightColor, darkColor) = colorPalette[color] ?: error("Undefined color")
        val isDark = isSystemInDarkTheme()

        return when (theme) {
            ThemeMode.Light -> lightColor
            ThemeMode.Dark -> darkColor
            else -> if (isDark) darkColor else lightColor
        }
    }

    private enum class C {
        PrimaryColor,
        BackgroundColor,
        SecondaryColor,
        AccentColor,
        TextColor1,
        TextColor2,
        ErrorColor,
        AcceptColor,
        SheetColor;
    }
}
