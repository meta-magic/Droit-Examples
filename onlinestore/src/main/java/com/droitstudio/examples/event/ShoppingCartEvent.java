package com.droitstudio.examples.event;

import java.io.Serializable;

import com.droitstudio.Event;
import com.droitstudio.examples.aggregate.ShopItem;

/**
 * Events for shopping cart
 * 
 * @author Mahesh Pardeshi
 *
 */
public interface ShoppingCartEvent extends Event, Serializable {

	public final class CartCreatedEvent implements ShoppingCartEvent {

		private static final long serialVersionUID = 8601626673683533166L;

		private final String cartId;

		private final String custId;

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

	public final class ItemAddedEvent implements ShoppingCartEvent {

		private static final long serialVersionUID = -4327981560432460453L;

		private final String cartId;

		private final ShopItem shopItem;

		public ItemAddedEvent(String cartId, ShopItem shopItem) {
			super();
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

	public final class ItemRemovedEvent implements ShoppingCartEvent {

		private static final long serialVersionUID = 4898274196863786710L;

		private final String cartId;

		private final ShopItem shopItem;

		public ItemRemovedEvent(String cartId, ShopItem shopItem) {
			super();
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

	public final class ItemUpdatedEvent implements ShoppingCartEvent {

		private static final long serialVersionUID = 8962615183475674792L;

		private final String cartId;

		private final ShopItem shopItem;

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