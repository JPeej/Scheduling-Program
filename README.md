# Java GUI-based Schedule Desktop Application
## Joshua Perry
#### jper856@wgu.edu - joshuaperry001@gmail.com
#### Version 1.0 (MVP)
#### November 06, 2022
#### IntelliJ IDEA 2021.1.3
#### Java SDK 11.0.15 | JavaFX SDK 18.0.1 | MYSQL Connector J 8.0.31

# Purpose
The purpose of this program is to provide a fictional company with a solution to their issue with their current 
methods. They operate globally, and it is assumed due to this, and their growth that their current methods of
customer interaction are lacking. This application is the Minimal Viable Product for the requirements put forth.
Further elaboration on the requirements can be found /Documentation/Requirements.md.

# Additional Report
I was required to provide an additional report of my choosing to include within the Report Menu. I included a report 
that provided a list of since expired appointments. The current database currently holds no triggers or functions
to deal with expired appointments. This could quickly lead to a bloated database. Further implementation could either
add a delete button or prompt the user upon Appointment Menu initialization.

# Application Instructions
## Login
Provide an authenticated username and associated password. Click submit. If the credentials are correct, then you will
be prompted with a message that describes any appointments of yours within the next 15 minutes. If no appointments are 
found you will still be prompted. If login failed, please try again or contact IT about your credentials.

## Navigation
Navigation to the other functional areas done via the top right buttons labeled: Customers Menu, 
Appointments Menu, Reports Menu. The application can be exited via the exit button in the bottom right. Any sub menus
may be backed out of with the cancel button.

## Customer Functionality
Upon login, you will be directed to the Customer Menu. This initial menu will provide a table with a total overview
of all customer data within your database. 
### Add a customer
To add a customer, from the Customer Menu click the add button in the bottom left. A new screen will load. Customer
creation can only be completed if all fields are filled in and all combo boxes have a selection. If any errors are 
present, the application will prompt you of them. Upon a successful save of a new customer you will be redirected back 
to the Customer Menu which will now show your new customer.
### Modify a customer
To modify a customer, from the Customer Menu click a customer to modify and then click the modify button in the bottom 
left. If no customer is selected, navigation will not occur. Once in the Modify Menu, you may change any of the fields 
or combo box selections. Again all fields must be filled in and all combo boxes selected. Upon a successful save of 
the modified customer you will be redirected back to the Customer Menu which will now show your modified customer.
### Delete a customer
To delete a customer, from the Customer Menu click a customer to delete and then click the delete button in the bottom
left. If no customer is selected, no deletion will occur. A customer will only delete if they have no appointments 
scheduled. If appointments are schedule you will be prompted so.

## Appointment Functionality
The initial menu will provide a table with a total overview of all appointment data within your database. This table
can be filtered for all appointments, appointments within the month, and appointments within the week by the
respective radio buttons in the top left.
### Add an appointment
To add an appointment, from the Appointment Menu click the add button in the bottom left. A new screen will load. 
Appointment creation can only be completed if all fields are filled in and all combo boxes have a selection. If any are
left blank you will be prompted to fix before continuing. Start and end date/time must meet the following criteria:
start must be before end, start cannot be in the past, and appointment must not overlap other appointments for the
same customer. Upon a successful save of a new appointment you will be directed back to Appointment Menu which will
now show your new appointment.
### Modify an appointment
To modify an appointment, from the Appointment Menu click an appointment to modify and then click the modify button 
in the bottom left. If no appointment is selected, navigation will not occur. Once in the Modify Menu, you may change 
any of the fields or combo box selections. Again all fields must be filled in and all combo boxes selected. All start
and end date/time checks will still be completed. Upon a successful save of the modified appointment you will be 
redirected back to the appointment Menu which will now show your modified appointment.
### Delete an appointment
To delete an appointment, from the Appointment Menu click an appointment to delete and then click the delete button in 
the bottom left. If no appointment is selected, no deletion will occur. Upon successful deletion the user will be 
prompted.

## Report Functionality
The report menu has three reports. The contact schedule was required by the company. It initializes with all 
appointment data but can be directed to provided appointment data only for a selected contact. To select a contact, use
the combo box found in the upper right. The customer appointment by month and type count report was required by the
company. It gives a count for each type of appointment for each month. The expired appointments is the additional 
custom report requested. Details can be found above.
