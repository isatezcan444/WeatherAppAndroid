package com.example.weatherapp.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.ui.features.details.screen.DetailsScreen
import com.example.weatherapp.ui.features.details.DetailsViewModel
import com.example.weatherapp.ui.features.home.screen.HomeScreen
import com.example.weatherapp.ui.features.home.HomeViewModel
import com.example.weatherapp.ui.features.onboarding.OnboardingAction
import com.example.weatherapp.ui.features.onboarding.screen.OnboardingScreen
import com.example.weatherapp.ui.features.onboarding.OnboardingViewModel
import com.example.weatherapp.ui.features.searchlocation.screen.SearchLocationScreen
import com.example.weatherapp.ui.features.settings.screen.SettingsScreen
import com.example.weatherapp.ui.features.settings.SettingsViewModel

@Composable
fun NavGraph(
    isDarkTheme: Boolean
) {
    val navController = rememberNavController()
    val onboardingViewModel: OnboardingViewModel = hiltViewModel()
    val startDestination = onboardingViewModel.startDestination ?: return
    val homeViewModel: HomeViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Routes.OnboardingScreen> {
            OnboardingScreen(
                homeViewModel = homeViewModel,
                onFinished = { action ->
                    when (action) {
                        is OnboardingAction.RequestLocation,
                        is OnboardingAction.SelectCityManually -> {
                            navController.navigate(Routes.SearchLocationScreen) {
                                popUpTo(Routes.SearchLocationScreen) {
                                    inclusive = true
                                }
                            }
                        }

                        is OnboardingAction.FinishOnboarding -> {
                            navController.navigate(Routes.HomeScreen) {
                                popUpTo(Routes.OnboardingScreen) { inclusive = true }
                            }
                        }

                        else -> {}
                    }
                }
            )
        }

        composable<Routes.SearchLocationScreen>(
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(250)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(250)
                )
            }
        ) {
            SearchLocationScreen(
                homeViewModel = homeViewModel,
                onConfirmClick = {
                    navController.navigate(Routes.HomeScreen) {
                        popUpTo(Routes.SearchLocationScreen) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable<Routes.HomeScreen> {
            HomeScreen(
                viewModel = homeViewModel,
                onShowDetailsClick = { locationName, selectedDate ->
                    navController.navigate(
                        route = Routes.DetailsScreen(
                            locationName = locationName,
                            date = selectedDate
                        )
                    )
                },
                onDayDetailsClick = { locationName, selectedDate ->
                    navController.navigate(
                        route = Routes.DetailsScreen(
                            locationName = locationName,
                            date = selectedDate
                        )
                    )
                },
                onSettingsClick = {
                    navController.navigate(
                        route = Routes.SettingsScreen
                    )
                }
            )

        }

        composable<Routes.DetailsScreen> {
            val detailsViewModel: DetailsViewModel = hiltViewModel()
            DetailsScreen(
                viewModel = detailsViewModel,
                onBackClick = {
                    navController.navigateUp()
                },
                isDarkTheme = isDarkTheme
            )
        }

        composable<Routes.SettingsScreen> {
            val settingsViewModel: SettingsViewModel = hiltViewModel()
            SettingsScreen(
                viewModel = settingsViewModel,
                onBackClick = {
                    navController.navigateUp()
                }
            )
        }
    }
}