Food Tracker
=======

This is an app built using Java and Android Studio for tracking the food you eat and being able to display useful information based on the data.

The brief was:

### MVP
You are required to write an Android app that allows a user to track the food they eat. Users should be able to enter what they eat and when (date/time) and for what meal.


### My app
My app allows the user to record the food they ate, choose the date from a date picker dialog, select the meal from a drop-down list and add a comment if required.

This information is used to create a Java Food object and this is saved to a SQLite database through use of the Android Studio Room library.

The menu allows the user to view the foods they have eaten by day, by meal and by search. A weekly summary showing the number of different types of meals eaten can also be viewed. Individual entires can be deleted and it is possible to clear all entries recorded.

## Deploying the app on an android device
* Change the settings on your phone to allow Developer Options. To do this go to Settings > About phone and tap the Build Number 7 times. A message should pop up to tell you this has worked.
* Connect your phone to your computer
* Open the project in android studio
* Select 'app' from the drop-down menu next to the green triangle
* Click on the green triangle to view the app on your device.
