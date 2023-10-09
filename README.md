# Virtual Treasure Hunt  #

This is a simple app that demonstrates the use of Geofences in Android. The app tracks user device
location and if it entered a Geofence a notification is generated, that the user found the treasure.

## GeoFence ##

A geofence is a virtual perimeter defined by GPS or RFID around a real world area. Geofences can be
created with a radius around a point location.
The geofencing API uses the device sensors to accurately detect the location of the device in a
battery-efficient way. Geofences have three transition types.

* **Enter** - Indicates that the user entered the geofence(s).
* **Dwell** - Indicates that the user enters and dwells in geofences for a given period of time.
* **Exit** - Indicates that the user has exited the geofence(s).

## Broadcast Receiver ##

The broadcast receiver is how Android apps can send or receive broadcast messages from the Android
system and other Android apps, similar to the publish-subscribe pattern. The broadcasts are sent out
and apps can register to receive specific broadcasts. When the desired broadcasts are sent out the
apps are notified.

## Pending Intent ##

PendingIntent is an intent that will perform at a later time. A PendingIntent is a description of an
Intent and target action to perform with it. Even if its owning application's process is killed, the
PendingIntent itself will remain usable.

## Helpful Tips ##

1. On Android apps targeting API 30+ are now no longer allowed to ask for background_permission at
   the same time as regular location permission. We have to split it into 2 separate asks:

* Ask for regular foreground location permission, once granted,
* Ask for background location permission on a new ask.