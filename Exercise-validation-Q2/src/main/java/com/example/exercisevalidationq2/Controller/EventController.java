package com.example.exercisevalidationq2.Controller;


import com.example.exercisevalidationq2.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    ArrayList<Event>events=new ArrayList<>();
    @GetMapping("/get")
    public ArrayList<Event> getEvents (){

        return events;
    }

    @PostMapping("/add")
    public ResponseEntity addEvent(@Valid @RequestBody Event event, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        events.add(event);
        return ResponseEntity.status(HttpStatus.OK).body("Event added");
    }
    @PutMapping("update/{index}")
    public ResponseEntity updateEvent(@PathVariable int index, @Valid @RequestBody Event event,Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        events.set(index,event);

        return ResponseEntity.status(HttpStatus.OK).body("Event updated");
    }
    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEvent(@PathVariable int index){

        events.remove(index);

        return ResponseEntity.status(HttpStatus.OK).body("Event deleted");

    }

    @PutMapping("/change-capacity/{index}/{capacity}")
    public ResponseEntity changeCapacity(@PathVariable int index,@PathVariable int capacity){
        if(capacity>=25) {
            events.get(index).setCapacity(capacity);

            return ResponseEntity.status(HttpStatus.OK).body("Capacity changed");
        }else return ResponseEntity.status(HttpStatus.OK).body("capacity cannot be less than 25");

    }


    @GetMapping("get-events/{ID}")
    public ResponseEntity getEvents(@PathVariable String ID){

        for(Event e : events){
            if(e.getID().equals(ID)){
                return ResponseEntity.status(HttpStatus.OK).body(e);
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body("there is no Event with this ID");
    }
}
