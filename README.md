# CSYE6220 Enterprise Software Design
## Spring MVC, Hibernate Web Application 						   	  
1.Built a complete dating web application with a multi-roles login system  
2.Created a database to collect the user information from the web application  
3.Established a user login system offering different functions for multiple user roles  
4.Created an interface allowing administrators to manage the database

  
### There are three roles in my project:
1. User: can use comment function  
2. Prime user: can use appointment and google map function  
3. Admin: can confirm appointment and manage use  

### Overall design:
1.This project was design for three kinds of users: user, prime, and admin  
I have a controller-service-Dao chain to handle the role for each user   
2.Two main functions to access to database:  
  &nbsp (1) Allow user to create and check comment  
  &nbsp (2) Allow user to create edit, and check appointment  
3.Two controller-service-Dao chains to handle the function  
  (1)Webcontent/Resource to hold all of the third part resource like JS file and CSS  
  (2)Each function was separate from other function. So error in one function can not impact other. Beside, this design is re-useable. I can put one function into another project without too much change    

### Technology:
1. Spring MVC  
2. Hibernate  
3. Spring security  
4. Maven  
5. JQuery  

### Structure: 
#### Front-end structure:
1.Home page  
2.Login page - New account page  
3.Comment page - Check comment page  
4.appointment page (prime and admin only) - New appointment page / edit appointment page - Check appointment page  
5.Google map page (prime and admin only)  
6.Confirm appointment page(admin only) - Appointment list  
7.Admin page(admin only) - User list - Management page

#### Back-end structure:
1. Domain package:   
hold three entities: User, Comment, and Appointment  
2. Controller layer:  
Three controller:Usercontroller, Commentcontroller, and Appointmentcontroller  
And one error handler  
3. Service layer  
Three service: UserService , CommentService , and AppointmentService  
4. Dao layer  
Three Dao: UserDao , CommentDao , and AppointmentDao  
5. Config package  
Dao, security, and service  
6. Resource:  
JS file, Css file  
7. JSP : 20 jsp pages  
8. Servlet to fix the URL and web.xml  
9. Maven dependency  

### Database structure:
#### Three tables:
1 Users: username(PK), name, gender, email, password, authority, enable  
2 Comment: id(PK), subject, comment, username(FK from users)  
3 Appointment: id(PK), time, detail, confirm, username(FK from users)  
 ![database](https://github.com/gobackhuoxing/Dating-Web-CSYE6220/blob/master/picture/database.jpg)
 
#### Functions:
The main functions include:  
1. Allow user to sign in and log in their personal account  
2. Allow user to create and check comment  
3. Allow user to create edit, and check appointment  
4. Google map  
5. Allow admin to check, confirm or deny appointment  
6. Allow admin to check user list and change authority and enable of user  

