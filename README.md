# Package Delivery Tracker Web App :package:

This repository contains a :coffee: Java Swing application.  
This project needs to be run with its :leaves::hiking_boot: [Spring Boot Web Server](https://github.com/mrpthemrp/A-213-WebServer).

:warning: ***Please DO NOT FORK this repository/project!*** :warning:  
:red_square: ***Please DO NOT COPY ANY CODE from this project!*** :red_square:

This project is not intended to be open-source, feel free to use it as a reference but DO NOT FORK OR COPY!  
If used as reference, please cite by providing link to project and author name \([see section below](#4-citation-format)\).

:hotsprings: **[JavaDocs Link](https://mrpthemrp.github.io/A-213/)**
  
Watch a quick demo of the project through the link below!  
:vhs: **[Video Demo Link](??)**

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

**See [video demo](#package-delivery-tracker-web-app-package) for more comprehensive walkthrough.**

### :sparkles: Project Takeaways

- Coded with [OOP/D](https://en.wikipedia.org/wiki/Object-oriented_programming) in mind
    - Fine-tuned OOP/D skills with this project
-  Practiced using [MVC Model](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) intentionally
-  Learned how to use [Java's File Separator](https://www.baeldung.com/java-file-vs-file-path-separator) to account for backslash differences in different OS
-  Extensively used Java Swing's built in [GUI Components](https://en.wikipedia.org/wiki/Swing_(Java)) to build UI
    - Learned how to use JFrame, JPanels, etc.
-  Created a lot of custom UI components through OOD concepts of [Inheritance](https://www.geeksforgeeks.org/inheritance-in-java/), [Abstraction](), and [Polymorphism](https://www.geeksforgeeks.org/polymorphism-in-java/) 
-  Built simple Spring Boot [web server](https://github.com/mrpthemrp/A-213-WebServer) to emulate the [Client-Server Model](https://en.wikipedia.org/wiki/Client–server_model)
-  Practiced writing both front and backend code for this project
-  Practiced using [CRC Cards](http://agilemodeling.com/artifacts/crcModel.htm) and [UML Diagrams](https://en.wikipedia.org/wiki/Unified_Modeling_Language) to plan out code prior to writing code
    - This [tag's folder](https://github.com/mrpthemrp/A-213/tree/6db2edbe1d6c21e1b94a0cfa5b9f912676a7cd36/docs) has the documents
- Practiced iterative programming as this project was completed in 4 different increments

### :exclamation: Project Shortcomings

- UI is not 100% adaptive to different screen sizes
- UI Pop-up windows are not centered on screen properly
- UI Pop-up is not always responsive
- Package UI is not cohesive in width, some are longer and others are shorter
    - This causes information to be cut off from the screen at times
- :lady_beetle: There is a bug! Packages that turn overdue as the program is running does not automatically get marked as overdue.
    - This minor bug is an error on the backend algorithm 

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
2. Unzip the main folder - **'record-player-sim-main'**.
    - This may take a few minutes, make sure your machine has ample space!
3. Open **IntelliJ** and select **'Open'** or 'Open Project'.
4. **Locate** where the **unzipped folder** from step 2 is on your machine. Click on the folder to open the project.
    - Click 'Trust Project' when the pop-up appears.
    - The project will now open.
</details>
<details><summary><h4>Part II - Configure Folders and Libraries</h4></summary><br>

5. Configure the SDK by **File > Project Structure > Project Settings > Project**
    - *We will set the JDK, Language Level, and Compiler output here.*
6. Select ***JDK 16***
    - It is HIGHLY important that JDK 16 is used! Project will not run otherwise.
    - JDK 16.0.2 is preferred.
7. Select **'Language Level' to be 16**
    - If language level is not 16, the project will not run.
8. Set **'Compiler output:'** to the out folder in the project folder
    - Path: **record-player-sim-main/out**
9. Go to **Libraries** which is **also under Project Settings**
10. **Click on the "+" button** to add a library
11. **Click "Java"** from the library options
12. **Add 'core.jar'** and only this one jar!
    - Path: **record-player-sim-main/src/core.jar**
13. Once the core.jar is added, **click on the '+' under the core library**
    - It should say 'Add Alt+Insert' on mouse hover
14. Now **add all the remaining jar files** under the src folder
    - Add all at the same time by holding down 'Shift' and selecting all.
15. Click **'Apply'** and then **'OK'**
</details>
<details><summary><h4>Part III - Set Sources Root and Configurations</h4></summary><br>

16. **Find the 'src' folder** in the project directory
17. Right-click and **go to 'Mark Directory as'**
18. **Click on 'Sources Root'** from the listed options
    - The folder colour should turn blue after clicking.
19. Let IntelliJ reconfigure things and once it is done, *go to click on 'Current File' to edit run configurations**
    - Located near the run button; top right bar.
20. Click **'Edit Configurations...'** and then **'Add new configuration...'**
21. Click **'Application'** on the pop-up
22. Under **'Build and run'** set SDK to 16 if not already, **type 'main.RoomApp' in the Main class bar**
    - The bar will be highlighted red if no main class is specified.
23. In **'Working directory:'** set the directory to **'src'**
    - It currently is just the record-player-sim-main folder which will not allow the program to run correctly.
24. **Change the name of the build** to something meaningful like 'RoomApp'
    - On default it is just 'Unnamed'
25. Click **'Apply'** and then **'OK'**
26. The project is now ready to run!
</details>

## 3. References

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
