# Gantt-Manager-with-Java-Swing

Software named "Gantt Manager" is a process manager.

Input is a file with tasks which includes parent tasks and child tasks.
Child tasks refer to the parent task.
Each task has the following fields: TaskId, TaskText, MamaId, start, end, and cost.
Parent task has MamaId equal to 0.
Child task has MamaId equal to the TaskId of its parent task.
File contains a large number of tasks in a random order.

UC1: Load task file:
The first step is to load the CSV file and sort tasks based on the TaskId.
Tasks are then sorted based on the 'start' field.
Tasks that start earlier must be run before tasks that start later.
Tasks are presented to the user with a UI in a Gantt Diagram

UC2: Top Tasks:
Top Tasks are presented with UI in a Gant Diagram.
Top Tasks are the tasks that have MamaID = 0

UC3: Find Task By ID:
Task that "taskId" matched with id given is presented to user with a UI in a Gant Diagram

UC4: Find task by prefix:
Tasks that "taskText" matches with the given description are presented to user with a UI in a Gant Diagram

UC5: Save Report:
1. Save in tsv file:
  This save the sorted tasks in the wright format that they should be executed in a tsv file
2. Save in md file:
  This save the sorted tasks in the wright format that they should be executed in a markdown file
3. Save in html file:
  This save the sorted tasks in the wright format that they should be executed in a html file
  
  
 #### Import these files as a project in eclipse in order to run the Gant Manager!
 ### Have Fun!!!
