package codesquad.domain;

import codesquad.dto.ProductDetailDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Size(min = 2)
    private String imgUrl;

    @Column
    private int price;

    @Column
    @Size(min = 2, max = 50)
    private String title;

    @Column
    @Size(min = 2)
    private String description;

    @ManyToOne
    @JsonIgnore
    private Category category;

    @ManyToOne
    @JsonIgnore
    private TabCategory parent;

    public ProductDetailDTO toDto(){
        List<String> categoriesTitle = new ArrayList<>();
        category.getRootTitle(categoriesTitle);
        Collections.reverse(categoriesTitle);
        return new ProductDetailDTO(id,imgUrl,price,title,description,categoriesTitle);
    }
}
