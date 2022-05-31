package com.code.basicbankautomation.service;



import com.code.basicbankautomation.Dto.CreateCustomerRequest;
import com.code.basicbankautomation.Dto.CustomerDto;
import com.code.basicbankautomation.Dto.CustomerDtoConverter;
import com.code.basicbankautomation.Dto.UpdateCustomerRequest;
import com.code.basicbankautomation.model.City;
import com.code.basicbankautomation.model.Customer;
import com.code.basicbankautomation.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter customerDtoConverter;
    public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter customerDtoConverter) {
        this.customerRepository = customerRepository;
        this.customerDtoConverter = customerDtoConverter;
    }

    public CustomerDto createCustomer(CreateCustomerRequest createCustomerRequest){
        Customer customer= new Customer();
        customer.setId(createCustomerRequest.getId());
        customer.setAddress(createCustomerRequest.getAddress());
        customer.setName(createCustomerRequest.getName());
        customer.setDateOfBirth(createCustomerRequest.getDateOfBirth());
        customer.setCity(City.valueOf(createCustomerRequest.getCity().name()));

        customerRepository.save(customer);

        return customerDtoConverter.convert(customer);

    }
    public List<CustomerDto> getAllCustomer(){
        List<Customer> customerList=customerRepository.findAll();
        ArrayList<CustomerDto> customerDtoList= new ArrayList<>();
        for (Customer customer:customerList) {
            customerDtoList.add(customerDtoConverter.convert(customer));
        }
        return customerDtoList;
    }
    public CustomerDto getCustomerDtoById(String id){
        Optional <Customer> optionalCustomer= customerRepository.findById(id);
        return optionalCustomer.map(customer -> customerDtoConverter.convert(customer)).orElse(new CustomerDto());
    }

    public void deleteCustomerById(String id){
        customerRepository.deleteById(id);
    }

    public CustomerDto updateCustomerById(String id, UpdateCustomerRequest customerRequest){
        Optional<Customer> optionalCustomer= customerRepository.findById(id);
        optionalCustomer.ifPresent(customer -> {
            customer.setDateOfBirth(customerRequest.getDateOfBirth());
            customer.setName(customerRequest.getName());
            customer.setAddress(customerRequest.getAddress());
            customer.setCity(City.valueOf(customerRequest.getCity().name()));
            customerRepository.save(customer);
        });
        return optionalCustomer.map(customer -> customerDtoConverter.convert(customer)).orElse(new CustomerDto());
    }
    protected Customer getCustomerById(String id){
        return customerRepository.findById(id).orElse(null);
    }
}


