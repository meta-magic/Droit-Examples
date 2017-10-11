package com.metamagic.desire.examples.aggregate;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.metamagic.desire.examples.event.ShoppingCartEvent.CartCreatedEvent;
import com.metamagic.desire.examples.event.ShoppingCartEvent.ItemAddedEvent;
import com.metamagic.desire.examples.event.ShoppingCartEvent.ItemRemovedEvent;
import com.metamagic.desire.examples.event.ShoppingCartEvent.ItemUpdatedEvent;
import com.metamagic.droit.eventsourcing.annotation.EventProcessor;

public class ShoppingCart {

	private String cartId;

	private String custId;

	private List<ShopItem> shopItems;

	public ShoppingCart() {
	}

	private final Logger logger = LoggerFactory.getLogger(ShoppingCart.class);

	/* EVENT SOURCING HANDLER */	
	@EventProcessor
	public void process(CartCreatedEvent cartCreatedEvent) {
		this.cartId = cartCreatedEvent.getCartId();
		this.custId = cartCreatedEvent.getCustId();
	}

	/* EVENT SOURCING HANDLER */
	@EventProcessor
	public void process(ItemAddedEvent itemAddedEvent) {
		logger.info("ItemAddedEvent event sourcing handler called");
		this.cartId = itemAddedEvent.getCartId();
		if (shopItems != null && !shopItems.stream().anyMatch(item -> item.getItemId().equals(itemAddedEvent.getShopItem().getItemId()))) {
			shopItems.add(itemAddedEvent.getShopItem());
		} else {
			shopItems = new ArrayList<ShopItem>();
			shopItems.add(itemAddedEvent.getShopItem());
		}
		logger.info("+++++++++++++ Items in cart at source " + shopItems);
	}

	/* EVENT SOURCING HANDLER */
	@EventProcessor
	public void process(ItemRemovedEvent itemRemovedEvent) {
		logger.info("ItemRemovedEvent event sourcing handler called");
		this.cartId = itemRemovedEvent.getCartId();
		if (shopItems != null) {
			logger.info("Before Remove " + shopItems);
			shopItems.removeIf(item -> item.getItemId().equals(itemRemovedEvent.getShopItem().getItemId()));
			logger.info("After Remove " + shopItems);
		}
		logger.info("+++++++++++++ Items in cart at source " + shopItems);
	}

	@EventProcessor
	public void process(ItemUpdatedEvent itemUpdatedEvent) {
		logger.info("ItemUpdatedEvent event sourcing handler called");
		this.cartId = itemUpdatedEvent.getCartId();
		if (shopItems != null && shopItems.removeIf(item -> item.getItemId().equals(itemUpdatedEvent.getShopItem().getItemId()))) {
			shopItems.add(itemUpdatedEvent.getShopItem());
		}
		logger.info("+++++++++++++ Items in cart at source " + shopItems);
	}

	public String getCartId() {
		return cartId;
	}

	public String getCustId() {
		return custId;
	}

	public List<ShopItem> getShopItems() {
		return shopItems;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public void setShopItems(List<ShopItem> shopItems) {
		this.shopItems = shopItems;
	}

	@Override
	public String toString() {
		return "ShoppingCart [cartId=" + cartId + ", custId=" + custId + ", shopItems=" + shopItems + "]";
	}
}