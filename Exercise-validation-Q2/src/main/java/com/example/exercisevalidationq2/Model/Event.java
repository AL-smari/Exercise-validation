package com.example.exercisevalidationq2.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@AllArgsConstructor
public class Event {
    @NotEmpty(message = "ID cannot be Empty")
    @Size(min = 2,message = "ID cannot be less than 2")
    private String ID;
    @NotEmpty(message = "description cannot be Empty")
    @Size(min = 15,message = "description cannot be less than 15")
    private String description;
    @NotNull(message = "the capacity cannot be Empty")
    @Min(value = 25,message = "capacity cannot be less than 25")
    private int capacity;
    @JsonFormat(pattern ="yyyy-MM-dd")
    @Future
    private LocalDateTime startDate ;
    @JsonFormat(pattern ="yyyy-MM-dd")
    private LocalDateTime endDate;

}