# Master Detail Application

## Description

This application displays a list of items obtained from a iTunes Search API and shows a detailed view of each item. This master-detail application contains at least `one` dependency.

## User Stories

- [x] The list should show the following details from the API: 
    - [x] Track Name
    - [x] Artwork 
    - [x] Price
    - [x] Genre
- [x] The detail view shows Long Description for the given item.
- [x] Each row should have a placeholder image if for some reason the URL is unable to be loaded. Images must not be duplicated when scrolling. 
- [x] The app should demonstrate the ability to save data and reuse it when the user opens the app again.
- [x] Demonstrate the ability to save data and reuse it when the user opens the app again. 
    * The items persisted are the following:
        * Track ID
        * Collection ID
        * Track Name
        * Artwork URLs
        * Track Rental Price
        * Collection HD Price
        * Track HD Price
        * Track HD Rental Price
        * Currency
        * Country
        * Genre
        * Kind
        * Release Date
        * Track Time
        * Collection Explicitness
        * Track Explicitness
        * Short Description
        * Long Description

*Not all of these items are used but are still being persisted for further enhancements of the app.*

### Suggestions:
- [x] A date when the user previously visited, shown in the list header.
- [ ] Save the last screen that the user was on. When the app restores, it should present the user with that screen. 
    

## Architecture
    
The design pattern use in this application are the following:
* **MVVM** - Model - View - ViewModel
* **Clean Architecture**

These two architecture patters are use because these are easy to maintain. These patterns also reduce boiler plate. Additionally, these patterns take advantage in using `Kotlin dependency injection (KODEIN)` which is a recent development in Android technology. 


## UI and Design

The UI design of this application is based on master-detail flow template in the Android Studio. Thus, the application shows a different view for a mobile phone and for a tablet. 

<br>

>The **mobile phone view** only shows a list movies in which details of each movie is displayed whenever a movie on the list is clicked. The list includes the name of the movie, the genre and the price. The details of the movie shows the description of the movie.

![](https://i.imgur.com/71z9EwD.png =250x420) ![](https://i.imgur.com/iEIutDq.png =250x420)

<br>

>The **tablet view** shows a list of movies on the left side of the screen and details of the selected movie on the right side of the screen. The left side includes the name of the movie, the genre and the price.

![](https://i.imgur.com/CfBYw9R.png)

<br>

## Video Walkthrough

![](https://i.imgur.com/sbmxa07.gif =210x420) 

![](https://i.imgur.com/DC8XTKM.gif =400x250)





