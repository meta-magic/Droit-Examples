package com.metamagic.desire.examples.command;

import java.util.UUID;

import com.metamagic.desire.examples.aggregate.ShopItem;
import com.metamagic.desire.examples.aggregate.ShoppingCart;
import com.metamagic.desire.examples.event.ShoppingCartEvent.CartCreatedEvent;
import com.metamagic.desire.examples.event.ShoppingCartEvent.ItemAddedEvent;
import com.metamagic.desire.examples.event.ShoppingCartEvent.ItemRemovedEvent;
import com.metamagic.desire.examples.event.ShoppingCartEvent.ItemUpdatedEvent;
import com.metamagic.droit.eventsourcing.repository.EventSourcingRepository;

public class ShoppingCartCommandService {

	private final EventSourcingRepository<ShoppingCart> repository;

	public ShoppingCartCommandService(EventSourcingRepository<ShoppingCart> repository) {
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

	public ShoppingCart findCart(final String cartId) throws Exception {
		return repository.getAggregate(cartId);
	}
}
