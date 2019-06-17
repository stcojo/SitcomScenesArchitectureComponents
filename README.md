# Sitcom Scenes Architecture Components
Android app which uses Architecture components(LiveData, ViewModel, Room) and demonstrates how a repository is used in order to 
fetch information either from the web, using Retrofit or to generate it locally.

<img src="https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/img/a7da8f5ea91bac52.png"/>

As per the Architecture Components guidelines, <b>separation of concerns is used and each component only knows about the first one below it</b>.

The repository is used a single source of truth when it comes to fetching data. 

Flow of the app:

1) The ViewModel is observed in the Main Activity and the RecyclerView adapter is notified of any changes
2) User clicks the + button, this signals the addition of a new scene to the ViewModel
3) The ViewModel requests a new item from the Repository
4) The repository makes sure a new item is generated, either from a Web Service using Retrofit or it is generated randomly locally.
5) After the item is generated, it is inserted to the Room database using the DAO
6) Step 1 observes the changes the updates the UI.

The recyclerview displaying the Scenes is notified of the updates to the LiveData by "observing" the livedata and changes are reflected
instantly, there is no need to manually update it.
