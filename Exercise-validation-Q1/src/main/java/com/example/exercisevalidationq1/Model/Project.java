package com.example.exercisevalidationq1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    @NotEmpty(message = "ID cannot be Empty")
    @Size(min = 2,message = "ID cannot be less than 2")
    private String ID;
    @NotEmpty(message = "title cannot be Empty")
    @Size(min = 8,message = "title cannot be less than 8")
    private String title;
    @NotEmpty(message = "description cannot be Empty")
    @Size(min = 15,message = "description cannot be less than 15")
    private String description;
    @NotEmpty(message = "status cannot be Empty")
    @Pattern(regexp = "Not Started|Progress|Completed",message = "the status must be Not Stated or Progress or Completed")
    private String status;
    @NotEmpty(message = "company name cannot be Empty")
    @Size(min=6,message = "company name cannot be less than 6")
    private String companyName;

}