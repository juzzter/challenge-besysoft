package juamlosada.challengebesysoft.webservices.dto.category;

import juamlosada.challengebesysoft.entities.Sale;

import java.util.List;

public class SaleListDto {

    private List<Sale> sales;

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    public static SaleListDto valueOf(List<Sale> sales) {
        SaleListDto saleListDto = new SaleListDto();
        saleListDto.setSales(sales);
        return saleListDto;
    }
}
