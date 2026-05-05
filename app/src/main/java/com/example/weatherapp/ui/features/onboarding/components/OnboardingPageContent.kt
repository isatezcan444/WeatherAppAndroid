package com.example.weatherapp.ui.features.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.ui.features.onboarding.OnboardingAction
import com.example.weatherapp.ui.features.onboarding.model.OnboardingModel
import com.example.weatherapp.domain.settings.AppThemeType
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun OnboardingPageContent(
    data: OnboardingModel,
    onPrimaryButtonClick: () -> Unit,
    onSecondaryButtonClick: () -> Unit,
    onTertiaryButtonClick: () -> Unit
) {
    val weatherGradient = Brush.linearGradient(
        colors = listOf(Color(0xFF4F7FFA), Color(0xFF335FD1)),
        start = Offset(0f, 0f),
        end = Offset.Infinite
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        if (data.hasTopTitle) {
            OnboardingHeader(
                data = data,
                gradient = weatherGradient
            )
        }

        Image(
            painter = painterResource(id = data.imageRes),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Fit
        )

        if (!data.hasTopTitle) {
            Text(
                text = stringResource(id = data.title),
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 30.sp),
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
        }

        Text(
            text = stringResource(id = data.description),
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            OnboardingButton(
                text = stringResource(id = data.primaryButtonText),
                elevationDp = 4.dp,
                brush = weatherGradient,
                onClick = onPrimaryButtonClick
            )

            data.secondaryButtonText?.let { secondaryStringResId ->
                Spacer(modifier = Modifier.height(12.dp))
                OnboardingButton(
                    text = stringResource(id = secondaryStringResId),
                    colors = { buttonDefaults ->
                        buttonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color(0xFF2960E1)
                        )
                    },
                    onClick = onSecondaryButtonClick
                )
            }
        }

        data.footerText?.let { textResId ->
            val textModifier = if (data.tertiaryAction != null) {
                Modifier.clickable { onTertiaryButtonClick() }
            } else {
                Modifier
            }

            val textStyle = if (data.tertiaryAction != null) {
                MaterialTheme.typography.titleLarge.copy(
                    color = Color(0xFF2960E1)
                )
            } else {
                MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    lineHeight = 24.sp,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }

            Text(
                text = stringResource(id = textResId),
                style = textStyle,
                textAlign = TextAlign.Center,
                modifier = textModifier
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun OnboardingWelcomePreview() {
    WeatherAppTheme {
        val welcomeData = OnboardingModel(
            title = R.string.onboarding_title_1,
            description = R.string.onboarding_desc_1,
            imageRes = R.drawable.onboarding_welcome,
            primaryButtonText = R.string.onboarding_btn_get_started,
            primaryAction = OnboardingAction.NextPage,
            isTitleGradient = true,
            hasTopTitle = true
        )

        Surface {
            OnboardingPageContent(
                data = welcomeData,
                onPrimaryButtonClick = {},
                onSecondaryButtonClick = {},
                onTertiaryButtonClick = {}
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun OnboardingThemePreview() {
    WeatherAppTheme {
        val themeData = OnboardingModel(
            title = R.string.onboarding_title_2,
            description = R.string.onboarding_desc_2,
            imageRes = R.drawable.onboarding_location,
            primaryButtonText = R.string.onboarding_btn_dark_mode,
            primaryAction = OnboardingAction.SelectTheme(AppThemeType.DARK),
            secondaryButtonText = R.string.onboarding_btn_light_mode,
            secondaryAction = OnboardingAction.SelectTheme(AppThemeType.LIGHT),
            footerText = R.string.onboarding_footer_appearance,
            tertiaryAction = OnboardingAction.SelectTheme(AppThemeType.SYSTEM),
            isTitleGradient = false,
            hasTopTitle = false
        )

        Surface {
            OnboardingPageContent(
                data = themeData,
                onPrimaryButtonClick = {},
                onSecondaryButtonClick = {},
                onTertiaryButtonClick = {}
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun OnboardingLocationPreview() {
    WeatherAppTheme {
        val locationData = OnboardingModel(
            title = R.string.onboarding_title_3,
            description = R.string.onboarding_desc_3,
            imageRes = R.drawable.onboarding_location,
            primaryButtonText = R.string.onboarding_btn_use_location,
            primaryAction = OnboardingAction.RequestLocation,
            secondaryButtonText = R.string.onboarding_btn_select_city,
            secondaryAction = OnboardingAction.SelectCityManually,
            footerText = R.string.onboarding_footer_safe_data,
            isTitleGradient = false,
            hasTopTitle = false
        )

        Surface {
            OnboardingPageContent(
                data = locationData,
                onPrimaryButtonClick = {},
                onSecondaryButtonClick = {},
                onTertiaryButtonClick = {}
            )
        }
    }
}