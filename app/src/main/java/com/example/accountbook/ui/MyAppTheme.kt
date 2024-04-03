package com.example.accountbook.ui

import androidx.compose.material.Colors
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import com.example.accountbook.common.config.Config

/**
 * @author: YanMinng
 * @date: 2024/3/26
 * @description: 主题管理
 */
object MyAppThemeData {
    const val LIGHT_MODE = 0
    const val DARK_MODE = 1
    const val SYSTEM_MODE = 2
    const val AUTO_MODE = 3
    const val NIGHT_MODE = 4
    const val DAY_MODE = 5
    const val AUTO_NIGHT_MODE = 6
    val themeList = arrayListOf<Int>(
        LIGHT_MODE,
        DARK_MODE,
        SYSTEM_MODE,
        AUTO_MODE,
        NIGHT_MODE,
        DAY_MODE,
        AUTO_NIGHT_MODE
    )
}

// 定义亮色主题
private val LightColorPalette = lightColors(
    primary = Color.Green,
    secondary = Color.Green
)

// 定义暗色主题
private val DarkColorPalette = darkColors(
    primary = Color(0xFF03DAC5),
    secondary = Color(0xFF03DAC5),
)

fun getThemePalette(theme: Int = Config.Theme.MyAppTheme.mutableStateData.value): Colors {
    return when (theme) {
        MyAppThemeData.LIGHT_MODE -> LightColorPalette
        MyAppThemeData.DARK_MODE -> DarkColorPalette
        else -> LightColorPalette
    }
}

@Composable
fun MyAppTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalContentColor provides getThemePalette().onSurface) {
        MaterialTheme(
            colors = getThemePalette(),
            content = content
        )
    }
}