package com.droitstudio.examples.aggregate;

import java.util.List;

/**
 * POJO Class is defined as an aggregate root for ShoppingCart domain
 *  
 * @author Mahesh Pardeshi
 *  
 * */
public class ShoppingCart {

	private String cartId;

	private String custId;

	private List<ShopItem> shopItems;

	/**
	 * @return the cartId
	 */
	public final String getCartId() {
		return cartId;
	}

	/**
	 * @param cartId the cartId to set
	 */
	public final void setCartId(final String cartId) {
		this.cartId = cartId;
	}

	/**
	 * @return the custId
	 */
	public final String getCustId() {
		return custId;
	}

	/**
	 * @param custId the custId to set
	 */
	public final void setCustId(final String custId) {
		this.custId = custId;
	}

	/**
	 * @return the shopItems
	 */
	public final List<ShopItem> getShopItems() {
		return shopItems;
	}

	/**
	 * @param shopItems the shopItems to set
	 */
	public final void setShopItems(final List<ShopItem> shopItems) {
		this.shopItems = shopItems;
	}

	@Override
	public String toString() {
		return "ShoppingCart [cartId=" + cartId + ", custId=" + custId + ", shopItems=" + shopItems + "]";
	}
}