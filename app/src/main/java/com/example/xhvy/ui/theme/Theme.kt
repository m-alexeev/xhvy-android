package com.example.xhvy.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Blue800,
    onPrimary = Slate200,
    primaryContainer = Blue500.copy(alpha = .1f),
    onPrimaryContainer = Blue800,
    inversePrimary = Blue200,
    secondary = Teal900,
    onSecondary = Slate200,
    secondaryContainer = Teal500.copy(alpha = 0.1f),
    onSecondaryContainer = Teal900,
    tertiary = Fuchsia900,
    onTertiary = Slate200,
    tertiaryContainer = Fuchsia500.copy(alpha = 0.1f),
    onTertiaryContainer = Fuchsia900,
    surface = Gray800,
    onSurface = Slate200,
    onBackground = Slate200,
    background = Gray900,
    outline = Gray500,
    surfaceVariant = Gray700,
    onSurfaceVariant = Slate200,
    surfaceTint = Blue800,
    inverseSurface = Gray900,
    inverseOnSurface = Color.White,
    error = Red900,
    onError = Color.White,
    errorContainer = Red500.copy(alpha = 0.1f),
    onErrorContainer = Red900,
    outlineVariant = Gray700,
    surfaceBright = Gray700,
    surfaceContainer = Gray800,
    surfaceContainerHigh = Gray700,
    surfaceContainerHighest = Gray600,
    surfaceContainerLow = Gray900,
    surfaceContainerLowest = Gray950,
    surfaceDim = Gray900
)

private val LightColorScheme = lightColorScheme(
    primary = Blue400,
    onPrimary = Color.White,
    primaryContainer = Blue200,
    onPrimaryContainer = Gray800,
    inversePrimary = Orange500,
    secondary = Green500,
    onSecondary = Color.White,
    secondaryContainer = Green200,
    onSecondaryContainer = Gray800,
    tertiary = Purple500,
    onTertiary = Color.White,
    tertiaryContainer = Purple200,
    onTertiaryContainer = Gray800,
    background = Gray50,
    onBackground = Gray800,
    surface = Gray100,
    onSurface = Gray800,
    surfaceVariant = Gray200,
    onSurfaceVariant = Gray700,
    surfaceTint = Blue500, // Assuming primary is Tailwind Blue500
    inverseSurface = Gray900,
    inverseOnSurface = Color.White,
    error = Red500,
    onError = Color.White,
    errorContainer = Red200,
    onErrorContainer = Gray800,
    outline = Gray400,
    outlineVariant = Gray300,
    surfaceBright = Gray50,
    surfaceContainer = Gray100,
    surfaceContainerHigh = Gray200,
    surfaceContainerHighest = Gray300,
    surfaceContainerLow = Gray50,
    surfaceContainerLowest = Gray100,
    surfaceDim = Gray200
)

@Composable
fun XhvyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
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