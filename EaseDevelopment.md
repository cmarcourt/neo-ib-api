Several things have been improved from IB API to ease development.

# Ease development #
## To type or not to type? ##
### IB API ###
Almost no enum exists. Input and output parameters are native type (int, long...). Not so readable and not too easy to maintain code.

### Neo IB API ###
Every native or simple object input and output parameters have been typed. So now when you're placing a buy order, no more `"BUY"` String coding. Use `OrderAction.BUY` enum.

## Handle tick price events ##
### IB API ###
When requesting market data, EReader firstly read tick price event then call tickPrice() callback method, secondly call tickSize() callback method. Not so easy to bind price and size if you've implemented multi-threading on your own.

### Neo IB API ###
Just subscribe to `MarketDataSubscriptionRequest` then listen to `CompositeTickEventListener` to be notified of a `TickPriceEvent` binded to a `TickSizeEvent`.

## Manage reqId, tickerId, orderId or whatEverId ##
### IB API ###
When requesting market data, one has to provide an `int` for the request (not really human readable) and maintains it until the call of the callback method.
When placing an order, one has to provide an `int` for the order id (not really human readable) and again maintains it until the call of orderStatus() callback method.

### Neo IB API ###
All ids are human readable as they are `String` typed. When subscribing or sending a request, one can provide a `String` id or let the API generates automatically a human readable id using object's fields concatenation.

## Increment nextValidOrderId ##
### IB API ###
One has to retrieve the next valid order id and maintains incrementation. Be careful though when placing orders, even orderIds are different they must be in ascending order when sending request!

### Neo IB API ###
It just does the trick for you. Don't bother with incrementation. Just give a `String` id or let the API generates it for you.