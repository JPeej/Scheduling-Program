#Requirements

1. Create a GUI based program for the company. Non-Java APIs are not allowed with the exception of JavaFX SDK and MySQL JDBC Driver.
    A.  Create a login form with the following features.
    
        *   Accept User ID and password. Provides error messaging.
        *   Determine user location and display it.
        *   Adjust user login form language based upon user location (English or French).
        *   Adjust error messaging language based upon user location (English or French).

    B.  Provide following customer features.
    
        *   Customer records and appointments can be added, modified, and removed. All customer appointments must be deleted before customer may be removed.
        *   The following customer data must be collected for adding or modifying customer: customer name, address, zipcode, and phone number.
        *   Customer IDs must be auto-generated.
        *   First-level divison and country data are collected and pre-populated using seperate combo boxes or lists.
        *   First-level list should be filtered by user's country selection.
        *   When modifying customer, screen must provided autopopulated form.
        *   Follow the provided format:
            *   U.S. address: 123 ABC Street, White Plains
            *   Canadian address: 123 ABC Street, Newmarket
            *   UK address: 123 ABC Street, Greenwich, London
        *   Customer ID is disabled on modify form. All other fields may be changed.
        *   Customer data, including first-level divison data, is displayed via a TableView. A list of all customers and their data is displayed via a TableView. Updates of the data can be performed on the textfields.
        *   Prompt the user with a message when a customer is deleted.

    C.  Provide following scheduling features.
    
        *   Allow users to add, modify, and delete appointments.
        *   Contact customer name assigned to appointment via drop down or combo box.
        *   Prompt user with message if appointment is canceled, should provide appointment ID and type of appointment.
        *   Appointment ID is auto-generated and diasbled in program.
        *   The following appointment data must be collected for adding or modifying appointment: title, description, location, contact, type, start date and time, end date and time, customer ID, and user ID.
        *   Original appointment info displayed in modifiy screen is in local time.
        *   All fields except ID may be modified.
        *   Allow users to filter and view appointments by month and week within TableView. Allow choice between month and week via tabs or radio buttons.
        *   Provide the following info in the appointment TableView as columns: appointment ID, title, description, location, contact, type, start date and time, end date and time, customer ID, user ID.
        *   Allow user to adjust appointment time.
        *   Store appointment times in UTC.
        *   Adjust appointment time displayed to user locale time.
        *   Do not allow the following situations to occur and provide error messaging for each:
            *    scheduling an appointment outside of business hours defined as 8:00 a.m. to 10:00 p.m. EST, including weekends.
            *   scheduling overlapping appointments for customers.
            *   entering an incorrect username and password.
        *   Alert user upon login if they have an appointment within 15 minutes. Message should include appointment ID, date, and time.
        *   Alert user upon login if they have no appointments within 15 minutes.
        *   Allowing generation of the following reports:
            *   the total number of customer appointments by type and month.
            *   a schedule for each contact in your organization that includes appointment ID, title, type and description, start date and time, end date and time, and customer ID.
            *   an additional report of your choice that is different from the two other required reports in this prompt and from the user log-in date and time stamp that will be tracked in part 3.

2. Use two unique lambda expressions.

3. Write code that provides the ability to track user activity by recording all user log-in attempts, dates, and time stamps and whether each attempt was successful in a file named login_activity.txt. Append each new record to the existing file, and save to the root folder of the application.

4. Provide descriptive Javadoc comments for at least 70 percent of the classes and their members throughout the code, and create an index.html file of your comments to include with your submission based on Oracle’s guidelines for the Javadoc tool best practices. Your comments should include a justification for each lambda expression in the method where it is used.

5. Create a README.txt file that includes the following information:

    *   title and purpose of the application
    *   author, contact information, student application version, and date
    *   IDE including version number (e.g., IntelliJ Community 2020.01), full JDK of version used (e.g., Java SE 17.0.1), and JavaFX version compatible with JDK version (e.g. JavaFX-SDK-17.0.1)
    *   directions for how to run the program
    *   a description of the additional report of your choice you ran in part A3f
    *   the MySQL Connector driver version number, including the update number (e.g., mysql-connector-java-8.1.23)

6. Demonstrate professional communication.
