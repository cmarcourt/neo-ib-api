This API could replace the API provided by IB.
It is based on request (subscription, simple, ...) and event driven design.

## Neo IB API 1.1.5 (10 April 2015) ##

# Why ? #
I wanted to have an API more **elegant** and more **efficient**. I also wanted to have good naming for fields and methods.

# Specifications #
On 2012/11/11, Neo IB API implements specifications from IB's API version 9.67.

# A simple sample #

```
public class Main {
  public static void main(final String[] args) {
    new Main();
  }

  private Main() {
    final NeoIbApiClient apiClient = new NeoIbApiClient(new MyClientCallback());
    apiClient.connect(new ConnectionParameters(1), new ConnectionCallback() {
  
      @Override
      public void onSuccess(final Session session) {
        session.registerListener(new MyContractSpecificationEventListener());
        session.registerListener(new MyTickSizeEventListener());
        session.registerListener(new MyTickGenericEventListener());
        session.registerListener(new MyCompositeTickEventListener());
  
        Contract contract = getContract("DAX");
        session.request(new ContractSpecificationRequest(contract));
        session.subscribe(new MarketDataSubscriptionRequest(contract));
  
        session.start();
      }
  
      private Contract getContract(final String symbol) {
        final Contract contract = new Contract();
        contract.setCurrencyCode("EUR");
        contract.setExchange("DTB");
        contract.setExpiry("201306");
        contract.setSecurityType(SecurityType.FUTURE);
        contract.setSymbol(symbol);
        return contract;
      }
  
      @Override
      public void onFailure(final ConnectionException exception) {
      }
    });
  }
}
```

and listeners...

```
public class MyContractSpecificationEventListener implements ContractSpecificationEventListener {
   ...
}
```

```
public class MyTickSizeEventListener implements TickSizeEventListener {
   ...
}
```

```
public class MyTickGenericEventListener implements TickGenericEventListener {
   ...
}
```

```
public class MyCompositeTickEventListener implements CompositeTickEventListener {
   ...
}
```

# Maven integration #
```
<dependency>
  <groupId>ch.aonyx.broker.ib</groupId>
  <artifactId>neo-ib-api</artifactId>
  <version>1.1.5</version>
</dependency>

<repositories>
  <repository>
     <id>central.maven</id>
     <name>Central Maven Repository</name>
     <url>http://central.maven.org/maven2</url>
  </repository>
</repositories>
```