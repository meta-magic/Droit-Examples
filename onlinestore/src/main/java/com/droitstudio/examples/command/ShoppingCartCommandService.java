package com.droitstudio.examples.command;

import java.util.UUID;

import com.droitstudio.examples.aggregate.ShopItem;
import com.droitstudio.examples.aggregate.ShoppingCart;
import com.droitstudio.examples.aggregate.factory.ShoppingCartFactory;
import com.droitstudio.examples.event.ShoppingCartEvent.CartCreatedEvent;
import com.droitstudio.examples.event.ShoppingCartEvent.ItemAddedEvent;
import com.droitstudio.examples.event.ShoppingCartEvent.ItemRemovedEvent;
import com.droitstudio.examples.event.ShoppingCartEvent.ItemUpdatedEvent;
import com.droitstudio.repository.EventSourcingRepository;

/**
 * Service to process commands sent by user
 * 
 * @author Mahesh Pardeshi
 * 
 * */
public class ShoppingCartCommandService {

	private final EventSourcingRepository<ShoppingCart, ShoppingCartFactory> repository;

	public ShoppingCartCommandService(EventSourcingRepository<ShoppingCart, ShoppingCartFactory> repository) {
		this.repository = repository;
	}

	public String createCart(final String customerId) throws Exception {
		String aggregateId = UUID.randomUUID().toString();
		repository.createEvent(() -> new CartCreatedEvent(aggregateId, customerId));
		return aggregateId;
	}

	public void addItem(final String aggregateId, final ShopItem shopItem) throws Exception {
		repository.createEvent(() -> new ItemAddedEvent(aggregateId, shopItem));
	}

	public void updateItem(final String aggregateId, final ShopItem shopItem) throws Exception {
		repository.createEvent(() -> new ItemUpdatedEvent(aggregateId, shopItem));
	}

	public void removeItem(final String aggregateId, final ShopItem shopItem) throws Exception {
		repository.createEvent(() -> new ItemRemovedEvent(aggregateId, shopItem));
	}

}