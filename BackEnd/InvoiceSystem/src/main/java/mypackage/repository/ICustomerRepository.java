package mypackage.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import mypackage.model.Customer;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

}
