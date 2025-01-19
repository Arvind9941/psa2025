package in.arvind.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class EmployeeDto {

    private Long id;
    @Size(min = 3,max = 30, message = "Name must be between 2 and 30 characters")
    private String name;
    @Email
    private String email;
    @Size(min=10,max=10,message="Mobile number must of be 10 digits")
    private String mobile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
