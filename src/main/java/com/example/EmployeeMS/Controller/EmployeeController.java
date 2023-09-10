package com.example.EmployeeMS.Controller;

import com.example.EmployeeMS.DTO.EmployeeDTO;
import com.example.EmployeeMS.DTO.ResponseDTO;
import com.example.EmployeeMS.Service.EmployeeService;
import com.example.EmployeeMS.Util.RespondMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/employee")
@CrossOrigin
public class EmployeeController {
    @Autowired
    private ResponseDTO responseDTO;

    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/SaveEmployee")
    public ResponseEntity saveEmployeeData(@RequestBody EmployeeDTO employeeDTO){
    try {
       String Res= employeeService.saveEmployee(employeeDTO);

       if(Res.equals("00")){
          responseDTO.setCode(RespondMessages.RSP_SUCCESS);
          responseDTO.setMessage("SUCCESS");
          responseDTO.setContent(employeeDTO);
          return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);

       }else if(Res.equals("06")){
           responseDTO.setCode(RespondMessages.RSP_DUPLICATED);
           responseDTO.setMessage("DATA ALREADY EXISTS");
           responseDTO.setContent(employeeDTO);
           return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);


       }
       else{
           responseDTO.setCode(RespondMessages.RSP_ERROR);
           responseDTO.setMessage("ERROR");
           responseDTO.setContent(null);
           return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);


       }
    } catch(Exception ex){
        responseDTO.setCode(RespondMessages.RSP_ERROR);
        responseDTO.setMessage(ex.getMessage());
        responseDTO.setContent(null);
        return new ResponseEntity(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);



    }

    }

    @PutMapping("/updateEmployeeData")
    public ResponseEntity updateEmployeeData(@RequestBody EmployeeDTO employeeDTO){
        String res =employeeService.updateEmployee(employeeDTO);
        try{
           if(res.equals("00")) {
               responseDTO.setCode(RespondMessages.RSP_SUCCESS);
               responseDTO.setMessage("Successfully Updated");
               responseDTO.setContent(employeeDTO);
               return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
           } else if(res.equals("01")){
               responseDTO.setCode(RespondMessages.RSP_NO_DATA_FOUND);
               responseDTO.setMessage("Not A Registered Employee");
               responseDTO.setContent(null);
               return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);


           } else {

               responseDTO.setCode(RespondMessages.RSP_ERROR);
               responseDTO.setMessage("Error");
               responseDTO.setContent(null);
               return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
           }


        }
        catch(Exception ex) {
            responseDTO.setCode(RespondMessages.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        }


        @GetMapping("/getAllEmployees")

        public ResponseEntity getAllEmployees(){

        try{
            List<EmployeeDTO>empListTwo=employeeService.getAllEmployees();


            responseDTO.setCode(RespondMessages.RSP_SUCCESS);
            responseDTO.setMessage("Records Of All Employees");
            responseDTO.setContent(empListTwo);
            return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);





            }
        catch(Exception ex){

            responseDTO.setCode(RespondMessages.RSP_ERROR);
            responseDTO.setMessage("Error");
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);

        }

        }
        @DeleteMapping("/deleteEmployeeData/{employeeID}")
        public ResponseEntity deleteEmployeeData(@PathVariable int employeeID){
               String res= employeeService.deleteEmployee(employeeID);
            try{
                if(res.equals("00")) {
                    responseDTO.setCode(RespondMessages.RSP_SUCCESS);
                    responseDTO.setMessage("Successfully Deleted");
                    responseDTO.setContent(null);
                    return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
                } else if(res.equals("01")){
                    responseDTO.setCode(RespondMessages.RSP_NO_DATA_FOUND);
                    responseDTO.setMessage("Not A Registered Employee");
                    responseDTO.setContent(null);
                    return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);


                } else {

                    responseDTO.setCode(RespondMessages.RSP_ERROR);
                    responseDTO.setMessage("Error");
                    responseDTO.setContent(null);
                    return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
                }


            }
            catch(Exception ex) {
                responseDTO.setCode(RespondMessages.RSP_ERROR);
                responseDTO.setMessage(ex.getMessage());
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            }


        }


        @GetMapping("/searchEmployeeData/{employeeID}")
            public ResponseEntity searchEmployeeData(@PathVariable int employeeID){

        try {
            EmployeeDTO employeeDTO = employeeService.searchEmployee(employeeID);
            if (employeeDTO != null) {
                responseDTO.setCode(RespondMessages.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO,HttpStatus.FOUND);
            } else {
                responseDTO.setCode(RespondMessages.RSP_FAIL);
                responseDTO.setMessage("Employee Does Not Exist");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);
            }

        } catch(Exception ex){
            responseDTO.setCode(RespondMessages.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);

            }


            }
    }

