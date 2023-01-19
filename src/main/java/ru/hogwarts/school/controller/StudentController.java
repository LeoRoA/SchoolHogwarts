package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public Collection<Student> getAllStudents() {
        return this.studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable("id") long id) {
        return this.studentService.getStudent(id);
    }

    @GetMapping("/age/{age}")
    public Collection<Student> getByAge(@PathVariable("age") int age) {

        return this.studentService.getAllStudentByAge(age);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return this.studentService.addStudent(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent (@PathVariable ("id") long id, @RequestBody  Student student){
        return this.studentService.editStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeStudent(@PathVariable("id") long id){
        this.studentService.removeStudent(id);
        return  ResponseEntity.noContent().build();
    }


}
