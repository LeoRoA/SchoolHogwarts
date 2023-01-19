package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public Collection<Faculty> getAllStudents() {
        return this.facultyService.getAllFaculty();
    }

    @GetMapping("/{id}")
    public Faculty getById(@PathVariable("id") long id) {
        return this.facultyService.getFaculty(id);
    }

    @GetMapping("/color/{color}")
    public Collection<Faculty> getByColor(@PathVariable("color") String color) {

        return this.facultyService.getAllFacultyByColor(color);
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return this.facultyService.addFaculty(faculty);
    }

    @PutMapping("/{id}")
    public Faculty updateFaculty (@PathVariable ("id") long id, @RequestBody  Faculty faculty){
        return this.facultyService.editFaculty(id, faculty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeFaculty(@PathVariable("id") long id){
        this.facultyService.removeFaculty(id);
        return  ResponseEntity.noContent().build();
    }
}
