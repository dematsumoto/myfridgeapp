# ANDROID FRIDGE APP #

### What is this repository for? ###

* This is a Android version client to access the [Fridge Webapp](http://fridge-tracker-d.herokuapp.com) API.
* The idea is to not get lost until when your food is still good inside the fridge by adding them with a 'startDate' and for how long they are good. For example, right after you open your milk carton, you set the day you opened it and how many days before it gets expired (based on the information on the carton or your own experience).
* The color indicates the state of the items. Red means it passed the expiration date, yellow means it will expire on the current day or in the next, green means that it is at least 2 days from expiring.
* Check some screenshots from the App: [Main Screen](https://dematsumoto.github.io/images/fridge_app_demo/Home.png), [Options Expanded](https://dematsumoto.github.io/images/fridge_app_demo/Expanded_items.png), [Add Item](https://dematsumoto.github.io/images/fridge_app_demo/New_item.png), [Edit Item](https://dematsumoto.github.io/images/fridge_app_demo/Edit_item.png)


### How do I set up? ###

* The project was developed in Java 1.8 on Android Studio.
* Dependencies: Used Gradle to manage build and dependencies.
* How to run the application: Clone this repository and Run on the desired target hardware or emulator.
* This project is only the client that makes CRUD HTTP requests and display the contents, nothing is stored within the app. The API was also developed by me and you can check its [Repository](https://github.com/dematsumoto/fridge-app). 
