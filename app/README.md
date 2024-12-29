# Crop Prediction App

## Overview
The **Crop Prediction App** is a mobile application designed to assist farmers and agricultural enthusiasts in determining the most suitable crop to grow based on environmental and soil conditions. This app integrates a machine learning model to provide accurate crop predictions.

## Features
- **Crop Suitability Prediction**: Predicts the best crop to grow based on user input.
- **Interactive UI**: A user-friendly Android interface for seamless interaction.
- **Machine Learning Integration**: Utilizes a trained machine learning model for predictions.
- **API Backend**: Flask-based API with CORS support for secure communication.

## Architecture
1. **Machine Learning Model**: The core of the prediction system, trained on agricultural datasets.
2. **Flask API**: Hosts the ML model, processes user input, and returns predictions.
3. **Android App**: Acts as the front-end, sending requests to the Flask API and displaying results.

## Technologies Used
- **Frontend**: Android (Kotlin/Java)
- **Backend**: Flask, CORS
- **Machine Learning**: Python libraries like Scikit-learn, Pandas, NumPy
- **Database**: CSV/Database for training the ML model
- **Hosting**: Flask server hosted locally or on a cloud platform

## Installation and Setup

### Prerequisites
- Python 3.7+
- Android Studio
- Flask and required Python libraries (`flask`, `flask-cors`, `scikit-learn`, `pandas`, `numpy`)

### Backend Setup
1. Clone the repository:
   ```bash
   git clone <https://github.com/shaurya-afk/Crop-Predictor.git>
   cd crop-prediction-backend
   ```
2. Install dependencies:
   ```bash
   pip install -r requirements.txt
   ```
3. Start the Flask server:
   ```bash
   python app.py
   ```
   ```bash
   flask run --host=0.0.0.0 --port=5000
   ```

### Android App Setup
1. Clone the Android app repository:
   ```bash
   git clone <https://github.com/shaurya-afk/Crop-Predictor.git>
   cd croppredictor
   ```
2. Open the project in Android Studio.
3. Update the API URL in the app configuration file.
4. Build and run the app on an emulator or physical device.

## Usage
1. Open the Crop Prediction App on your Android device.
2. Enter the required input parameters such as soil type, temperature, humidity, etc.
3. Submit the data to get the predicted crop recommendation.

## Contributing
Contributions are welcome! Please follow the steps below:
1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Submit a pull request with a detailed description of your changes.

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.

## Contact
For queries or support, please contact:
- **Developer**: Shaurya Sharma
- **Email**: [shauryakumarsharma0007@gmail.com]
- **LinkedIn**: [https://www.linkedin.com/in/shaurya-afk/]

---
Thank you for using the Crop Prediction App!

