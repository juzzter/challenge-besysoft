package juamlosada.challengebesysoft.webservices.dto.category;

import juamlosada.challengebesysoft.entities.Seller;

import java.util.List;

public class SellerListDto {

    private List<Seller> sellers;

    public List<Seller> getSellers() {
        return sellers;
    }

    public void setSellers(List<Seller> sellers) {
        this.sellers = sellers;
    }

    public static SellerListDto valueOf(List<Seller> sellers) {
        SellerListDto sellerListDto = new SellerListDto();
        sellerListDto.setSellers(sellers);
        return sellerListDto;
    }
}
