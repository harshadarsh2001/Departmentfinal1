package com.harshproject.controller;

import com.harshproject.dto.DepartmentDTO;
import com.harshproject.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/department")
@SecurityRequirement(name = "bearer-key")
public class DepartmentController {
    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(summary = "Get all departments", description = "Retrieve a list of all departments")
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments(RequestEntity<Void> requestEntity) {
        HttpHeaders headers = requestEntity.getHeaders();
        logger.info("Fetching all Departments. Request Headers: {}", headers);

        List<DepartmentDTO> departmentDTOs = departmentService.getAllDepartmentDTOs();
        return new ResponseEntity<>(departmentDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(summary = "Get department by ID", description = "Retrieve a department by its ID")
    public ResponseEntity<DepartmentDTO> getDepartmentById(
            @Parameter(name = "id", description = "ID of the department", in = ParameterIn.PATH, required = true)
            @PathVariable long id, RequestEntity<Void> requestEntity) {
        HttpHeaders headers = requestEntity.getHeaders();
        logger.info("Fetching department by ID: {}. Request Headers: {}", id, headers);

        Optional<DepartmentDTO> departmentDTOOptional = departmentService.getDepartmentDTOById(id);
        return departmentDTOOptional.map(departmentDTO -> new ResponseEntity<>(departmentDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PutMapping("/{id}")
    @Operation(summary = "Update department by ID", description = "Update a department based on its ID")
    public ResponseEntity<DepartmentDTO> updateDepartment(
            @Parameter(name = "id", description = "ID of the department", in = ParameterIn.PATH, required = true)
            @PathVariable Long id,
            @Parameter(name = "updatedDepartmentDTO", description = "Updated department information", required = true)
            @RequestBody DepartmentDTO updatedDepartmentDTO, RequestEntity<Void> requestEntity) {
        HttpHeaders headers = requestEntity.getHeaders();
        logger.info("Updating department with ID {}: {}. Request Headers: {}", id, updatedDepartmentDTO, headers);

        DepartmentDTO updated = departmentService.updateDepartmentDTO(id, updatedDepartmentDTO);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            // Return 403 Forbidden when the ID is not found for updating
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PreAuthorize("hasAuthority('ROLE_USER')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete department by ID", description = "Delete a department based on its ID")
    public ResponseEntity<Void> deleteDepartment(
            @Parameter(name = "id", description = "ID of the department", in = ParameterIn.PATH, required = true)
            @PathVariable Long id, RequestEntity<Void> requestEntity) {
        HttpHeaders headers = requestEntity.getHeaders();
        logger.info("Deleting department with ID: {}. Request Headers: {}", id, headers);

        departmentService.deleteDepartmentDTO(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(summary = "Save new department", description = "Save a new department")
    public ResponseEntity<DepartmentDTO> saveDepartment(
            @Parameter(name = "departmentDTO", description = "New department information", required = true)
            @RequestBody DepartmentDTO departmentDTO, RequestEntity<Void> requestEntity) {
        HttpHeaders headers = requestEntity.getHeaders();
        logger.info("Saving a new Department: {}. Request Headers: {}", departmentDTO, headers);

        DepartmentDTO savedDepartmentDTO = departmentService.saveDepartmentDTO(departmentDTO);

        return new ResponseEntity<>(savedDepartmentDTO, HttpStatus.CREATED);
    }
}


