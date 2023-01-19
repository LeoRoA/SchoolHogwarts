package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.StudentNotFoundedException;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private long counter = 0L;
    private final Map<Long, Student> students = new HashMap<>();

    public Student addStudent (Student student){
        long newId=++this.counter;
        student.setId(newId);
        students.put(newId,student);
        return student;
    }

    public Student editStudent (Long id,Student student){
        if(this.students.containsKey(id)){
            Student oldStudent = this.students.get(id);
            oldStudent.setAge(student.getAge());
            oldStudent.setName(student.getName());
            return oldStudent;
        }
        else {
            throw new StudentNotFoundedException();
        }
    }

    public Student getStudent (Long id){
        if(this.students.containsKey(id)){
            return this.students.get(id);
        }
        else {
            throw new StudentNotFoundedException();
        }
    }

    public Collection<Student> getAllStudents (){
        return students.values();
    }

    public void removeStudent (Long id){
        if(this.students.containsKey(id)){
            this.students.remove(id);
        }
        else {
            throw new StudentNotFoundedException();
        }
    }

    public Collection<Student> getAllStudentByAge(int age) {
        return this.students.values()
                .stream()
                .filter(s-> s.getAge() == age)
                .collect(Collectors.toList());
    }
}
