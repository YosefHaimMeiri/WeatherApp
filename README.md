# WeatherApp by Yossi Meiri for One Robotix
#### A Kotlin-based Android Weather app that utilizes MVVM, Retrofit and Gson
https://github.com/YosefHaimMeiri/WeatherApp/assets/62378957/963ba08a-b42f-4482-8e0f-20b281156756
## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/WeatherApp.git
2. Add the file attached to the E-Mail (Includes the API key necessary)

---

## Architecture Overview

The application follows the MVVM (Model-View-ViewModel) architecture pattern:

- **Model:**
  - `DailyWeatherAndForecastResponse`: Represents weather forecast structure.
  - `DailyWeatherResponse`: Defines the structure for daily weather structure.

- **View:**
  - XML layouts for UI components.
  - MainActivity interacts with ViewModel.

- **ViewModel:**
  - `WeatherViewModel`: Manages UI-related data and business logic.
  - Handles API calls using Retrofit and processes JSON responses.

- **Retrofit Integration:**
  - Implements Retrofit for network requests to OpenWeatherMap API.
  - Gson basedJSON parsing.

- **Responsive UI:**
  - Different layout resources for various screen sizes.
  - RecyclerView for displaying the 7-day and hourly forecasts.
