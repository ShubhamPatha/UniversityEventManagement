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
