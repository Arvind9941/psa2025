package in.arvind.service;

import in.arvind.dto.EmployeeDto;
import in.arvind.entity.Employee;
import in.arvind.exception.NoResourceFoundException;
import in.arvind.repo.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private ModelMapper mapper;
    EmployeeRepo employeeRepo;
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }
   public EmployeeDto addEmployee(EmployeeDto employeeDto) {
       Employee employee = mapToEntity(employeeDto);
       Employee saved = employeeRepo.save(employee);
       return mapToDto(saved);
   }

    public List<EmployeeDto> getEmployees(int pageNo,int pageSize,String sortBy,String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable of = PageRequest.of(pageNo, pageSize, sort);
        Page<Employee> all = employeeRepo.findAll(of);
        List<Employee> emps = all.getContent();
        return   emps.stream().map(employee -> mapToDto(employee)).collect(Collectors.toList());
    }

    public void deleteEmployee(Long id) {
        employeeRepo.deleteById(id);
    }

    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepo.findById(id).orElseThrow(
                () -> new NoResourceFoundException("Employee not found with id: " + id)
        );
        return mapToDto(employee);
    }
    public  Employee mapToEntity(EmployeeDto dto) {
        Employee employee = mapper.map(dto, Employee.class);
        return employee;
    }
    public  EmployeeDto mapToDto (Employee employee){
        EmployeeDto dto = mapper.map(employee, EmployeeDto.class);
        return dto;
    }


}
