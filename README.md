# Pract1_SistemesOberts
Pràctica 1 de l'assignatura Sistemes Oberts (Enginyeria Informàtica URV)

To use this repository you'll need:
  * JDK 8 (or higher)
  * Netbeans IDE 8.2 version EE (or higher)
  * Glassfish server 4.1.1
  * Java DB

If you have all of this you'll be able to run this web application.
Follow this steps to run this project:
  1. Go to Services tag and create a JavaDB called Homework1 with username and password ROOT.
  2. Connect this DataBase.
  3. Go to Project tag and right click above the name of this project (Pract1_SistemesOberts) and click Deploy.
  4. Go to your web browser and in the URL write http://localhost:8080/Pract1_SistemesOberts
  5. Click on insert button. This button executes a JSP file and inserts rows to DB Homework1. In this step if you see database you'll be able to see all tables full of data.
This is basically how to run this project. 

If you want to make REST calls just follow this link for POSTMAN client and run our test (https://www.getpostman.com/collections/9513faf733d5abbf68bf)

IMPORTANT: Room REST API does not need autentication! But if you want to see information of tenants and you don't have autenticate you'll not see anything.
To authenticate follow this steps:
  1. Run in POSTMAN: POST /webresources/autenticacio with username (cesc | aleix) and password (copy password that install.jsp generates) form params. This will return a token
  2. Run in POSTMAN: POST /webresources/tenant/processarToken and just pass an JSON with this format:
        { "tokenAutoritzacio" : "token" }
     Now you can make all REST calls of tenants!
     
ENJOY IT!

Developers: Francesc Ferré Tarrés and Aleix Sancho Pujals
