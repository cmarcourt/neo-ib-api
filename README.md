This API could replace the API provided by IB.
It is based on request (subscription, simple, ...) and event driven design.

<h1>Neo IB API 1.1.5 (10 April 2015)</h1>

<h2>Why ?</h2>
I wanted to have an API more *elegant* and more *efficient*. I also wanted to have good naming for fields and methods.

<h2>Specifications</h2>
On 2012/11/11, Neo IB API implements specifications from IB's API version 9.67.

<h2>A simple sample</h2>

<pre>
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
</pre>

and listeners...

<pre>
public class MyContractSpecificationEventListener implements ContractSpecificationEventListener {
   ...
}
</pre>

<pre>
public class MyTickSizeEventListener implements TickSizeEventListener {
   ...
}
</pre>

<pre>
public class MyTickGenericEventListener implements TickGenericEventListener {
   ...
}
</pre>

<pre>
public class MyCompositeTickEventListener implements CompositeTickEventListener {
   ...
}
</pre>

<h2>Maven integration</h2>
<pre>
&lt;dependency&gt;
  &lt;groupId&gt;ch.aonyx.broker.ib&lt;/groupId&gt;
  &lt;artifactId&gt;neo-ib-api&lt;/artifactId&gt;
  &lt;version&gt;1.1.5&lt;/version&gt;
&lt;/dependency&gt;

&lt;repositories&gt;
  &lt;repository&gt;
     &lt;id&gt;central.maven&lt;/id&gt;
     &lt;name&gt;Central Maven Repository&lt;/name&gt;
     &lt;url&gt;http://central.maven.org/maven2&lt;/url&gt;
  &lt;/repository&gt;
&lt;/repositories&gt;
</pre>
