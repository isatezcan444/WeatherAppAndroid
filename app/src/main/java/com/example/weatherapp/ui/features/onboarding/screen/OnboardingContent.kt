package com.example.weatherapp.ui.features.onboarding.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.R
import com.example.weatherapp.ui.features.onboarding.OnboardingAction
import com.example.weatherapp.ui.features.onboarding.components.OnboardingPageContent
import com.example.weatherapp.ui.features.onboarding.model.OnboardingModel
import com.example.weatherapp.ui.theme.WeatherAppTheme
import kotlinx.coroutines.launch

@Composable
fun OnboardingContent(
    pages: List<OnboardingModel>,
    pagerState: PagerState,
    onPrimaryClick: (Int) -> Unit,
    onSecondaryClick: (Int) -> Unit,
    onTertiaryClick: (Int) -> Unit
) {
    val scope = rememberCoroutineScope()
    BackHandler(
        enabled = pagerState.currentPage > 0
    ) {
        scope.launch {
            pagerState.animateScrollToPage(pagerState.currentPage - 1)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            HorizontalPager(
                verticalAlignment = Alignment.CenterVertically,
                state = pagerState,
                userScrollEnabled = false,
                modifier = Modifier.weight(1f)
            ) { position ->
                OnboardingPageContent(
                    data = pages[position],
                    onPrimaryButtonClick = { onPrimaryClick(position) },
                    onSecondaryButtonClick = { onSecondaryClick(position) },
                    onTertiaryButtonClick = { onTertiaryClick(position) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingContentPreview() {
    WeatherAppTheme {
        val mockPages = listOf(
            OnboardingModel(
                title = R.string.onboarding_title_1,
                description = R.string.onboarding_desc_1,
                imageRes = R.drawable.onboarding_welcome,
                primaryButtonText = R.string.onboarding_btn_get_started,
                primaryAction = OnboardingAction.NextPage
            )
        )
        OnboardingContent(
            pages = mockPages,
            pagerState = rememberPagerState { mockPages.size },
            onPrimaryClick = {},
            onSecondaryClick = {},
            onTertiaryClick = {}
        )
    }
}