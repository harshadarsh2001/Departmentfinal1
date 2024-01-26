
package com.harshproject.service;

import java.util.List;
import java.util.Optional;

import com.harshproject.dto.DepartmentDTO;

public interface DepartmentService {

    List<DepartmentDTO> getAllDepartmentDTOs();

    Optional<DepartmentDTO> getDepartmentDTOById(Long id);

    DepartmentDTO saveDepartmentDTO(DepartmentDTO departmentDTO);

    Optional<DepartmentDTO> updateDepartmentDTO(Long id, DepartmentDTO updatedDepartmentDTO);

    void deleteDepartmentDTO(Long id);
}
