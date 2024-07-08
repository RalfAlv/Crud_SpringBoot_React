package com.SpringBootCrud.SpringBootCrud.controller;

import com.SpringBootCrud.SpringBootCrud.model.Employee;
import com.SpringBootCrud.SpringBootCrud.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class EmployeeControllerUnitTest {
    /*PRUEBAS UNITARIAS*/

    private MockMvc mockMvc;

    @Mock
    private EmployeeRepository employeeRepository; // Mock del repositorio

    @InjectMocks
    private EmployeeController employeeController; // La clase bajo prueba

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // Inicializar los mocks
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    void getAllEmployees() throws Exception {
        // Datos de ejemplo
        Employee employee1 = new Employee(1L, "John", "Doe", "john@example.com");
        Employee employee2 = new Employee(2L, "Jane", "Smith", "jane@example.com");
        List<Employee> employees = Arrays.asList(employee1, employee2);

        // Configurar el comportamiento del mock
        when(employeeRepository.findAll()).thenReturn(employees);

        // Llamar al método del controlador y verificar el resultado
        mockMvc.perform(get("/api/v1/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void createEmployee() throws Exception {
        // Datos de ejemplo en formato JSON
        String employeeJson = "{\"firstName\":\"John\",\"lastName\":\"Doe\",\"emailId\":\"john@example.com\"}";
        Employee employee = new Employee(1L, "John", "Doe", "john@example.com");

        // Configurar el comportamiento del mock
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        // Llamar al endpoint del controlador y verificar el resultado
        mockMvc.perform(post("/api/v1/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employeeJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.emailId").value("john@example.com"));
    }

    @Test
    void getEmployeeById() throws Exception {
        // Datos de ejemplo
        Employee employee = new Employee(1L, "John", "Doe", "john@example.com");

        // Configurar el comportamiento del mock
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        // Llamar al endpoint del controlador y verificar el resultado
        mockMvc.perform(get("/api/v1/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.emailId").value("john@example.com"));
    }

    @Test
    void updateEmployee() throws Exception {
        // Datos de ejemplo en formato JSON
        String employeeJson = "{\"firstName\":\"Updated\",\"lastName\":\"Doe\",\"emailId\":\"updated@example.com\"}";
        Employee existingEmployee = new Employee(1L, "John", "Doe", "john@example.com");
        Employee updatedEmployee = new Employee(1L, "Updated", "Doe", "updated@example.com");

        // Configurar el comportamiento del mock
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);

        // Llamar al endpoint del controlador y verificar el resultado
        mockMvc.perform(put("/api/v1/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employeeJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Updated"))
                .andExpect(jsonPath("$.emailId").value("updated@example.com"));
    }

    @Test
    void deleteEmployee() throws Exception {
        // Datos de ejemplo
        Employee employee = new Employee(1L, "John", "Doe", "john@example.com");

        // Configurar el comportamiento del mock
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        // Llamar al endpoint del controlador y verificar el resultado
        mockMvc.perform(delete("/api/v1/employees/1"))
                .andExpect(status().isNoContent());
    }
}
