package mypackage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mypackage.model.InvoiceProduct;

public interface IInvoiceProductRepository extends JpaRepository<InvoiceProduct, Integer> {

}
