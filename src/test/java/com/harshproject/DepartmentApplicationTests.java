
package com.harshproject;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harshproject.controller.AuthController;
import com.harshproject.controller.DepartmentController;
import com.harshproject.dto.DepartmentDTO;
import com.harshproject.service.AuthService;
import com.harshproject.service.DepartmentService;
import com.harshproject.service.JwtService;

class DepartmentApplicationTests {
	
	@Mock
    private AuthService authService;

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;
    
    @InjectMocks
    private AuthController authController;

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public DepartmentApplicationTests() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();
    }

    @Test
    void testGetAllDepartments() throws Exception {
        when(departmentService.getAllDepartmentDTOs()).thenReturn(Arrays.asList(
                new DepartmentDTO(1L, "IT", "Information Technology"),
                new DepartmentDTO(2L, "HR", "Human Resources")
        ));

        mockMvc.perform(get("/department"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("IT"))
                .andExpect(jsonPath("$[1].description").value("Human Resources"));

        verify(departmentService, times(1)).getAllDepartmentDTOs();
        verifyNoMoreInteractions(departmentService);
        
        
    }

    @Test
    void testGetDepartmentById() throws Exception {
        Long departmentId = 1L;
        DepartmentDTO departmentDTO = new DepartmentDTO(departmentId, "IT", "Information Technology");

        when(departmentService.getDepartmentDTOById(departmentId)).thenReturn(Optional.of(departmentDTO));

        mockMvc.perform(get("/department/{id}", departmentId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("IT"))
                .andExpect(jsonPath("$.description").value("Information Technology"));

        verify(departmentService, times(1)).getDepartmentDTOById(departmentId);
        verifyNoMoreInteractions(departmentService);
    }

    @Test
    void testGetDepartmentByIdNotFound() throws Exception {
        Long nonExistingDepartmentId = 99L;

        when(departmentService.getDepartmentDTOById(nonExistingDepartmentId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/department/{id}", nonExistingDepartmentId))
                .andExpect(status().isNotFound());

        verify(departmentService, times(1)).getDepartmentDTOById(nonExistingDepartmentId);
        verifyNoMoreInteractions(departmentService);
    }

    @Test
    void testSaveDepartment() throws Exception {
        DepartmentDTO departmentDTO = new DepartmentDTO(null, "New Department", "Finance");
        DepartmentDTO savedDepartmentDTO = new DepartmentDTO(1L, "New Department", "Finance");

        when(departmentService.saveDepartmentDTO(any(DepartmentDTO.class))).thenReturn(savedDepartmentDTO);

        mockMvc.perform(post("/department")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(departmentDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("New Department"))
                .andExpect(jsonPath("$.description").value("Finance"));

        verify(departmentService, times(1)).saveDepartmentDTO(any(DepartmentDTO.class));
        verifyNoMoreInteractions(departmentService);
    }

    @Test
    void updateDepartment_shouldReturnNotFound_whenDepartmentIsNotUpdated() throws Exception {
        // Arrange
        Long departmentId = 2L;
        DepartmentDTO updatedDepartmentDTO = new DepartmentDTO(/* construct your updated department DTO */);

        // Mock the service call to return null, indicating not found
        when(departmentService.updateDepartmentDTO(anyLong(), any(DepartmentDTO.class)))
                .thenReturn(null);

        // Act
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestJson = objectMapper.writeValueAsString(updatedDepartmentDTO);

        RequestEntity
                .put("/department/{id}" + departmentId)
                .headers(headers)
                .body(requestJson);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders.put("/department/{id}", departmentId)
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testDeleteDepartment() throws Exception {
        Long departmentId = 1L;

        mockMvc.perform(delete("/department/{id}", departmentId))
                .andExpect(status().isNoContent());

        verify(departmentService, times(1)).deleteDepartmentDTO(departmentId);
        verifyNoMoreInteractions(departmentService);
    }
    @Test
    void updateDepartment_shouldReturnOk_whenDepartmentIsUpdatedSuccessfully() throws Exception {
        // Arrange
        Long departmentId = 1L;
        DepartmentDTO updatedDepartmentDTO = new DepartmentDTO(departmentId, "Updated IT", "Updated Information Technology");

        // Mock the service call
        when(departmentService.updateDepartmentDTO(anyLong(), any(DepartmentDTO.class)))
                .thenReturn(updatedDepartmentDTO);

        // Act & Assert
        mockMvc.perform(put("/department/{id}", departmentId)
                .content(objectMapper.writeValueAsString(updatedDepartmentDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(departmentId))
                .andExpect(jsonPath("$.name").value("Updated IT"))
                .andExpect(jsonPath("$.description").value("Updated Information Technology"));

        verify(departmentService, times(1)).updateDepartmentDTO(anyLong(), any(DepartmentDTO.class));
        verifyNoMoreInteractions(departmentService);
    }

    @Test
    void updateDepartment_shouldReturnNotFound_whenDepartmentIsNotUpdated1() throws Exception {
        // Arrange
        Long departmentId = 2L;
        DepartmentDTO updatedDepartmentDTO = new DepartmentDTO(/* construct your updated department DTO */);

        // Mock the service call to return null, indicating not found
        when(departmentService.updateDepartmentDTO(anyLong(), any(DepartmentDTO.class)))
                .thenReturn(null);

        // Act & Assert
        mockMvc.perform(put("/department/{id}", departmentId)
                .content(objectMapper.writeValueAsString(updatedDepartmentDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(departmentService, times(1)).updateDepartmentDTO(anyLong(), any(DepartmentDTO.class));
        verifyNoMoreInteractions(departmentService);
    }

    @Test
    void deleteDepartment_shouldReturnNoContent_whenDepartmentIsDeletedSuccessfully() throws Exception {
        // Arrange
        Long departmentId = 1L;

        // Act & Assert
        mockMvc.perform(delete("/department/{id}", departmentId))
                .andExpect(status().isNoContent());

        verify(departmentService, times(1)).deleteDepartmentDTO(departmentId);
        verifyNoMoreInteractions(departmentService);
    }
    @Test
    void saveDepartment_shouldReturnCreated_whenDepartmentIsSavedSuccessfully() throws Exception {
        // Arrange
        DepartmentDTO departmentDTO = new DepartmentDTO(null, "New IT", "New Information Technology");
        DepartmentDTO savedDepartmentDTO = new DepartmentDTO(1L, "New IT", "New Information Technology");

        // Mock the service call
        when(departmentService.saveDepartmentDTO(any(DepartmentDTO.class)))
                .thenReturn(savedDepartmentDTO);

        // Act & Assert
        mockMvc.perform(post("/department")
                .content(objectMapper.writeValueAsString(departmentDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("New IT"))
                .andExpect(jsonPath("$.description").value("New Information Technology"));

        verify(departmentService, times(1)).saveDepartmentDTO(any(DepartmentDTO.class));
        verifyNoMoreInteractions(departmentService);
    }
    @Mock
    private JwtService jwtService;
    
    @Mock
    private AuthenticationManager authenticationManager;
    

}
