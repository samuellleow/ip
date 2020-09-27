# User Guide
Duke is an application that helps the user with task scheduling and management.

## Things to do before we start
1. Ensure you have **Java 11** installed in your computer.
2. Copy the `ip.jar` file into an empty folder.
3. Open a command window in that folder.
4. Enter the following commands `chcp 65001` followed by setting the font to **NSimSun**.
5. Run the command `java -Dfile.encoding=UTF-8 -jar ip.jar` to start the application.

## Main Features 
* Create various types of tasks and adding into a list (E.g todo, deadline, event)
* Mark tasks as done
* Delete tasks from the list
* Search for specific tasks

### Adding a Todo task
Adds a todo task to the task list.

Command: `todo TASK_DESCRIPTION`

Example: `todo read book`

Expected output:

    ____________________________________________________________
     Got it. I've added this task:
       [T][✘] read book
     Now you have 6 tasks in the list.
    ____________________________________________________________



    
### Adding a Deadline task
Adds a deadline task to the task list.

Commands: 
1. `deadline TASK_DESCRIPTION /by DEADLINE_BY`
2. `deadline TASK_DESCRIPTION /by (yyyy-MM-dd)T(HH:mm)`

Example:
1. `deadline cs2113 project /by December 2020`
2. `deadline team project /by 2020-12-31T23:59`

Expected output:
1.     ____________________________________________________________
        Hello! You may want to use the correct date and time format:)
        /by (yyyy-MM-dd)T(HH:mm) OR
        /at (yyyy-MM-dd)T(HH:mm)
        But no worries, I have added your task into the list:)
       ____________________________________________________________
        Got it. I've added this task:
          [D][✘] cs2113 project (by: December 2020)
        Now you have 7 tasks in the list.
       ____________________________________________________________
       
2.     ____________________________________________________________
        Got it. I've added this task:
          [D][✘] team project (by: Dec 31 2020 23:59)
        Now you have 8 tasks in the list.
       ____________________________________________________________

        
### Adding an Event task
Adds an event task to the task list.

Commands: 
1. `event TASK_DESCRIPTION /by EVENT_AT`
2. `event TASK_DESCRIPTION /by (yyyy-MM-dd)T(HH:mm)`

Example:
1. `event project presentation /at January 2021`
2. `event Grandma 99th birthday /at 2020-12-31T23:59`

Expected output:
1.     ____________________________________________________________
        Hello! You may want to use the correct date and time format:)
        /by (yyyy-MM-dd)T(HH:mm) OR
        /at (yyyy-MM-dd)T(HH:mm)
        But no worries, I have added your task into the list:)
       ____________________________________________________________
        Got it. I've added this task:
          [E][✘] project presentation (at: January 2021)
        Now you have 9 tasks in the list.
       ____________________________________________________________
       
2.     ____________________________________________________________
        Got it. I've added this task:
          [E][✘] Grandma 99th birthday (at: Dec 31 2020 23:59)
        Now you have 10 tasks in the list.
       ____________________________________________________________



         
### Listing all the tasks
Shows a list of all tasks in the task list.

Command: `list`


### Marking task as done
Marks the specified task in the list as done.

Command: `done TASK_INDEX`

Example: `done 2`

Expected output: 

    ____________________________________________________________
     Nice! I've marked this task as done:
       [E][✓] water plants (at: 12/03/2021)
    ____________________________________________________________


### Deleting task
Deletes the specified task in the list

Command: `deletes TASK_INDEX`

Example: `delete 3`

Expected output: 

    ____________________________________________________________
     Noted. I've removed this task:
       [D][✘] cs2113 project (by: 02/10/20202)
     Now you have 9 tasks in the list.
    ____________________________________________________________


### Finding task using keyword provided
Finds task(s) in the list containing the keyword provided

Command: `find KEYWORD`

Example: `find book`

Expected output:

    ____________________________________________________________
     Here are the matching tasks in your list:
     1.[D][✓] return book (by: June 6th)
     2.[D][✓] read book (by: Oct 11 2020 18:00)
     3.[T][✘] read book
    ____________________________________________________________


### Terminating the application
Terminates Duke program

Command: `bye`



