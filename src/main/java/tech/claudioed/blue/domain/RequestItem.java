package tech.claudioed.blue.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class RequestItem {

  private String productId;

  private BigInteger quantity;

  private BigDecimal price;

  public BigDecimal value(){
    return BigDecimal.valueOf(this.quantity.intValue()).multiply(this.price);
  }

}
