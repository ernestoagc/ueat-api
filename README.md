# UEAT API

#### **1.  Image docker**
I build the image docker: -t ernestoagc/soaint-prueba:0.4 .

also you can download these version on 
docker pull ernestoagc/ueat-api:0.1

![](https://i.imgur.com/If1lZLE.jpg)

#### **2. Base de datos y script.**
In the resource folder  you will find the script to restore de database for these application


![](https://i.imgur.com/5WVa2id.jpg)

#### **3. Levantar ambiente de prueba para probar aplicacion**
Se van a utilizar dos contenedores para probar la aplicacion, la primera es para hospedar el servicio de la base de datos y la segunda para levantar la aplicacion

**running mysql container**
a) execute : 
*docker container run -d --network ueat-red  --name dbUeatTest -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password123 mysql:8.0*

b) Enter into console container:  *docker exec -it dbUeatTest  /bin/sh*

c) Put mysql credentials: 
  -mysql -u root -p;
   -password123
   
 d) create database: CREATE DATABASE dbUeat;
 
 **Running container of application**
 execute: 
*docker run -p 8700:8700  -d --link dbUeatTest:mysql  --network ueat-red  -e spring.profiles.active=test  --name=ueat-api ernestoagc/ueat-api:0.1*
