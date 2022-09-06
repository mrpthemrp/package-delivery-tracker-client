# Package Delivery Tracker Web App :package:

This repository contains a :coffee: Java Swing application.  
This project needs to be run with its :leaves::hiking_boot: [Spring Boot Web Server](https://github.com/mrpthemrp/package-delivery-tracker-server).

:warning: ***Please DO NOT FORK this repository/project!*** :warning:  
:red_square: ***Please DO NOT COPY ANY CODE from this project!*** :red_square:

This project is not intended to be open-source, feel free to use it as a reference but DO NOT FORK OR COPY!  
If used as reference, please cite by providing link to project and author name \([see section below](#4-citation-format)\).

:hotsprings: **[JavaDocs Link](https://mrpthemrp.github.io/package-delivery-tracker-client/)**
  
Watch a quick demo of the project through the link below!  
***Video Link coming soon!***

## :bookmark_tabs: Table of Contents
1. [Project Description and Summary](#1-project-description-and-summary)
   1. [Project Takeaways](#sparkles-project-takeaways)
   2. [Project Shortcomings](#exclamation-project-shortcomings)
2. [Installation Guide](#2-installation-guide)
   1. [Software Requirements](#computer-software-requirements)
   2. [Steps](#memo-steps)
3. [References](#3-references)
4. [Citation Format](#4-citation-format)

## 1. Project Description and Summary

This project is the final assignment for CMPT 213 ([Dr. Victor Cheung](http://www.victorcheung.net/)) at SFU.

**Main Screen**  
This project is a package delivery tracker. On program start, the user will be brought to a simple screen that has three lists: All Packages, Upcoming Packages, and Overdue Packages. Existing packages in the system will appear sorted from earliest delivery date. The screen will also show the current date and time, as well as a button for adding a package. If ADD PACKAGE button is clicked, a pop-up will appear that prompts the user to create a new package entry.

**Packages**  
The user is able to add 3 different types of packages: Book, Perishable, and Electronic. Based on the delivery date added on package creation, it will be sorted under upcoming or overdue on the main screen. The user is able to mark padckage as delivered or remove the package on the main screen. On package removal, from any of the lists, the package will immediately be deleted from all lists. If the package is marked as delivered the package will only appear in the ALL PACKAGES list.

**Saving and Exiting**  
If the user wants to delete a package, a pop-up will appear to confirm. If the user wants to exit, simply click the exit button and the program will automatically save the data to the server. The server will continue running if the client stops.

***User needs to stop the server side by themselves! Exiting the client app will not terminate the server.***

**See [video demo](#package-delivery-tracker-web-app-package) for more comprehensive walkthrough.**

### :sparkles: Project Takeaways

- Coded with [OOP/D](https://en.wikipedia.org/wiki/Object-oriented_programming) in mind.
    - Fine-tuned OOP/D skills with this project.
-  Practiced using [MVC Model](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) intentionally.
-  Learned how to use [Java's File Separator](https://www.baeldung.com/java-file-vs-file-path-separator) to account for backslash differences in different OS.
-  Extensively used Java Swing's built in [GUI Components](https://en.wikipedia.org/wiki/Swing_(Java)) to build UI
    - Learned how to use JFrame, JPanels, etc.
-  Created a lot of custom UI components through OOD concepts of [Inheritance](https://www.geeksforgeeks.org/inheritance-in-java/), [Abstraction](), and [Polymorphism](https://www.geeksforgeeks.org/polymorphism-in-java/).
-  Built simple Spring Boot [web server](https://github.com/mrpthemrp/A-213-WebServer) to emulate the [Client-Server Model](https://en.wikipedia.org/wiki/Client–server_model).
-  Practiced writing both front and backend code for this project.
-  Practiced using [CRC Cards](http://agilemodeling.com/artifacts/crcModel.htm) and [UML Diagrams](https://en.wikipedia.org/wiki/Unified_Modeling_Language) to plan out code prior to writing code.
    - This [tag's folder](https://github.com/mrpthemrp/A-213/tree/6db2edbe1d6c21e1b94a0cfa5b9f912676a7cd36/docs) has the documents.
- Practiced iterative programming as this project was completed in 4 different increments.

### :exclamation: Project Shortcomings

- UI is not 100% adaptive to different screen sizes.
- UI Pop-up windows are not centered on screen properly.
- UI Pop-up is not always responsive to changes.
- Package UI is not cohesive in width, some are longer and others are shorter.
    - This causes information to be cut off from the screen at times.
- :lady_beetle: There is a bug! Packages that turn overdue as the program is running does not automatically get marked as overdue.
    - This minor bug is an error on the backend algorithm.

## 2. Installation Guide
***This project was created through IntelliJ.***

### :computer: Software Requirements
- **Windows OS** or **MacOS**
  - ***This project HAS BEEN tested on MacOS and does work.***
  - Windows 7 and up is recommended
  - MacOS 10.12 and up is recommended.
- **Latest version of [IntelliJ](https://www.jetbrains.com/idea/download/#section=windows)**
  - Either Ultimate or Community version works.

### :memo: Steps
***The following steps are intended for use with the IntelliJ IDE***

<details><summary><h4>Part I - Download Code and Open on IntelliJ</h4></summary><br>

1. Download this project as a **ZIP file**.
    - Click **'Code'** and then **'Download ZIP'**.
2. Unzip the main folder - **'package-delivery-tracker-client-master'**.
    - This may take a few minutes, make sure your machine has ample space!
3. Open **IntelliJ** and select **'Open'** or 'Open Project'.
4. **Locate** where the **unzipped folder** from step 2 is on your machine. Click on the folder to open the project.
    - Click 'Trust Project' when the pop-up appears.
    - The project will now open.
</details>
<details><summary><h4>Part II - Configure Libraries and Run Program</h4></summary><br>

5. Configure the SDK by **File > Project Structure > Project Settings > Project**
    - *We will set the JDK, Language Level, and Compiler output here.*
6. Select ***JDK 18***
    - It is HIGHLY important that JDK 18 and up is used!
7. Select **'Language Level' to be 18**
    - Or whichever JDK number you are using.
8. Make sure **'Compiler output:'** is set to the out folder
    - It should already be set but if not make sure the path is **../package-delivery-tracker-client-master/out**
9. Go to **Libraries** which is **also under Project Settings**
10. **Click on the "+" button** to add a library
11. **Click "Maven"** from the library options
12. **Type 'com.google.gson'** and click on the search button
13. **Select the newest gson library** and click ok
    - Version 2.9.1 was used to write this project
14. **Click 'OK'** when pop-up comes up on adding 'client app' to module
    - If asked to replace old libary, click CANCEL and the old libary will be added to path.
15. Click **'Apply'** and then **'OK'**
16. **Click Run** and the app will run properly. Make sure to run the server side of the program too!
    - The console will print [Status Codes](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes) on each operation.
      - Usually 200, 201 are the SUCCESS codes that will print
</details>


## 3. References

All references are cited within the program's [API](https://mrpthemrp.github.io/A-213/).

However, there are a few external libraries used that should be noted:
- [Google GSON Libray](https://github.com/google/gson)
    - [Runtime Type Adapter Factory](https://github.com/google/gson/blob/master/extras/src/main/java/com/google/gson/typeadapters/RuntimeTypeAdapterFactory.java)
- [LGoodDatePicker](https://github.com/LGoodDatePicker/LGoodDatePicker)

## 4. Citation Format
Example of citing this project as a reference:
> Reference used for creating MVC model: https://github.com/mrpthemrp/A-213/tree/master/src/cmpt213/assignment4/packagedeliveries/client  
> Date Accessed: August 2022  
> Developer: [Deborah Wang](https://github.com/mrpthemrp)

If using this project as a reference please copy and paste the following into your references/citations:
```diff
Reference for <code referenced>: <file/folder URL>
Date Accessed: <date accessed>
Developer: Deborah Wang (https://github.com/mrpthemrp)
```

---
Last Code Update Date: August 2022

Copyright August 2022, Deborah Wang
