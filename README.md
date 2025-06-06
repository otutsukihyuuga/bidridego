# BidRideGo

BidRideGo is an Android carpooling application that allows users to share rides and split costs.

## Features

- **User Roles:** Supports both drivers and passengers.
- **Ride Bidding:** Passengers can bid on rides, and drivers can accept bids.
- **Trip Management:** Users can view upcoming and past trips.
- **User Authentication:** Secure login and registration functionality.
- **Navigation:** In-app navigation using maps.
- **Payment Integration:** (Optional) Payment processing for ride fares.
- **Real-time Updates:** (Optional) Real-time tracking of rides and bid status.

## Technologies Used

- **Java:** The primary programming language for the application.
- **Android SDK:** For building the native Android app.
- **Firebase:** For user authentication, database, and cloud storage.
- **Google Maps API:** For displaying maps and navigation.
- **Material Design:** For a modern and consistent UI.
- **Gradle:** For dependency management and building the project.

## Getting Started

1.  **Clone the repository:**
```
bash
    git clone https://github.com/otutsukihyuuga/bidridego
    
```
2.  **Open the project in Android Studio:**
    -   File -> Open... and select the project directory.

3.  **Configure Firebase:**
    -   Create a new Firebase project on the Firebase console.
    -   Add an Android app to your Firebase project.
    -   Download the `google-services.json` file and place it in the `app/` directory.
    -   Enable Authentication (Email/Password) and Firestore Database in your Firebase project.

4.  **Build and run the app:**
    -   Click the "Run" button in Android Studio or use the shortcut Shift+F10.
    -   Select a device or emulator to run the app on.

## Project Structure

The project follows a standard Android project structure with the following key modules:

-   **app:** Contains the main application code, including activities, fragments, view models, adapters, and resources.
-   **models:** Defines the data models used in the application (e.g., User, Trip, Bid).
-   **services:** Contains services for interacting with the backend (e.g., Firebase).
-   **ui:** Contains UI-related components, such as custom views and dialogs.
-   **utils:** Provides utility functions for common tasks.
-   **viewadapter:** Implements the adapters for populating data in the UI.
-   **viewholder:** Implements the view holders for the adapters.

## Contributing

Contributions are welcome! Please follow these steps:

1.  Fork the repository.
2.  Create a new branch for your feature or bug fix.
3.  Implement your changes and write tests.
4.  Submit a pull request with a clear description of your changes.