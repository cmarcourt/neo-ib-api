package ch.aonyx.broker.ib.api;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class NeoIbApiClientTest {

	@Test
	public void testIsConnected() {
		NeoIbApiClient c = new NeoIbApiClient(new ClientCallback() {
			@Override
			public void onSuccess(CallbackObject object) {
			}
			
			@Override
			public void onFailure(NeoIbApiClientException exception) {
			}
		});
		assertFalse(c.isConnected());
	}

}
