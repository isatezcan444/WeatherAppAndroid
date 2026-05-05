package com.example.weatherapp.ui.features.onboarding.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.weatherapp.ui.features.onboarding.model.OnboardingModel

@Composable
fun OnboardingHeader(
    data: OnboardingModel,
    gradient: Brush? = null
) {
    val titleString = stringResource(id = data.title)

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (data.isTitleGradient && titleString.contains("\n")) {
            val parts = stringResource(data.title).split("\n")

            Text(
                text = parts[0],
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 35.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                ),
                textAlign = TextAlign.Center
            )

            Text(
                text = parts[1],
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 35.sp,
                    fontWeight = FontWeight.SemiBold,
                    brush = gradient
                ),
                textAlign = TextAlign.Center
            )
        } else {
            Text(
                text = stringResource(data.title),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}