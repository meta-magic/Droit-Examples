package com.droitstudio.examples.aggregate.processor;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.droitstudio.annotation.EventProcessor;
import com.droitstudio.examples.aggregate.ShopItem;
import com.droitstudio.examples.aggregate.ShoppingCart;
import com.droitstudio.examples.event.ShoppingCartEvent.CartCreatedEvent;
import com.droitstudio.examples.event.ShoppingCartEvent.ItemAddedEvent;
import com.droitstudio.examples.event.ShoppingCartEvent.ItemRemovedEvent;
import com.droitstudio.examples.event.ShoppingCartEvent.ItemUpdatedEvent;

/**
 * Class to build aggregate shopping cart by processing/applying events
 * 
 * @author Mahesh Pardeshi
 */
public class ShoppingCartProcessor {

	private final Logger logger = LoggerFactory.getLogger(ShoppingCartProcessor.class);

	private ShoppingCart aggregate;

	public ShoppingCartProcessor(ShoppingCart aggregate) {
		this.aggregate = aggregate;
	}

	/* EVENT SOURCING HANDLER */
	@EventProcessor
	public void process(CartCreatedEvent cartCreatedEvent) {
		aggregate.setCartId(cartCreatedEvent.getCartId());
		aggregate.setCustId(cartCreatedEvent.getCustId());
	}

	/* EVENT SOURCING HANDLER */
	@EventProcessor
	public void process(ItemAddedEvent itemAddedEvent) {
		logger.info("ItemAddedEvent event sourcing handler called");
		aggregate.setCartId(itemAddedEvent.getCartId());
		if (aggregate.getShopItems() != null
				&& !aggregate.getShopItems().stream().anyMatch(item -> item.getItemId().equals(itemAddedEvent.getShopItem().getItemId()))) {
			aggregate.getShopItems().add(itemAddedEvent.getShopItem());
		} else {
			aggregate.setShopItems(new ArrayList<ShopItem>());
			aggregate.getShopItems().add(itemAddedEvent.getShopItem());
		}
		logger.info("+++++++++++++ Items in cart at source " + aggregate.getShopItems());
	}

	/* EVENT SOURCING HANDLER */
	@EventProcessor
	public void process(ItemRemovedEvent itemRemovedEvent) {
		logger.info("ItemRemovedEvent event sourcing handler called");
		aggregate.setCartId(itemRemovedEvent.getCartId());
		if (aggregate.getShopItems() != null) {
			logger.info("Before Remove " + aggregate.getShopItems());
			aggregate.getShopItems().removeIf(item -> item.getItemId().equals(itemRemovedEvent.getShopItem().getItemId()));
			logger.info("After Remove " + aggregate.getShopItems());
		}
		logger.info("+++++++++++++ Items in cart at source " + aggregate.getShopItems());
	}

	@EventProcessor
	public void process(ItemUpdatedEvent itemUpdatedEvent) {
		logger.info("ItemUpdatedEvent event sourcing handler called");
		aggregate.setCartId(itemUpdatedEvent.getCartId());
		if (aggregate.getShopItems() != null
				&& aggregate.getShopItems().removeIf(item -> item.getItemId().equals(itemUpdatedEvent.getShopItem().getItemId()))) {
			aggregate.getShopItems().add(itemUpdatedEvent.getShopItem());
		}
		logger.info("+++++++++++++ Items in cart at source " + aggregate.getShopItems());
	}

}
