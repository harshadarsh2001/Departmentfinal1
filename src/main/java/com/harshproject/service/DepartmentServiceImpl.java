package com.harshproject.service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import com.harshproject.dto.DepartmentDTO;
import com.harshproject.entity.Department;
import com.harshproject.repository.Repository;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private Repository departmentRepository;


    @Override
    public List<DepartmentDTO> getAllDepartmentDTOs() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DepartmentDTO> getDepartmentDTOById(Long id) {
        return departmentRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public DepartmentDTO saveDepartmentDTO(DepartmentDTO departmentDTO) {
        Department department = convertToEntity(departmentDTO);
        Department savedDepartment = departmentRepository.save(department);

        return convertToDTO(savedDepartment);
    }

    @Override
    public DepartmentDTO updateDepartmentDTO(Long id, DepartmentDTO updatedDepartmentDTO) {
        Optional<Department> existingDepartmentOptional = departmentRepository.findById(id);

        if (existingDepartmentOptional.isPresent()) {
            Department existingDepartment = existingDepartmentOptional.get();
            BeanUtils.copyProperties(updatedDepartmentDTO, existingDepartment);
            Department updatedDepartment = departmentRepository.save(existingDepartment);

            return convertToDTO(updatedDepartment);
        } else {
            throw new NotFoundException("Department with ID " + id + " not found");
        }
    }

    @Override
    public void deleteDepartmentDTO(Long id) {
        departmentRepository.deleteById(id);
    }

    private DepartmentDTO convertToDTO(Department department) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        BeanUtils.copyProperties(department, departmentDTO);
        return departmentDTO;
    }

    private Department convertToEntity(DepartmentDTO departmentDTO) {
        Department department = new Department();
        BeanUtils.copyProperties(departmentDTO, department);
        return department;
    }

}

