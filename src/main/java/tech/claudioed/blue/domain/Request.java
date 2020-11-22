package tech.claudioed.blue.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Request extends PanacheEntity {

  private String customerId;

  private String token;

  @ElementCollection
  @CollectionTable(name = "request_items")
  private Set<RequestItem> items;


}
