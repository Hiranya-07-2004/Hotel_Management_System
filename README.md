# Hotel_Management_System
Console-based Hotel Reservation System built using Java and MySQL (JDBC). It supports room booking, viewing, updating, and deleting reservations through a menu-driven interface, demonstrating CRUD operations, database connectivity, and core Java concepts.
Hotel Reservation System (Java + MySQL)

A console-based Hotel Reservation System developed using Java and MySQL (JDBC). This project allows users to manage hotel room reservations through a simple, menu-driven interface while demonstrating database connectivity and CRUD operations.

📌 Features

➤Reserve a Room
        Add new reservations by entering guest name, room number, and contact number.

➤View Reservations
       Display all existing reservations in a formatted table.

➤Get Room Number
     Retrieve the room number using Reservation ID and Guest Name.

➤Update Reservation
     Modify guest details, room number, and contact information.

➤Delete Reservation
      Remove a reservation using its Reservation ID.

➤Exit System
      Safely exits the application with a user-friendly message.

🛠️ Technologies Used
     ➤Java
     ➤JDBC (Java Database Connectivity)
     ➤MySQL
     ➤IntelliJ IDEA / Any Java IDE

🗄️ Database Details
             Database Name: hotel_db
             Table Name: reservations

▶️ How to Run the Project

➣Install MySQL and create the database:
➣CREATE DATABASE hotel_db;
➣Create the table:
➣CREATE TABLE reservations (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    guest_name VARCHAR(100),
    room_number INT,
    contact_number VARCHAR(15),
    reservation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
➣Update database credentials in the Java file:

              private static final String url = "jdbc:mysql://localhost:3306/hotel_db";
              private static final String username = "root";
              private static final String password = "your_password";
➣Add MySQL Connector/J to your project dependencies.
➣Run the HotelReservationSystem.java file.

➤ Concepts Demonstrated

   ➣ JDBC connection handling
   ➣ SQL CRUD operations
   ➣ Exception handling
   ➣ Menu-driven console applications
   ➣ Java Scanner usage

➤ Future Enhancements

  ➣Use PreparedStatement to prevent SQL Injection
  ➣Implement room availability checks
  ➣Add user authentication
  ➣Build a GUI using JavaFX or Swing
