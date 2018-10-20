# SitcomScenesArchitectureComponents
Android app which uses Architecture components(LiveData, ViewModel, Room) and demonstrates how a repository is used in order to 
fetch information either from the web, using Retrofit or to generate it locally.

<img src="https://codelabs.developers.google.com/codelabs/build-app-with-arch-components/img/4ccbee1976421920.png"/>

As per the Architecture Components guidelines, separation of concerns is used and each component only knows about the first one below it.

The recyclerview displaying the Scenes is notified of the updates to the LiveData by "observing" the livedata and changes are reflected
instantly, there is no need to manually update it.
