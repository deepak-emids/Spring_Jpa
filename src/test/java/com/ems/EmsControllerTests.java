package com.ems;


import com.ems.dto.EmployeeDto;
import com.ems.dto.ResponseDto;
import com.ems.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ems.service.EmployeeService;


@SpringBootTest
@AutoConfigureMockMvc
class EmsControllerTests {

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    public void whenGetAllEmployeesIsCalledFromControllerItShouldReturnEmployeesWithStatusCode_200() throws Exception {

        //mocking service response
        Employee mockEmployee = new Employee();

        mockEmployee.setId(1);
        mockEmployee.setEmail("deep@gmail.com");
        mockEmployee.setPassword("bcdad");

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(mockEmployee);

        ResponseDto dto = new ResponseDto();

        dto.setData(employeeList);
        dto.setStatus(200);

        Mockito.when(employeeService.getAllEmployee()).thenReturn(dto);

        //expected result
        ObjectMapper mapper = new ObjectMapper();
        String expectedResult = mapper.writeValueAsString(dto);

        //actual result
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/employee")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse actualResult = response.getResponse();

        String actualResultJson = actualResult.getContentAsString();

        int status = actualResult.getStatus();

        //compare
        assertThat(actualResultJson).isEqualTo(expectedResult);

        assertEquals(200, status);
    }

    @Test
    public void whenGetEmployeeMethodIsCalledFromControllerItShouldReturnEmployeeWithStatusCode_200() throws Exception {

        int id = 1;

        //mocking service response
        Employee mockEmployee = new Employee();

        mockEmployee.setId(1);
        mockEmployee.setEmail("deep@gmail.com");
        mockEmployee.setPassword("bcdad");

        ResponseDto dto = new ResponseDto();

        dto.setData(mockEmployee);
        dto.setStatus(200);

        Mockito.when(employeeService.getEmployee(id)).thenReturn(dto);

        //expected result
        ObjectMapper mapper = new ObjectMapper();
        String expectedResult = mapper.writeValueAsString(dto);

        //actual result
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/employee/1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse actualResult = response.getResponse();

        String actualResultJson = actualResult.getContentAsString();

        int status = actualResult.getStatus();

        //compare
        assertThat(actualResultJson).isEqualTo(expectedResult);

        assertEquals(200, status);
    }

    @Test
    public void whenDeleteEmployeeMethodIsCalledFromControllerItShouldDeleteEmployeeWithStatusCode_200() throws Exception {

        int id = 1;

        //mocking service response
        ResponseDto dto = new ResponseDto();

        dto.setStatus(200);

        Mockito.when(employeeService.deleteEmployee(id)).thenReturn(dto);

        //expected result
        ObjectMapper mapper = new ObjectMapper();
        String expectedResult = mapper.writeValueAsString(dto);

        //actual result
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/employee/1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse actualResult = response.getResponse();

        int status = actualResult.getStatus();

        //compare
        assertEquals(200, status);
    }

    @Test
    public void whenUpdateEmployeeMethodIsCalledFromControllerItShouldReturnEmployeeWithStatusCode_200() throws Exception {

        int id = 1;

        //mocking service response
        EmployeeDto mockEmployee = new EmployeeDto();

        mockEmployee.setEmail("deep@gmail.com");
        mockEmployee.setPassword("bcdad");

        ResponseDto dto = new ResponseDto();

        dto.setData(mockEmployee);
        dto.setStatus(200);

        Mockito.when(employeeService.updateEmployee(mockEmployee, id)).thenReturn(dto);

        //expected result
        ObjectMapper mapper = new ObjectMapper();

        //converting expected result to Json
        String expectedResult = mapper.writeValueAsString(dto);

        //Json testData
        String testData = mapper.writeValueAsString(mockEmployee);

        //actual result
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/employee/1")
                .accept(MediaType.APPLICATION_JSON)
                .content(testData)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse actualResult = response.getResponse();

        String actualResultJson = actualResult.getContentAsString();

        int status = actualResult.getStatus();

        //compare
        assertThat(actualResultJson).isEqualTo(expectedResult);

        assertEquals(200, status);
    }

    @Test
    public void whenAddEmployeeMethodIsCalledFromControllerItShouldReturnEmployeeWithStatusCode_201() throws Exception {

        //mocking service response
        EmployeeDto mockEmployee = new EmployeeDto();

        mockEmployee.setEmail("deep@gmail.com");
        mockEmployee.setPassword("bcdad");

        ResponseDto dto = new ResponseDto();

        dto.setData(mockEmployee);
        dto.setStatus(201);

        Mockito.when(employeeService.addEmployee(mockEmployee)).thenReturn(dto);

        //expected result
        ObjectMapper mapper = new ObjectMapper();

        //converting expected result to Json
        String expectedResult = mapper.writeValueAsString(dto);

        //Json testData
        String testData = mapper.writeValueAsString(mockEmployee);

        //actual result
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/employee")
                .accept(MediaType.APPLICATION_JSON)
                .content(testData)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse actualResult = response.getResponse();

        String actualResultJson = actualResult.getContentAsString();

        int status = actualResult.getStatus();

        //compare
        assertThat(actualResultJson).isEqualTo(expectedResult);

        assertEquals(201, status);
    }
}
