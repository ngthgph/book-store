<h1>Gbook Project</h1>
<h2>Description</h2>
<p>This is a BookStore Android Application built in Kotlin that provides a place for users to search, view, save, and buy books. The UI is built using Jetpack Compose to be more accessible for all users and improve performance. Besides, View is still utilized to create needed features like ad banners. </p>
<h2>Content</h2>
<table>
  <tr>
    <th>Module</th>
    <th>Feature</th>
    <th>Tech Stacks</th>
    <th>Note</th>
  </tr>
  <tr>
    <td rowspan="4">Data layer <br> (DI)</td>
    <td>Online books <br> datasource</td>
    <td>Retrofit, Kotlin Serialization, Coil</td>
    <td>Datasource: Google Book API</td>
  </tr>
  <tr>
    <td>Books <br> database</td>
    <td>Room, Coroutines, Flow <br> RESTful, SQL</td>
    <td></td>
  </tr>
  <tr>
    <td>Account <br> database</td>
    <td>Room, Coroutines, Flow, <br> Firebase (ongoing)</td>
    <td></td>
  </tr>
  <tr>
    <td>User layout <br> preference</td>
    <td>Coroutines, Flow, <br> Preferences DataStore</td>
    <td>Save the latest configuration layout <br>of the book list chosen by users</td>
  </tr>
  <tr>
    <td rowspan="4">UI layer <br> (MVVM)</td>
    <td>ViewModel</td>
    <td>Coroutines</td>
    <td></td>
  </tr>
  <tr>
    <td>Screens</td>
    <td>Compose, View, XML </td>
    <td>Include Home, Library, Cart, Categories, <br> Account, Book Details Screens, <br> and other subscreens</td>
  </tr>
  <tr>
    <td>Adaptive Layout</td>
    <td>Compose Navigation, WindowSize</td>
    <td>Bottom Bar Navigation, Navigation Rail, <br> Permanent Navigation Drawer, <br> List-Detail Layout </td>
  </tr>
  <tr>
    <td>Theming</td>
    <td>Material Design, XML </td>
    <td></td>
  </tr>
  <tr>
    <td rowspan="1">Testing</td>
    <td>Automated Test <br> (Unit Test)</td>
    <td>JUnit, Espresso</td>
    <td>Test Network API service, ViewModel, <br> NetworkRepository</td>
  </tr>
</table>
<h2>UI Demo</h2>
<a href="https://sites.google.com/view/gbook-ui-demo">https://sites.google.com/view/gbook-ui-demo</a>
