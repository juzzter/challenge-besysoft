package juamlosada.challengebesysoft.webservices.dto.category;

import juamlosada.challengebesysoft.entities.Category;

import java.util.List;

public class CategoryListDto {

    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public static CategoryListDto valueOf(List<Category> categories) {
        CategoryListDto categoryListDto = new CategoryListDto();
        categoryListDto.setCategories(categories);
        return categoryListDto;
    }

}
