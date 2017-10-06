package com.metamagic.desire.examples.event;

import java.io.Serializable;

import com.metamagic.desire.examples.aggregate.ShopItem;
import com.metamagic.droit.eventsourcing.Event;

/**
 * Events for shopping cart
 * 
 * @author Mahesh Pardeshi
 *
 */
public interface ShoppingCartEvent extends Event, Serializable {

	public class CartCreatedEvent implements ShoppingCartEvent {

		private static final long serialVersionUID = 8601626673683533166L;

		private String cartId;

		private String custId;

		public CartCreatedEvent() {
		}

		public CartCreatedEvent(String cartId, String custId) {
			super();
			this.cartId = cartId;
			this.custId = custId;
		}

		public String getCartId() {
			return cartId;
		}

		public String getCustId() {
			return custId;
		}

		public Object getAggregateIdentifier() {
			return cartId;
		}
	}

	public class ItemAddedEvent implements ShoppingCartEvent {

		private static final long serialVersionUID = -4327981560432460453L;

		private String cartId;

		private ShopItem shopItem;

		public ItemAddedEvent() {
		}

		public ItemAddedEvent(String cartId, ShopItem shopItem) {
			super();
			this.cartId = cartId;
			this.shopItem = shopItem;
		}

		public String getCartId() {
			return cartId;
		}

		public void setCartId(String cartId) {
			this.cartId = cartId;
		}

		public ShopItem getShopItem() {
			return shopItem;
		}

		public void setShopItem(ShopItem shopItem) {
			this.shopItem = shopItem;
		}

		public Object getAggregateIdentifier() {
			return cartId;
		}
	}

	public class ItemRemovedEvent implements ShoppingCartEvent {

		private static final long serialVersionUID = 4898274196863786710L;

		private String cartId;

		private ShopItem shopItem;

		public ItemRemovedEvent() {
		}

		public ItemRemovedEvent(String cartId, ShopItem shopItem) {
			super();
			this.cartId = cartId;
			this.shopItem = shopItem;
		}

		public String getCartId() {
			return cartId;
		}

		public void setCartId(String cartId) {
			this.cartId = cartId;
		}

		public ShopItem getShopItem() {
			return shopItem;
		}

		public void setShopItem(ShopItem shopItem) {
			this.shopItem = shopItem;
		}

		public Object getAggregateIdentifier() {
			return cartId;
		}
	}

	public class ItemUpdatedEvent implements ShoppingCartEvent {

		private static final long serialVersionUID = 8962615183475674792L;

		private String cartId;

		private ShopItem shopItem;

		public ItemUpdatedEvent() {
		}

		public ItemUpdatedEvent(String cartId, ShopItem shopItem) {
			this.cartId = cartId;
			this.shopItem = shopItem;
		}

		public String getCartId() {
			return cartId;
		}

		public ShopItem getShopItem() {
			return shopItem;
		}

		public Object getAggregateIdentifier() {
			return cartId;
		}
	}

}