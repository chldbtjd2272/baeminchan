package codesquad.controller;


import codesquad.domain.category.Category;
import codesquad.dto.category.ChildCategoryDto;
import codesquad.service.CategoryService;
import codesquad.util.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class ApiAdminController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/category")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<Category> createCategory(@RequestBody ChildCategoryDto childCategoryDto){
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+childCategoryDto);
        return new CustomResponse<Category>(CustomResponse.MSG.ALREADT_EXISTS_USER_ERROR,categoryService.addChild(childCategoryDto));
    }
}
