package com.example.weatherapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R

val SfProDisplay = FontFamily(
    // Regular & Medium & Bold
    Font(resId = R.font.sf_pro_display_regular, weight = FontWeight.Normal),
    Font(resId = R.font.sf_pro_display_medium, weight = FontWeight.Medium),
    Font(resId = R.font.sf_pro_display_bold, weight = FontWeight.Bold),

    // Light & Thin
    Font(resId = R.font.sf_pro_display_light_italic, weight = FontWeight.Light, style = FontStyle.Italic),
    Font(resId = R.font.sf_pro_display_thin_italic, weight = FontWeight.Thin, style = FontStyle.Italic),
    Font(resId = R.font.sf_pro_display_ultra_light_italic, weight = FontWeight.ExtraLight, style = FontStyle.Italic),

    // Heavy & Black
    Font(resId = R.font.sf_pro_display_heavy_italic, weight = FontWeight.ExtraBold, style = FontStyle.Italic),
    Font(resId = R.font.sf_pro_display_black_italic, weight = FontWeight.Black, style = FontStyle.Italic),

    // SemiBold
    Font(resId = R.font.sf_pro_display_semi_bold_italic, weight = FontWeight.SemiBold, style = FontStyle.Italic)
)

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = SfProDisplay,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = SfProDisplay,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = 0.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = SfProDisplay,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = SfProDisplay,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.5.sp
    ),
    bodySmall = TextStyle(
        fontFamily = SfProDisplay,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = SfProDisplay,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        letterSpacing = 0.5.sp
    )
)