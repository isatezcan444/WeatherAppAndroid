package com.example.weatherapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color.White,
    secondary = SoftSurfaceBlue,
    tertiary = Black,
    onPrimary = VeryLightGray,
    onSecondary = LightGray,
    onSurface = Color.White,
    onBackground = Color(0xFF201E21),
    background = DeepNightBlue,

    //Summary Card
    errorContainer = Color(0xFF3B2525),
    onErrorContainer = CardAlertContent,

    primaryContainer = Color(0xFF1B2633),
    onPrimaryContainer = CardInfoContent,

    secondaryContainer = Color(0xFF2E2D1F),
    onSecondaryContainer = CardWarningContent,

    inverseSurface = Color(0xFF1E2B20),
    inverseOnSurface = CardSuccessContent
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF201C1C),
    secondary = VeryLightGray,
    tertiary = Black,
    onPrimary = VeryLightGray,
    onSecondary = DarkGray,
    onSurface = NavyBlue,
    onBackground = Color.White,
    background = Color.White,

    //Summary Card
    errorContainer = CardAlertBackground,
    onErrorContainer = CardAlertContent,

    primaryContainer = CardInfoBackground,
    onPrimaryContainer = CardInfoContent,

    secondaryContainer = CardWarningBackground,
    onSecondaryContainer = CardWarningContent,

    inverseSurface = CardSuccessBackground,
    inverseOnSurface = CardSuccessContent
)

@Composable
fun WeatherAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            val insetsController = WindowCompat.getInsetsController(window, view)

            insetsController.isAppearanceLightStatusBars = !darkTheme
            insetsController.isAppearanceLightNavigationBars = !darkTheme
        }
    }

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}