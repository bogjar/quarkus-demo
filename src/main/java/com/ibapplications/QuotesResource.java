package com.ibapplications;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.UUID;


/**
 * @author Bogdan Jarnea
 */
@Path("/quotes")
public class QuotesResource {

	@Channel("quote-requests")
	Emitter<String> quoteRequestEmitter;

	/**
	 * Endpoint to generate a new quote request id and send it to "quote-requests" Kafka topic using the emitter.
	 */
	@POST
	@Path("/request")
	@Produces(MediaType.TEXT_PLAIN)
	public String createRequest() {
		UUID uuid = UUID.randomUUID();
		quoteRequestEmitter.send(uuid.toString());
		return uuid.toString();
	}
}
