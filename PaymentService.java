package org.redhat.service;

import javax.ws.rs.core.MediaType;

import org.apache.http.impl.client.DefaultHttpClient;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.core.executors.ApacheHttpClient4Executor;
import org.redhat.model.Order;



//@Path(value = "PaymentOrders")
public class PaymentService {
	

	private static final String PATH_ORDERS = "/Orders";
	
	private static final String url = "http://localhost:8080";

	public void payForOrder(Order order){
		try {
		Class<Order> klass = Order.class;
		
		ClientRequest clientRequest = createRequest(PATH_ORDERS);
		clientRequest.body(MediaType.APPLICATION_XML, order);
		
		ClientResponse<Order> clientResponse = clientRequest.post(klass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private ClientRequest createRequest(String uri) {
		return createRequest(uri, null);
	}

	private ClientRequest createRequest(String uri, Object[] params) {
		final String apiUrl = url+uri;
		final DefaultHttpClient httpClient = new DefaultHttpClient();
		final ClientRequest request = new ClientRequest(apiUrl, new ApacheHttpClient4Executor(httpClient));
		request.accept(MediaType.APPLICATION_XML);

		if (params != null) {
			request.pathParameters(params);
		}

		return request;
	}

}
