# UniversityEventManagement


## FRAMEWORK AND LANGUAGE USED
* JAVA 17
* MAVEN
* SPRINGBOOT 3.1.1
* h2 database
<!-- Headings -->   
## DATA FLOW

<!-- Code Blocks -->

### CONTROLLER
  ``` 
package com.geekster.UniversityEventManagement.controller;

import com.geekster.UniversityEventManagement.model.Event;
import com.geekster.UniversityEventManagement.model.Student;
import com.geekster.UniversityEventManagement.model.Type;
import com.geekster.UniversityEventManagement.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping("AllStudent")
    public Iterable<Student> getAllStud()
    {
        System.out.println("done till AllStudent");
        return eventService.getAllStudent();
    }
    @GetMapping("AllStudent/{id}")
    public Iterable<Student> getAllStudbyid(Integer Id)
    {
        System.out.println("done till AllStudent");

        return eventService.getAllStudentbyid(Id);
    }



    @PostMapping("AddStudent")
    public String addStudent(@RequestBody @Valid Student stud)
    {
        return eventService.addstud(stud);
    }
    @PutMapping("Student/{id}")
    public String updateStudentDepartment(@PathVariable Integer id,@PathVariable Type type){
        return eventService.updatestudentdepartmentbyid(id,type);
    }

    @DeleteMapping("Student/{id}")
    public String deletestudent(@PathVariable Integer id)

    {
        return eventService.deleteStudentById(id);
    }




    @GetMapping("AllEvent")
    public Iterable<Event> getAllEve()
    {
        System.out.println("done till AllEvent");
        return eventService.getAllEvent();
    }

    @PostMapping("AddEvent")
    public String addEvent(@RequestBody @Valid Event eve)
    {
        return eventService.addeven(eve);
    }
    @PutMapping("Event/{id}")
    public String updateEvent(@PathVariable Integer id,@PathVariable Event eve){
        return eventService.updateEventbyid(id,eve);
    }

    @DeleteMapping("Student/{id}")
    public String deleteEvent(@PathVariable Integer id)

    {
        return eventService.deleteEventById(id);
    }





}

```


 ### MODEL
  #### Event
  ``` 
package com.geekster.UniversityEventManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Event")
public class Event {

    @Id
    private Integer EventId;
    private String EventName;
    private String EventLocation;
    private LocalDate EventDate;//EX : 2007-12-03.
    private String EventStartTime;
    private String EventEndTime;


}



```
#### Student
  ```
package com.geekster.UniversityEventManagement.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Student")

public class Student {


    @Id
    private Integer studentId;
    private String studentFirstName;
    private String studentLastName;

    @Min(value = 18)
    @Max(value = 30)
    private Integer age;
    private Type StudentDepartment;


}





```
#### Type
  ```
package com.geekster.UniversityEventManagement.model;

public enum Type {
    ME ,
    ECE ,
    civil ,
    CSE
}


```


### REPO
#### Event Repo
  ``` 
package com.geekster.UniversityEventManagement.repository;
import com.geekster.UniversityEventManagement.model.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface  EventRep extends CrudRepository<Event, Integer> {

}

```
#### Studentrepo
  ``` 
package com.geekster.UniversityEventManagement.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.geekster.UniversityEventManagement.model.Student;


@Repository
public interface  Studentrepo extends CrudRepository<Student,Integer> {



}

```


### SERVICE
  ``` 
package com.geekster.UniversityEventManagement.service;

import com.geekster.UniversityEventManagement.model.Event;
import com.geekster.UniversityEventManagement.model.Student;
import com.geekster.UniversityEventManagement.model.Type;
import com.geekster.UniversityEventManagement.repository.EventRep;

import com.geekster.UniversityEventManagement.repository.Studentrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventService {
    @Autowired
    EventRep eventrepo;
    @Autowired
    Studentrepo studentRepo;

    public Iterable<Student> getAllStudent() {
        return studentRepo.findAll();
    }


    public String addstud(Student stu) {
        studentRepo.save(stu);

        return "added";
    }





    public String updatestudentdepartmentbyid(Integer id, Type type) {
        Optional<Student> myRoomOpt = studentRepo.findById(id);
        Student myRoom = null;
        if(myRoomOpt.isPresent())
        {
            myRoom = myRoomOpt.get();
        }
        else {
            return "Id not Found!!!";
        }
        myRoom.setStudentDepartment(type);
        studentRepo.save(myRoom);
        return "room type updated";
    }



    public String deleteStudentById(Integer id) {
        studentRepo.deleteById(id);
        return "room deleted successfully of id "+id;
    }





    public Iterable<Event> getAllEvent() {
        return eventrepo.findAll();
    }

    public String addeven(Event eve)
    {
        eventrepo.save(eve);
        return "added event";

    }

    public String updateEventbyid(Integer id, Event eve) {


        Optional<Event> myEveOpt = eventrepo.findById(id);
        Event myeve = null;
        if(myEveOpt.isPresent())
        {
            myeve = myEveOpt.get();
            eventrepo.delete(myeve);
        }
        else {
            return "Id not Found!!!";
        }

        eventrepo.save(eve);
        return "Event  updated";
    }

    public String deleteEventById(Integer id) {
        studentRepo.deleteById(id);
        return "room deleted successfully of id "+id;
    }

    public Iterable<Student> getAllStudentbyid(Integer id) {

        Iterable<Student> myRoomOpt = studentRepo.findAll();
        Student myRoom = null;

        for(Student str:myRoomOpt)
        {
        if(str.getStudentId().equals(id))
        {
            myRoom = str;
        }}
        return (Iterable<Student>) myRoom;
    }
}

```


### MAIN
  ``` 
package com.geekster.UniversityEventManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UniversityEventManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniversityEventManagementApplication.class, args);
	}

}

}



```



 ### Application Properties
  ```

spring.datasource.url = jdbc:h2:mem:h2db
spring.datasource.driverClassName = org.h2.Driver
spring.datasource.userName = shubham
spring.datasource.password = shubham
spring.jpa.database-platform = org.hibernate.dialect.H2Dialect
spring.h2.console.enabled = true


spring.jpa.properties.hibernate.show_sql = true
spring.jpa.properties.hibernate.use_sql_comments=true
spring.jpa.properties.hibernate.format_sql=true

#EnableH2Console-http://localhost:8080/h2-console

```
 ### POM
  ``` 
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.1.1</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.geekster</groupId>
	<artifactId>University-Event-Management</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>University-Event-Management</name>
	<description>uem project</description>
	<properties>
		<java.version>17</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
			</plugin>
		</plugins>
	</build>

</project>

```
## DATA STRUCTURE USED
* LIST 
* STRING
* LOCAL DATE
* INTEGER
* USER

# PROJECT SUMMARY

## USER MANAGEMENT SYSTEM Has been created with the following attribute

* UserId
* username
* DateOfBirth
* Email
* Phone Number
* Date 
* Time
### Endpoint to be provided 
* AddUser 
* getUser/{userid}
* getAllUser
* updateUserInfo
* deleteUser









<!-- Headings -->   
# Author
SHUBHAM PATHAK
 <!-- UL -->
* Twitter <!-- Links -->
[@ShubhamPathak]( https://twitter.com/Shubham15022000)
* Github  <!-- Links -->
[@ShubhamPathak]( https://github.com/ShubhamPatha)
<!-- Headings -->   
