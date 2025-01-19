package in.arvind.controller;

import in.arvind.dto.EmployeeDto;
import in.arvind.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    //System.out.println("branch");
    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    // Save a new employee to the database
    public ResponseEntity<?> saveEmployee(@Valid @RequestBody EmployeeDto dto , BindingResult result) {
        if (result.hasErrors()) {
           new ResponseEntity<>(result.getAllErrors().toString(),HttpStatus.INTERNAL_SERVER_ERROR) ; // Return validation errors
        }
        EmployeeDto employeeDto = employeeService.addEmployee(dto);
        return new ResponseEntity<>(employeeDto,HttpStatus.OK);
    }
    @GetMapping("/get")
    // Get all employees from the database
    public List<EmployeeDto> getEmployees(
            @RequestParam(name = "pageSize",required = false ,defaultValue = "5") int pageSize,
            @RequestParam(name = "pageNo",required = false ,defaultValue = "0") int pageNo,
            @RequestParam(name = "sortBy",required = false ,defaultValue = "name") String sortBy,
            @RequestParam(name = "sortDir",required = false ,defaultValue = "asc") String sortDir
    ) {
        return employeeService.getEmployees(pageNo,pageSize,sortBy,sortDir);
    }
    @PutMapping("/update/{id}")
    public String updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employee) {
        employee.setId(id);
        employeeService.addEmployee(employee);
        return "Employee updated successfully";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "Employee deleted successfully";
    }
    @GetMapping("/empId/{id}")
    public ResponseEntity<EmployeeDto> getByEmployeeId(@PathVariable Long id){
        EmployeeDto employeeDto = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }
}
