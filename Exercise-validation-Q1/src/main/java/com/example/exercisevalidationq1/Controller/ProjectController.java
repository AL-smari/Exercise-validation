package com.example.exercisevalidationq1.Controller;

import com.example.exercisevalidationq1.Model.Project;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    ArrayList<Project>projects=new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Project> getProjects(){

        return projects;
    }
    @PostMapping("/add")
    public ResponseEntity addProject(@Valid @RequestBody Project project, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        projects.add(project);
        return ResponseEntity.status(HttpStatus.OK).body("Project added");
    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateProject(@PathVariable int index,@Valid@RequestBody Project project,Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        projects.set(index,project);

        return ResponseEntity.status(HttpStatus.OK).body("Project updated");

    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteProject(@PathVariable int index){
        projects.remove(index);
        return ResponseEntity.status(HttpStatus.OK).body("Project deleted");
    }

    @PutMapping("/change-status/{index}")
    public ResponseEntity changeStatus(@PathVariable int index){
        if(projects.get(index).getStatus().equals("Not Started")){
            projects.get(index).setStatus("Progress");
        }else if(projects.get(index).getStatus().equals("Progress")){
            projects.get(index).setStatus("Completed");
        }else projects.get(index).setStatus("Not Started");

        return ResponseEntity.status(HttpStatus.OK).body("status changed");
    }
    @GetMapping("search-project/{title}")
    public ResponseEntity searchProject(@PathVariable String title){

        for(Project p:projects){
            if(p.getTitle().equals(title)){
                return ResponseEntity.status(HttpStatus.OK).body(p);
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body("there is no Project with this title");

    }

    @GetMapping("/getProjects/{companyName}")
    public ResponseEntity getProjectByCompany(@PathVariable String companyName){
        ArrayList<Project> p = new ArrayList<>();

        for(Project project : projects){
            if(project.getCompanyName().equals(companyName)){
                p.add(project);
            }
        }
        if(p.size()>0) {

            return ResponseEntity.status(HttpStatus.OK).body(p);
        }else return ResponseEntity.status(HttpStatus.OK).body("there is no Project with this company name");


    }



}