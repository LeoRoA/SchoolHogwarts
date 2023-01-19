package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyNotFoundedException;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private long counter = 0L;
    private final Map<Long, Faculty> faculties = new HashMap<>();

    public Faculty addFaculty (Faculty faculty){
        long newId=++this.counter;
        faculty.setId(newId);
        faculties.put(newId,faculty);
        return faculty;

    }

    public Faculty editFaculty (Long id,Faculty faculty){
        if(this.faculties.containsKey(id)){
            Faculty oldFaculty = this.faculties.get(id);
            oldFaculty.setColor(faculty.getColor());
            oldFaculty.setName(faculty.getName());
            return oldFaculty;
        }
        else {
            throw new FacultyNotFoundedException();
        }
    }

    public Faculty getFaculty (Long id){
        if(this.faculties.containsKey(id)){
            return this.faculties.get(id);
        }
        else {
            throw new FacultyNotFoundedException();
        }
    }

    public Collection<Faculty> getAllFaculty (){
        return faculties.values();
    }

    public void removeFaculty (Long id){
        if(this.faculties.containsKey(id)){
            this.faculties.remove(id);
        }
        else {
            throw new FacultyNotFoundedException();
        }
    }

    public Collection<Faculty> getAllFacultyByColor(String color) {
        return this.faculties.values()
                .stream()
                .filter(s-> s.getColor().equals(color))
                .collect(Collectors.toList());
    }
}
