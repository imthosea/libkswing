## libkswing

KSwing is a Kotlin library for the Java Swing toolkit, inspired by Kotlin Compose and SwiftUI.<br>

```kotlin
val pizzasOrdered = KNonnullState(1)
KGroupPanel {
	KFlowPanel(addConstraints = BorderLayout.CENTER) {
		KHGlue()
		KLabel {
			pizzasOrdered.use {
				text = "$it pizzas will be ordered"
			}
		}
	}
	KFlowPanel(addConstraints = BorderLayout.PAGE_END) {
		KButton("Add another").kActionListener { pizzasOrdered.value++ }
	}
}
```
See the GitHub wiki for a tutorial.

## Examples
There are currently three examples:

A simple recreation of a `JDialog` using either `GridBagLayout` or `GroupLayout`,  
<img src="https://raw.githubusercontent.com/imthosea/images/refs/heads/master/kswing/v1/example-egg.png" alt="Eggs aren't supposed to be green" width="300">

A simple reminders app that lets you add/remove, check off and set the time of your reminders,  
<img src="https://raw.githubusercontent.com/imthosea/images/refs/heads/master/kswing/v1/example-reminder.png" alt="It's like Apple reminders, but not" width="300">

And a silly vessel creator.  
<img src="https://raw.githubusercontent.com/imthosea/images/refs/heads/master/kswing/v1/example-vessel.png" alt="WHAT VESSEL WILL YOU MAKE?" width="300">  

These are located in the `example` directory and use [FlatLaf](https://github.com/JFormDesigner/FlatLaf) as their look and feel. They're also available as a [runnable jar](https://github.com/imthosea/libkswing/releases/download/v1.0.0/libkswing-example-1.0.0.jar) on GitHub releases!

## Licensing
The library and the examples are under a very liberal BSD-2 clause license. See the LICENSE file for more.

## Installing
Add the maven repo:
<details open>
<summary>Gradle (Kotlin)</summary>

```kotlin
maven {
	name = "teamcelestial"
	url = "https://maven.teamcelestial.org/public"
}
```
</details>
<details>
<summary>Gradle (Groovy)</summary>

```groovy
maven {
    name "teamcelestial"
    url "https://maven.teamcelestial.org/public"
}
```
</details>
<details>
<summary>Maven</summary>

```xml
<repository>
  <id>teamcelestial</id>
  <url>https://maven.teamcelestial.org/public</url>
</repository>
```
</details>

Then add the dependency:
<details open>
<summary>Gradle (Kotlin)</summary>

```kotlin
implementation("me.thosea:libkswing:<version>")
```
</details>
<details>
<summary>Gradle (Groovy)</summary>

```groovy
implementation "me.thosea:libkswing:<version>"
```
</details>
<details>
<summary>Maven</summary>

```xml
<dependency>
  <groupId>me.thosea</groupId>
  <artifactId>libkswing</artifactId>
  <version>version</version>
</dependency>
```
</details>

Find the latest version here: https://maven.teamcelestial.org/#/public/me/thosea/libkswing/
