package chinh.nguyen.service;

import chinh.nguyen.model.Product;

import java.util.Optional;

public interface IProductService {
    Iterable<Product> findAll();
    Optional<Product> findById(Long id);

}
