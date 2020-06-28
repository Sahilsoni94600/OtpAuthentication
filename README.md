####**Task : Create API endpoints for Login flow via OTP being sent to the email**

Consider a database of users with the following fields:

* username (unique, length 255)
* password
* email
* contact number

You have to build a relevant table(s) to store the above data in Mysql. You have to figure out a way to store passwords in the database. It should not be stored as plain text. Consider some dummy users and then create a login application.

Users can log in using their email. If the email is present in the database, then the user should be sent an email which contains the OTP. Upon entering the correct OTP, display a welcome screen, with the user email and username. You can use relevant Api to send an email. 

Required Endpoints
The API should feature the following endpoint functionality -

The OTP Request body should contain an email. After which an OTP is sent to the email
The Login Request body should contain an email/otp pair. After which the welcome screen should be displayed.

The final application should be hosted on a publicly accessible URL.  You should also give a document detailing your approach and major architecture decisions/tradeoffs which you have taken throughout this task.





-> While reading given requirement of end points I decide to use spring boot. The reason spring Boot is easy create stand-alone application. 
   To store the data into system I used JPA api. It helped me to directly deal with entities.
   The most important part of the application was while developing is to store the OTP in cache and for this I found that google gives an API to store into cache. 
   Basically this case is key value pair map. Here The key -> username and value -> otp 
   API offers how long the data is persist in cache. I setup for 60 second for now. 


Finally, to send the email to the user I used JavaMailSender java api. It was very simple. I just need to provide the smtp configuration in application.properties

While trying to use the application please update the application.properties.

The design of the application is like following - 

LoginController -> OtpService/UserService/EmailService 

Login controller is exposing end point to generate the OTP.
Login Controller also expose another end point to validate the OTP. 

AppStarter.java is the starting point of the application just run the main method and application will start. 

As soon as app start I'm storing dummy user data into the table. So it will help you to test it. 




###### Create table with following scripts. Here username is unique key!! 

`drop table if exists tab_users cascade;`

`create table tab_users (id  bigserial not null, contact_number varchar(30) not null, email varchar(200) not null, password varchar(200) not null, username varchar(30) not null, primary key (id));
`


]
`alter table tab_users add constraint UK7ak70pk9jtoxnktt93310wc92 unique (username);`

`create sequence tab_users_id_seq;`

Insert dummy user data into table 

`insert into tab_users (id, contact_number, email, password, username) values (nextval('tab_users_id_seq'), '1234', 'abcd@gmail.com', 'pass', 'sahil.soni');`

