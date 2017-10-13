package com.droitstudio.examples.aggregate;

public class ShopItem {

	private String itemId;

	private String name;

	private int quantity;

	private double price;

	public ShopItem() {
	}

	public ShopItem(String itemId) {
		super();
		this.itemId = itemId;
	}

	public ShopItem(String itemId, String name, int quantity, double price) {
		super();
		this.itemId = itemId;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ShopItem [itemId=" + itemId + ", name=" + name + ", quantity=" + quantity + ", price=" + price + "]";
	}
}