package com.droitstudio.examples.command;

import com.droitstudio.examples.aggregate.ShoppingCart;
import com.droitstudio.examples.aggregate.factory.ShoppingCartFactory;
import com.droitstudio.repository.EventSourcingRepository;

/**
 * Class to query the event store
 * 
 * @author Mahesh Pardeshi
 *
 */
public class ShoppingCartQueryService {

	private final EventSourcingRepository<ShoppingCart, ShoppingCartFactory> repository;

	public ShoppingCartQueryService(EventSourcingRepository<ShoppingCart, ShoppingCartFactory> repository) {
		this.repository = repository;
	}

	public ShoppingCart findCart(final String cartId) throws Exception {
		return repository.getAggregate(cartId);
	}
}
