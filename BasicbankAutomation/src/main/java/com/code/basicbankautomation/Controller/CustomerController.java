package com.code.basicbankautomation.Controller;


import com.code.basicbankautomation.Dto.CreateCustomerRequest;
import com.code.basicbankautomation.Dto.CustomerDto;
import com.code.basicbankautomation.Dto.UpdateCustomerRequest;
import com.code.basicbankautomation.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/createCustomer")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest){
        return ResponseEntity.ok(customerService.createCustomer(createCustomerRequest));
    }
    @GetMapping("/getAllCustomer")
    public ResponseEntity<List<CustomerDto>> getAllCustomer(){
        return ResponseEntity.ok(customerService.getAllCustomer());
    }
    @GetMapping("/getCustomerById/{id}")
    public ResponseEntity <CustomerDto> getCustomerById(@PathVariable String id){
        return ResponseEntity.ok(customerService.getCustomerDtoById(id));
    }

    @DeleteMapping("/deleteCustomerById/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable String id){
        customerService.deleteCustomerById(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("updateCustomerById/{id}")
    public ResponseEntity<CustomerDto>updateCustomerById(@PathVariable String id, @RequestBody UpdateCustomerRequest customerRequest){
        return ResponseEntity.ok(customerService.updateCustomerById(id,customerRequest));
    }
}

