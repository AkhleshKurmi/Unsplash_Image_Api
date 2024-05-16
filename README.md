# Unsplash_Image_Api
Unsplash images api for loading images in a scrollable gridview by using Recycler view with MVVM pattern
# Android Image Grid with Unsplash API

This project demonstrates the use of the Unsplash API in an Android application to display images in a grid view. The images are fetched and displayed with specific dimensions and are cached using LruCache to enhance performance.

## Description

This Android app fetches images from the Unsplash API and displays them in a grid. Each image is fetched to fit exactly into the grid spaces, minimizing bandwidth and ensuring quick load times. The images are center cropped and cached using Android's `LruCache` to reduce API calls and enhance user experience.

## Installation

To run this project, follow these steps:

1. Clone the repository:
   https://github.com/AkhleshKurmi/Unsplash_Image_Api
2. Open the project in Android Studio.
3. Sync Gradle and run the project on an emulator or actual device.

4. Can be download a zip file from: 
   https://drive.google.com/file/d/1GCR3Nv09s2dNNZN_XpwvO0ehuG5KKK8m/view?usp=drive_link

## Usage

Navigate through the app to view images in a grid layout. Scroll to load new images, which are automatically fetched from Unsplash and cached for future viewing.

## Features

- Uses Unsplash API to fetch high-quality images.
- Caches images using `LruCache`.
- Implements grid view without third-party libraries.
- Efficiently handles API requests and optimizes bandwidth usage.

## Contributing

Contributions are welcome! For major changes, please open an issue first to discuss what you would like to change.

## Credits

- Unsplash API for providing a rich source of free images.
- Android documentation for providing guidance on using `LruCache`.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.
