package com.SpringBootCrud.SpringBootCrud.controller;

import com.SpringBootCrud.SpringBootCrud.model.Employee;
import com.SpringBootCrud.SpringBootCrud.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
@AutoConfigureMockMvc
public class EmployeeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepository employeeRepository;

    private Employee testEmployee;

    @BeforeEach
    void setUp() {
        // Crear un empleado de prueba para usar en las pruebas
        testEmployee = new Employee(1L, "John", "Doe", "john.doe@example.com");
    }

    @Test
    void testGetAllEmployees() throws Exception {
        when(employeeRepository.findAll()).thenReturn(List.of(testEmployee));

        mockMvc.perform(get("/api/v1/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].firstName").value("John"));
    }

    @Test
    void testCreateEmployee() throws Exception {
        when(employeeRepository.save(any(Employee.class))).thenReturn(testEmployee);

        mockMvc.perform(post("/api/v1/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"firstName\": \"John\", \"lastName\": \"Doe\", \"emailId\": \"john.doe@example.com\" }"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void testGetEmployeeById() throws Exception {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(testEmployee));

        mockMvc.perform(get("/api/v1/employees/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void testUpdateEmployee() throws Exception {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(testEmployee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(testEmployee);

        mockMvc.perform(put("/api/v1/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"firstName\": \"Updated John\", \"lastName\": \"Doe\", \"emailId\": \"john.doe@example.com\" }"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Updated John"));
    }

    @Test
    void testDeleteEmployee() throws Exception {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(testEmployee));

        mockMvc.perform(delete("/api/v1/employees/1"))
                .andExpect(status().isNoContent());
    }
}
