package com.example.EmployeeMS.Service;

import com.example.EmployeeMS.DTO.EmployeeDTO;
import com.example.EmployeeMS.Entity.Employee;
import com.example.EmployeeMS.Repository.EmployeeRepo;
import com.example.EmployeeMS.Util.RespondMessages;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EmployeeRepo employeeRepo;
    public String saveEmployee(EmployeeDTO employeeDTO){
    if(employeeRepo.existsById(employeeDTO.getEmployeeID()))
    {   return RespondMessages.RSP_DUPLICATED;


    } else {
    employeeRepo.save(modelMapper.map(employeeDTO, Employee.class));
      return RespondMessages.RSP_SUCCESS;

           }}

    public String updateEmployee(EmployeeDTO employeeDTO){

        if(employeeRepo.existsById(employeeDTO.getEmployeeID())){
            employeeRepo.save(modelMapper.map(employeeDTO,Employee.class));
            return RespondMessages.RSP_SUCCESS;

        } else {
            return RespondMessages.RSP_NO_DATA_FOUND;

        }


    }

    public List<EmployeeDTO> getAllEmployees(){
        List<Employee> empList=employeeRepo.findAll();

       return modelMapper.map(empList,new TypeToken<ArrayList <EmployeeDTO>>(){}.getType());

    }

    public EmployeeDTO searchEmployee(int employeeID){

        if (employeeRepo.existsById(employeeID)){
           Employee employeeObject= employeeRepo.findById(employeeID).orElse(null);
            return modelMapper.map(employeeObject,EmployeeDTO.class);
        } else {

            return null;
        }



    }

    public String deleteEmployee(int employeeID){

        if(employeeRepo.existsById(employeeID)) {
            employeeRepo.deleteById(employeeID);
            return RespondMessages.RSP_SUCCESS;
        } else {

            return RespondMessages.RSP_NO_DATA_FOUND;
        }

    }
}
