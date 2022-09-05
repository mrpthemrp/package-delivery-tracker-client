# User Manual

## Table of Contents
1. [Introduction](#1-introduction)
2. [Notes](#2-notes)
3. [How To Use](#3-how-to-use)

### 1. Introduction
Hello!  
Welcome to the user manual.  
This document will contain a few notes and show you how to use the program.

If you are curious, there is a prototype link that shows you what was designed, and what was implemented.
The prototype was also designed by the author of this program.

[Prototype Link](https://www.figma.com/proto/ElhYpu0hjZ7XigCQqGyEj5/PackageDeliveryTracker-UI-Mock-up?page-id=0%3A1&node-id=1%3A4&starting-point-node-id=1%3A4&scaling=scale-down)  
Link password: 'cmpt213'

Make sure not to have too many windows running when you open the link since Figma takes up quite a bit of space to load.

### 2. Notes
There are a few notes that are important to know.

- **JavaDocs**: For the Util class, the constants are not given a description since their names are self-explanatory.
    - Methods, enums, and classes are of course defined for everything else.
- **JavaDocs in Program**: Some JavaDoc lines go over a comfortable character-per-line count since they are html links.
    - Please let me know if that is an issue!
- This program is very media-rich which is why there are a TON of classes under the 'view' packages. Sub packages are  
included under 'view' to make looking at things easier.
- **UI - Add Package**: the brown line divider sometimes disappears when toggling different fields. I think it has something  
to do with the LGoodDatePicker jar's overrides, so I could not fix this minor bug. The JDialog works fine though!
- **UI - Main Screen**: If there are an abundant amount of packages in the list, the scroll pane for some reason does not start  
immediately at the top, it starts just a bit under the first item. Make sure to just scroll up a bit and the top will be reached.

### 3. How To Use

This program is super simple to use!  
#### Steps
1. **Run** the program
2. Start screen will appear, **click "ENTER"** to start the program.
3. You will land on the main screen and see any saved packages on the right and the option to add a package on the left.
    1. **View Packages on the Left**
        1. Click on "LIST ALL", "UPCOMING", "OVERDUE" to see the list associated with the button.
              Buttons will change colour on hover to show that they are clickable.
              If button is selected, it will stay the darker colour.
              To see scroll bar, hover over the rightmost side of the list of packages. The scroll bar will
              only appear on hover. It is a lighter blue so it may be hard to see if on a smaller screen.

        2. Remove a package by simply clicking "REMOVE" on the package you want to delete.
              This works on any of the views. You will get a remove confirmation dialog.

        3. Change a package's delivery status by simply clicking on the "Delivered?" check box.

    2. **Add A Package on the Right**
        1. Click "ADD PACKAGE" and a JDialog will pop-up

        2. Once the pop-up shows, fill in the fields for the new package.
              Required fields will turn red if not filled out, or not filled properly.
              Extra field will change dynamically depending on the selection of package type.

        3. Click "CREATE" to add package. You will not be able to create a new package unless
              Fields are all filled out. An error message will show if fields are not properly filled out.

        4. Click "CANCEL" to cancel package creation. A pop-up dialog will show to confirm your choice.

4. Once you are done, close the main window and the data will be automatically saved.
    
---
Copyright 2022, Deborah Wang
