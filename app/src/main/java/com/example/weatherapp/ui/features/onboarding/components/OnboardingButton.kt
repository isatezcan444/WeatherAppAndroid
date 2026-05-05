package com.example.weatherapp.ui.features.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun OnboardingButton(
    text: String,
    icon: ImageVector? = null,
    elevationDp: Dp = 0.dp,
    brush: Brush? = null,
    colors: @Composable (ButtonDefaults) -> ButtonColors = { it.buttonColors() },
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        colors = colors(ButtonDefaults),
        contentPadding = PaddingValues(0.dp),
        shape = MaterialTheme.shapes.large,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = elevationDp
        ),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .then(
                    if (brush != null) Modifier.background(brush) else Modifier
                )
                .padding(vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(6.dp)
            ) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(
                    modifier = Modifier.width(5.dp)
                )

                if (icon != null){
                    Icon(
                        imageVector = icon,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingButtonPreview() {
    val weatherGradient = Brush.linearGradient(
        colors = listOf(Color(0xFF4F7FFA), Color(0xFF335FD1)),
        start = Offset(0f, 0f),
        end = Offset.Infinite
    )

    WeatherAppTheme {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            OnboardingButton(
                text = stringResource(R.string.onboarding_btn_get_started),
                icon = Icons.Default.ArrowForward,
                elevationDp = 4.dp,
                brush = weatherGradient,
                onClick = {}
            )
        }
    }
}