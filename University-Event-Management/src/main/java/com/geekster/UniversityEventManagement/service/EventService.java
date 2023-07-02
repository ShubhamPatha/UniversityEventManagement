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
