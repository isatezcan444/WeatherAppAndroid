package com.example.weatherapp.ui.features.onboarding.screen

import android.Manifest
import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.weatherapp.ui.features.home.HomeViewModel
import com.example.weatherapp.ui.features.onboarding.OnboardingAction
import com.example.weatherapp.ui.features.onboarding.OnboardingViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel,
    onFinished: (OnboardingAction) -> Unit
) {
    val pages by viewModel.pages.collectAsState()
    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()

    val gpsLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            homeViewModel.loadWeatherWithLocationTracker()
            viewModel.saveCompleteOnboardingStatus()
            onFinished(OnboardingAction.RequestLocation)
        }
    }

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions.values.any { it }) {
            viewModel.checkDeviceLocationSettings(
                onAllowed = {
                    homeViewModel.loadWeatherWithLocationTracker()
                    viewModel.saveCompleteOnboardingStatus()
                    onFinished(OnboardingAction.RequestLocation)
                },
                onResolutionRequired = { exception ->
                    val intentSenderRequest = IntentSenderRequest.Builder(exception.resolution).build()
                    gpsLauncher.launch(intentSenderRequest)
                }
            )
        }
    }

    OnboardingContent(
        pages = pages,
        pagerState = pagerState,
        onPrimaryClick = { index ->
            handleAction(
                action = pages[index].primaryAction,
                pagerState = pagerState,
                scope = scope,
                onLocationRequest = {
                    locationPermissionLauncher.launch(
                        arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                    )
                },
                onFinished = { resultAction ->
                    when (resultAction) {
                        is OnboardingAction.SelectTheme -> viewModel.saveSelectedTheme(resultAction.themeType)
                        is OnboardingAction.FinishOnboarding -> {}
                        is OnboardingAction.SelectCityManually -> {}
                        else -> {}
                    }
                    onFinished(resultAction)
                }
            )
        },
        onSecondaryClick = { index ->
            pages[index].secondaryAction?.let { action ->
                handleAction(
                    action = action,
                    pagerState = pagerState,
                    scope = scope,
                    onFinished = { resultAction ->
                        when (resultAction) {
                            is OnboardingAction.SelectTheme -> viewModel.saveSelectedTheme(resultAction.themeType)
                            is OnboardingAction.FinishOnboarding -> {}
                            is OnboardingAction.SelectCityManually -> {}
                            else -> {}
                        }
                        onFinished(resultAction)
                    }
                )
            }
        },
        onTertiaryClick = { index ->
            pages[index].tertiaryAction?.let { action ->
                handleAction(
                    action = action,
                    pagerState = pagerState,
                    scope = scope,
                    onFinished = { resultAction ->
                        if (resultAction is OnboardingAction.SelectTheme) {
                            viewModel.saveSelectedTheme(resultAction.themeType)
                        }
                        onFinished(resultAction)
                    }
                )
            }
        }
    )
}

private fun handleAction(
    action: OnboardingAction,
    pagerState: PagerState,
    scope: CoroutineScope,
    onLocationRequest: () -> Unit = {},
    onFinished: (OnboardingAction) -> Unit
) {
    when (action) {
        is OnboardingAction.NextPage -> {
            scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
        }

        is OnboardingAction.SelectTheme -> {
            scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
            onFinished(action)
        }

        is OnboardingAction.RequestLocation -> {
            onLocationRequest()
        }

        is OnboardingAction.SelectCityManually -> {
            onFinished(action)
        }

        is OnboardingAction.FinishOnboarding -> {
            onFinished(action)
        }
    }
}