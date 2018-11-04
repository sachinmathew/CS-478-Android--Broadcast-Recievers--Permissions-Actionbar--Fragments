# CS-478-Android--Broadcast-Recievers--Permissions-Actionbar--Fragments

1. Application A 1 defines a new dangerous-level permission called “edu.uic.cs478.f18.project3” and sends
one of two kinds of broadcasts. Both broadcasts use implicit intents. The other two applications will
receive the broadcasts; however, these applications will receive the broadcasts only if the sender (i.e.,
A 1 ) has that permission. App A 1 specifically defines an activity containing two read-only text views
and two buttons. The buttons, when selected, will broadcast two different intents with actions con-
cerning points of interest in the cities of San Francisco, CA and New York, NY, depending on the
button pressed. The text views describe the meaning of the buttons to the device user. Both broadcasts
are ordered broadcasts. Moreover, both broadcasts require that a receiver have acquired permission
“edu.uic.cs478.f18.project3” in order to respond to the broadcast. App A 1 must also acquire this per-
mission before sending the broadcasts.


2. Application A 2 receives the intents sent by A 1 ; A 2 contains a single activity that defines a welcome
message and a button. When the button is pressed, the activity checks whether the permission has
been acquired “edu.uic.cs478.f18.project3”. If not, it requests the permission. App A 2 also define two
broadcast receivers programmatically, one for each of the two broadcasts by A 1 . Whenever a broadcast
intent is received, A 2 displays a toast message on the device’s display. The toast message indicates
whether the broadcast sender was selecting San Francisco or New York. However, A 2 ’s broadcast
receiver is designed in such a way that it will only respond to a broadcast if the broadcast sender has
permission “edu.uic.cs478.f18.project3”.


3. Application A 3 also receives A 1 ’s broadcasts if the sender has permission “edu.uic.cs478.f18.project3”.
Depending on the intent received, A 3 will launch one of two activities. The first activity displays
information about at least 6 points of interest in San Francisco. The second activity should show points
of interest in New York; however, you are not responsible for coding this activity. Just display a toast
message indicating the New York information is under construction. A 3 defines also a single broadcast
receiver statically; this receiver must respond to both kinds of intents sent by A 1 .
The San Francisco activity consists of two fragments, whose behavior is described below. Finally,
application A 3 maintains an action bar. The action bar shows the name of the application and an icon
associated with the application (your choice).

The San Francisco activity in A 3 contains two fragments. The first fragment displays a list of points of interest
for the city. The device user may select any point from the list; the currently selected item is highlighted. The
second fragment shows the official web page of the selected item.
When the device is in portrait mode, the two fragments are displayed on different screens. First, the
device will show only the first fragment. When the user selects an item, the the first fragment disappears and
the second fragment is shown. Pressing the “back” soft button on the device, will return the device to the
original configuration (first fragment only), thereby allowing the user to select a different point of interest.
When the device is in landscape mode, application A 3 initially shows only the first fragment across the entire
width of the screen. As soon as a user selects an item, the first fragment is “shrunk” to about 1/3 of the screen’swidth. This fragment will appear in the left-hand side of the screen, with the second fragment taking up the
remaining 2/3 of the display on the right. Again, pressing the “back” button will return the application to its
initial configuration. The action bar should be displayed at all times regardless of whether the device is in
portrait or landscape mode.

Finally, the state of application A 3 should be retained across device rotations, e.g., when the device is
switched from landscape to portrait configuration and vice versa. This means that the selected list item (in the
first fragment) and the page displayed in the second fragment will be kept during configuration changes.
As for the order of execution of A 2 and A 3 ’s receivers, you should configure these apps in such a way that
a receiver in A 2 is always executed before the receiver in A 3 , after A 1 sends a broadcast.
Implementation notes. For this project use a Pixel 2 device running a new Android platform (API 25—
Nougat). (You’ll have to download SDK tools for API level 25 and to create an AVD running 7.1 in the Studio
IDE.) You are not required to provide backward compatibility with previous Android versions. Use method
setRetainInstance() to prevent fragments from getting deleted when a configuration change occurs, resulting
in the destruction of the containing activity. 
