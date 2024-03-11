

package com.pornmanut.taskmanager.presentation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class TaskRequest {

    @NotBlank(message = "Name is mandatory")
    private String name;
    private String description;

    @NotBlank(message = "Status should be one of TODO, DOING, DONE")
    @Pattern(regexp = "TODO|DOING|DONE", message = "Status should be one of TODO, DOING, DONE")
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
