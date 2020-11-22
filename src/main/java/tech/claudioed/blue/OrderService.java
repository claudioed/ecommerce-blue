package tech.claudioed.blue;

import io.grpc.stub.StreamObserver;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.stream.Collectors;
import javax.inject.Singleton;
import tech.claudioed.blue.domain.Request;
import tech.claudioed.blue.domain.RequestItem;

@Singleton
public class OrderService extends OrderServiceGrpc.OrderServiceImplBase{

  @Override
  public void request(RequestOrder request, StreamObserver<RequestCreated> responseObserver) {
    var items = request.getItemsList().stream()
        .map(it -> RequestItem.builder().price(BigDecimal.valueOf(it.getPrice())).quantity(
            BigInteger.valueOf((int) it.getQuantity())).productId(it.getProductId()).build())
        .collect(
            Collectors.toSet());
    var requestOrder = Request.builder().customerId(request.getCustomerId()).token(request.getToken())
        .items(items).build();
    requestOrder.persist();
    RequestCreated created = RequestCreated.newBuilder().setId(String.valueOf(requestOrder.id)).build();
    responseObserver.onNext(created);
    responseObserver.onCompleted();
    super.request(request, responseObserver);
  }
}
