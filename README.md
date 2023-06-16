# TAMARA Android

The documentations of 'TAMARA' Applications in Bangkit Academy 2023


![Cover - TAMARA](https://github.com/Tamara-Capstone/tamara_android/assets/26517220/e9db4b5c-2272-41ae-bdc1-24e312e5e2fd)


## About TAMARA

TAMARA (Tani Maju Sejahtera) is a plant disease detection application. It also can predict weather for next 3 days based on user's current position using longitude and latitude from the GPS, and have a community to discuss about plant, plant disease, how to cure them, and other related things about plant, gardening, and farming.


## UI

![UI - TAMARA](https://github.com/Tamara-Capstone/tamara_android/assets/26517220/928aff4d-d3b8-48bd-9f96-a8816a6bf5da)

We design the UI/UX of the app in Figma. [TAMARA UI/UX Design Figma](https://www.figma.com/file/fKPRJcitIsibZ2vfqNxkEz/TAMARA-UI?type=design&node-id=534%3A45&t=Tt62GTPCRKbuPfw6-1)


## About the Android Project

This application is implementing MVVM architecture pattern where the view (activity/fragment) observe live data from the view model. While the data that observed in view model is obtained from data source layer (model and repository). Then in this project data source is the only layer that connecting response API to presentation layer (view and view model) so it can be viewed by the users.


## Feature

- Scan plant disease
- Weather Forcast
- Community


## Tech Stack

- Kotlin programming language
- Retrofit2
- Circle ImageView
- Google MAPS API


## REST API

[TAMARA RestAPI](https://github.com/Tamara-Capstone/tamara-backend)


## Project Instalation

1. Clone the repository


    ```bash
    git clone https://github.com/Tamara-Capstone/tamara_android.git
    cd tandur-android
    ```

2. Run the app in Android Studio from emulator or physical device.



