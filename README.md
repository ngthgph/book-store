<h1>Gbook Project</h1>
<h2>Description</h2>
<p>This is a BookStore Android Application built in Kotlin that provides a place for users to search, view, save, and buy books. The UI is built using Jetpack Compose to be more accessible for all users and improve performance. Besides, View is still utilized to create needed features like ad banners. </p>
<h2>Content</h2>
<table>
  <tr>
    <th width="15%">Module</th>
    <th width="15%">Feature</th>
    <th width="25%">Tech Stacks</th>
    <th width="35%">Note</th>
  </tr>
  <tr>
    <td rowspan="4">Data layer <br> (DI)</td>
    <td>Online books datasource</td>
    <td>Retrofit, Kotlin Serialization, Coil</td>
    <td>Datasource: Google Book API</td>
  </tr>
  <tr>
    <td>Books database</td>
    <td>Room, Coroutines, Flow RESTful, SQL</td>
    <td></td>
  </tr>
  <tr>
    <td>Account database</td>
    <td>Room, Coroutines, Flow, Firebase (ongoing)</td>
    <td></td>
  </tr>
  <tr>
    <td>User layout preference</td>
    <td>Coroutines, Flow, Preferences DataStore</td>
    <td>Save the latest configuration layout of the book list chosen by users</td>
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
    <td>Include Home, Library, Cart, Categories, Account, Book Details Screens, and other subscreens</td>
  </tr>
  <tr>
    <td>Adaptive Layout</td>
    <td>Compose Navigation, WindowSize</td>
    <td>Bottom Bar Navigation, Navigation Rail, Permanent Navigation Drawer, List-Detail Layout </td>
  </tr>
  <tr>
    <td>Theming</td>
    <td>Material Design, XML </td>
    <td></td>
  </tr>
  <tr>
    <td rowspan="1">Testing</td>
    <td>Automated Test (Unit Test)</td>
    <td>JUnit, Espresso</td>
    <td>Test Network API service, ViewModel, NetworkRepository</td>
  </tr>
</table>
<h2>UI Demo</h2>
<a href="https://sites.google.com/view/gbook-ui-demo">https://sites.google.com/view/gbook-ui-demo</a>
