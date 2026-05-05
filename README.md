# 🌦️ WeatherApp

Modern Android Weather App built with **Kotlin**, **Jetpack Compose**, **Hilt**, and **Clean Architecture**.
This project was built to improve my skills in modern Android development.

---

## 📸 Preview

<img width="360" height="360" alt="weatherapp" src="https://github.com/user-attachments/assets/c6279274-559e-4916-be7c-ab1a46cc147c" />


---

## ✨ Features

* 🌍 Current weather data
* 🌙 Dark / Light / System theme support
* ⚡ Splash Screen (Android 12+ API)
* 🧭 Navigation with Compose
* 💉 Dependency Injection with Hilt
* 💾 Persistent settings (DataStore)

---

## 🛠 Tech Stack

* Kotlin
* Jetpack Compose
* Hilt (DI)
* Navigation Compose
* DataStore
* MVVM Architecture

---

## 🧱 Architecture

```id="0fq9yb"
- ui/
- domain/
- data/
```

---

## 🚀 Getting Started

1. Clone the repo:

```bash id="r8z2kf"
git clone https://github.com/isatezcan444/WeatherAppAndroid.git
```

2. Open in Android Studio

3. Run the project

---

## 📱 Screens

* Home Screen (Weather overview)
* Detail Screen (Detailed weather info)
* Settings Screen (Language & Theme preferences)

---

## 📍 GPS & Location System

* Checks if GPS is enabled
* Handles location permission flow
* Infrastructure ready for location-based weather data

---

## 🌐 Language System

* English
* Turkish
* System Default (follows device language)

---

## 🎨 Theme System

* Light Mode
* Dark Mode
* System Default

---

## 🧠 Architecture Decisions

* Used Clean Architecture to separate layers (ui / domain / data)
* Managed UI state with ViewModel + StateFlow
* Preferred DataStore for async and type-safe storage
* Single Activity architecture with Navigation Compose

---

## ⚡ Notes

* Handled splash screen + theme transition to avoid flicker
* Managed system vs user theme preference with fallback logic
* Stabilized theme switching behavior in Compose

---

## 👨‍💻 Author

İsa Tezcan
