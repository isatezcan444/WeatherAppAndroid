package com.example.weatherapp.ui.common.components.indicators

import androidx.annotation.FloatRange
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun CircularIndicator(
    @FloatRange(from = 0.0, to = 1.0) progress: Float,
    valueText: String,
    modifier: Modifier = Modifier,
    activeColor: Color = Color(0xFF43A047),
    inactiveColor: Color = Color(0xFFE0E0E0),
    strokeWidth: Dp = 5.dp,
    startAngle: Float = 120f,
    sweepAngle: Float = 300f
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val strokeStyle = Stroke(
                width = strokeWidth.toPx(),
                cap = StrokeCap.Round
            )

            drawArc(
                color = inactiveColor,
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = strokeStyle
            )

            val currentSweepAngle = sweepAngle * progress
            drawArc(
                color = activeColor,
                startAngle = startAngle,
                sweepAngle = currentSweepAngle,
                useCenter = false,
                style = strokeStyle
            )
        }

        Text(
            text = valueText,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = activeColor
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CircularIndicatorPreview() {
    WeatherAppTheme {
        CircularIndicator(
            progress = 0.7f,
            valueText = "70",
            modifier = Modifier.size(70.dp)
        )
    }
}