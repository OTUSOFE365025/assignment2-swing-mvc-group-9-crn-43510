[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/57HVEcop)
Example of an MVC design based on Swing. Answer questions 1 & 2 in this README file.

# Assignment 2 – Swing MVC Application
Course: SOFE 3650 – Software Design and Architecture  
Group 9, CRN 43509  
October 08, 2025 



This assignment was to understand the Java Swing framework and how it uses the Model–View–Controller (MVC) architectural design pattern to create a simple desktop application.  
Swing gives us an event-driven (user interaction dependent), platform-independent (operable on any OS/platform) environment for making graphical user interfaces (GUI).  
The assignment shows us how Swing’s separable model architecture separates the model (data) from its user interface (view + controller) to provide more efficiency, flexibility yet also stability.



## Part 1 – Investigation of the Swing Framework
Part 1 was to understand the purpose, advantages in usage, and definition of Swing framework. 
In Swing, the View and Controller are merged into a single UI delegate for efficiency.  
This section also highlights how Swing supports an event-driven model and pluggable look-and-feel, allowing interfaces to change appearance at runtime.



## Part 2 
Part 2 analyzes the example code from the GitHub repo and to explain how it implements the MVC pattern.

- Model.java manages the program’s internal data such as stored values or calculations  
- View.java defines and arranges GUI components like JFrame, JPanel, JButton, JLabel, and text fields  
- Controller.java connects the view and model, responds to user actions, and updates the interface  




## Part 3 – Swing Component Diagram
Part 3 has a Visual Paradigm class diagram displaying key Swing components and their relationships:
- JFrame as the main window container  
- JPanel for grouping and organizing layout  
- Core components such as JButton, JTextField, and JLabel  
- ActionListener interfaces showing how events are captured and handled  



## Part 4
Part 4 includes a sequence diagram on the function of the scanner button in the code from the scenario of Part 3.


