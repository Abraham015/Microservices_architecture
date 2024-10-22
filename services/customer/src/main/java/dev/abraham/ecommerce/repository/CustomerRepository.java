package dev.abraham.ecommerce.repository;

import dev.abraham.ecommerce.model.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer,String> {
    Customer findByEmail(String email);

    Customer findByEmailAndPassword(String email, String password);
}
