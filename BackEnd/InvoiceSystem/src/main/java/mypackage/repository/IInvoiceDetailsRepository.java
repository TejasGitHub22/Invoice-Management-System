package mypackage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mypackage.model.InvoiceDetails;

public interface IInvoiceDetailsRepository extends JpaRepository<InvoiceDetails, Integer> {

}
