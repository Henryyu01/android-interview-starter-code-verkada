Used a LazyVerticalGrid for the main image screen and an AnimatedVisibility for the enlarged image.
Created a view model + repository for managing state. Used MutableStateFlows to store state information
Modified some of the provided UI code to all work with Picture objects

Added some functionality from the video that wasn't described in the doc - the background of the selected image will take on an important color from the image.
Used some code for extracting colours from the Android documentation: https://developer.android.com/develop/ui/views/graphics/palette-colors
Used a fix from stack overflow for working with bitmaps: https://stackoverflow.com/questions/60462841/java-lang-illegalstateexception-unable-to-getpixels-pixel-access-is-not-supp

Thank you!