package codesquad.service;

import codesquad.domain.Product;
import codesquad.dto.ProductDetailDTO;
import codesquad.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAllTitles() {
        return productRepository.findAllTitles();
    }

    public ProductDetailDTO findById(Long id){return productRepository.findById(id).orElseThrow(RuntimeException::new).toDto();}
}
