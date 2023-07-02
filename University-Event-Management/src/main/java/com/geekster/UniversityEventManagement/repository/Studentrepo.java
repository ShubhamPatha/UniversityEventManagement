package com.geekster.UniversityEventManagement.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.geekster.UniversityEventManagement.model.Student;


@Repository
public interface  Studentrepo extends CrudRepository<Student,Integer> {



}