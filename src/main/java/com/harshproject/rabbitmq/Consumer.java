package com.harshproject.rabbitmq;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harshproject.dto.DepartmentDTO;
import com.harshproject.service.DepartmentService;
import java.util.Optional;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class Consumer {

    @Autowired
    private DepartmentService departmentService;

    @RabbitListener(queues = "department-queue")
    public void receiveDepartmentMessage(String message) {
        System.out.println("Received department message: " + message);

        // Convert JSON message to DepartmentDTO
        DepartmentDTO receivedDepartment = convertJsonToDepartmentDTO(message);

        // Process the received DepartmentDTO for save or update
        processReceivedDepartment(receivedDepartment);
    }

    public static DepartmentDTO convertJsonToDepartmentDTO(String json) {
        try {
            // Use Jackson ObjectMapper to convert JSON to DepartmentDTO
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, DepartmentDTO.class);
        } catch (Exception e) {
            // Handle the exception appropriately (e.g., log it)
            e.printStackTrace();
            return null; // Return null or throw an exception based on your requirements
        }
    }

    private void processReceivedDepartment(DepartmentDTO departmentDTO) {
        if (departmentDTO != null) {
            // Check if the ID is present
            if (departmentDTO.getId() != null) {
                // If ID is present, it's an update operation
                handleUpdateOperation(departmentDTO);
            } else {
                // If ID is null, it's a save operation
                handleSaveOperation(departmentDTO);
            }
        } else {
            System.out.println("Failed to process department. DepartmentDTO is null.");
            // Handle the case where deserialization failed or departmentDTO is null
        }
    }

    private void handleSaveOperation(DepartmentDTO departmentDTO) {
        // Save the received departmentDTO to the database using DepartmentService
        departmentService.saveDepartmentDTO(departmentDTO);
        System.out.println("Saved department to the database: " + departmentDTO.toString());
        // Add your additional processing logic here if needed
    }

    private void handleUpdateOperation(DepartmentDTO departmentDTO) {
        // Update the department in the database using DepartmentService
        Long departmentId = departmentDTO.getId();
        Optional<DepartmentDTO> existingDepartment = departmentService.getDepartmentDTOById(departmentId);

        if (existingDepartment.isPresent()) {
            departmentService.updateDepartmentDTO(departmentId, departmentDTO);
            System.out.println("Updated department in the database: " + departmentDTO.toString());
            // Add your additional processing logic here if needed
        } else {
            System.out.println("Failed to update department. Department with ID " + departmentId + " not found.");
            // Handle the case where the department with the given ID is not found
        }
    }

}
