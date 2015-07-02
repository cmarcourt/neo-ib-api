Once connected to TWS using `NeoIbApiClient.connect()` method, you will be provided a `Session` object.

Register your listeners using `Session.registerListener()` method.

Subscribe to `SubscriptionRequest` using `Session.subscribe()` method.
Place order, for example, with `PlaceOrderRequest` using `Session.orderRequest()` method.

Just Start the `Session` using `Session.start()` method. You're done!