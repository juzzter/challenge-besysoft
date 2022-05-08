package juamlosada.challengebesysoft.webservices.dto.category;

import juamlosada.challengebesysoft.entities.Product;

import java.util.List;

public class ProductListDto {

    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public static ProductListDto valueOf(List<Product> products) {
        ProductListDto productListDto = new ProductListDto();
        productListDto.setProducts(products);
        return productListDto;
    }

}
