package codesquad.service;

import codesquad.domain.category.Category;
import codesquad.domain.category.CategoryRepository;
import codesquad.dto.category.ChildCategoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Transactional
    public Category addChild(ChildCategoryDto childCategoryDto) {
        // TODO: 2018. 7. 27. 카테고리가 없을 때 커스텀 에러를 만들어 주어야합니다.
        if(childCategoryDto.getParentId() == -1 ){
            log.info(childCategoryDto.getParentId()+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            return categoryRepository.save(new Category(childCategoryDto.getChildTitle()));
        }
        Category parent = categoryRepository
                 .findById(childCategoryDto.getParentId())
                 .orElseThrow(RuntimeException::new);
        parent.addChild(new Category(childCategoryDto.getChildTitle()));
        //log.info("@@@@@@@@@@@@@@@@@"+categoryRepository.findAll());
        return categoryRepository.save(parent);
    }

}
