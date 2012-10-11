package org.redhat.service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.http.impl.client.DefaultHttpClient;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.core.executors.ApacheHttpClient4Executor;
import org.redhat.model.Order;

import com.sun.jersey.spi.resource.Singleton;

@Produces("application/xml")
@Path(value = "Orders")
public class PaymentResource {

	public TreeMap<Integer, Order> orderMap = new TreeMap<Integer, Order>();

	public PaymentResource() {
		// hard coded just for example purpose. To be removed
		Order order = new Order();
		order.setChildName("Michael Smith");
		order.setMenu("Menu1");
		order.setParentName("Tom Smith");
		order.setPrice(20.0);
		payForOrder(order);
	}

	@POST
	@Path("Order")
	@Produces("text/plain")
	@Consumes("application/xml")
	public String payForOrder(Order order) {
		int id = orderMap.size();
		order.setId(id);
		orderMap.put(id, order);
		return "Order: "+ id +" "+order.getParentName()+ " "+ order.getChildName()+ " "+ order.getMenu()+ " "+ order.getPrice();
	}
	
	@GET
	public List<Order> getOrders(){
		List<Order> orders = new ArrayList<Order>();
		orders.addAll(orderMap.values());
		return orders;
	}
	
	@GET
	@Path("{id}")
	public Order getOrder(@PathParam("id") int orderId){
		return orderMap.get(orderId);
	}

}
