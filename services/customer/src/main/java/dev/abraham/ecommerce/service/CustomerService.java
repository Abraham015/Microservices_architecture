package dev.abraham.ecommerce.service;

import dev.abraham.ecommerce.exception.CustomerNotFoundException;
import dev.abraham.ecommerce.mapper.CustomerMapper;
import dev.abraham.ecommerce.model.Customer;
import dev.abraham.ecommerce.repository.CustomerRepository;
import dev.abraham.ecommerce.request.CustomerRequest;
import dev.abraham.ecommerce.response.CustomerResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public String createCustomer(CustomerRequest request) {
        //Verify that the customer does not exist
        if(findCustomerByEmail(request)==null){
            Customer customer=customerRepository.save(customerMapper.toCustomer(request));
            return customer.getId();
        }
        return null;
    }

    private Customer findCustomerByEmail(CustomerRequest request) {
        return customerRepository.findByEmail(request.email());
    }

    public void updateCustomer(@Valid CustomerRequest request) {
        Customer customer=customerRepository.findById(request.id())
                .orElseThrow(()->new CustomerNotFoundException("Customer not found"));
        mergeCustomer(customer, request);
        customerRepository.save(customer);
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstName())){
            customer.setFirstName(request.firstName());
        }
        if(StringUtils.isNotBlank(request.lastName())){
            customer.setLastName(request.lastName());
        }
        if(StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if(request.address()!=null){
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toCustomerResponse)
                .collect(Collectors.toList());
    }

    public CustomerResponse findById(String id) {
        return customerRepository.findById(id)
                .map(customerMapper::toCustomerResponse)
                .orElseThrow(()->new CustomerNotFoundException("Customer not found"));
    }

    public Boolean existsById(String customerId) {
        return customerRepository.findById(customerId).isPresent();
    }

    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
