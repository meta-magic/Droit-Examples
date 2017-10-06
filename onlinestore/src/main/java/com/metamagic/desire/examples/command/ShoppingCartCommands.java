package com.metamagic.desire.examples.command;

import java.util.UUID;

import com.metamagic.desire.examples.aggregate.ShopItem;
import com.metamagic.desire.examples.aggregate.ShoppingCart;
import com.metamagic.desire.examples.event.ShoppingCartEvent.CartCreatedEvent;
import com.metamagic.desire.examples.event.ShoppingCartEvent.ItemAddedEvent;
import com.metamagic.desire.examples.event.ShoppingCartEvent.ItemRemovedEvent;
import com.metamagic.desire.examples.event.ShoppingCartEvent.ItemUpdatedEvent;
import com.metamagic.droit.eventsourcing.repository.EventSourcingRepository;

public class ShoppingCartCommands {

	private final EventSourcingRepository<ShoppingCart> repository;

	public ShoppingCartCommands(EventSourcingRepository<ShoppingCart> repository) {
		this.repository = repository;
	}

	public String createCart(String customerId) throws Exception {
		String aggregateId = UUID.randomUUID().toString();
		repository.createEvent(() -> new CartCreatedEvent(aggregateId, customerId));
		return aggregateId;
	}

	public void addItem(String aggregateId, ShopItem shopItem) throws Exception {
		repository.createEvent(() -> new ItemAddedEvent(aggregateId, shopItem));
	}

	public void updateItem(String aggregateId, ShopItem shopItem) throws Exception {
		repository.createEvent(() -> new ItemUpdatedEvent(aggregateId, shopItem));
	}

	public void removeItem(String aggregateId, ShopItem shopItem) throws Exception {
		repository.createEvent(() -> new ItemRemovedEvent(aggregateId, shopItem));
	}

	public ShoppingCart findCart(String cartId) throws Exception {
		return repository.getAggregate(cartId);
	}
}
