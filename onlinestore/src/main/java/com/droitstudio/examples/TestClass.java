package com.droitstudio.examples;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.droitstudio.eventstore.EventStore;
import com.droitstudio.examples.aggregate.ShopItem;
import com.droitstudio.examples.aggregate.ShoppingCart;
import com.droitstudio.examples.aggregate.factory.ShoppingCartFactory;
import com.droitstudio.examples.command.ShoppingCartCommandService;
import com.droitstudio.examples.command.ShoppingCartQueryService;
import com.droitstudio.repository.EventSourcingRepository;
import com.droitstudio.util.DroitUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Mahesh Pardeshi
 *
 */
public class TestClass {

	private final ShoppingCartCommandService commands;
	private final ShoppingCartQueryService query;
	private static Logger logger;

	public TestClass() {
		super();
		EventStore eventStore = DroitUtils.registerJDOEventStore(PMFConfig.persistenceManagerFactory());
		commands = new ShoppingCartCommandService(
				new EventSourcingRepository<ShoppingCart, ShoppingCartFactory>(ShoppingCart.class, ShoppingCartFactory.class, eventStore));
		query = new ShoppingCartQueryService(
				new EventSourcingRepository<ShoppingCart, ShoppingCartFactory>(ShoppingCart.class, ShoppingCartFactory.class, eventStore));
		logger = LoggerFactory.getLogger(TestClass.class);
	}

	/**
	 * Method to create cart for random customer
	 * */
	private String createCart() throws Exception {
		try {
			String customerId = UUID.randomUUID().toString();
			logger.info("Creating cart for " + customerId);
			String aggregateId = commands.createCart(customerId);
			logger.info("Cart " + aggregateId + " is created for " + customerId);
			return aggregateId;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Method to add item in created customer's cart
	 * */
	private String addItem(String cartId, ShopItem item) throws Exception {
		try {
			logger.info("Adding item in " + cartId);
			commands.addItem(cartId, item);
			logger.info("Item added in " + cartId);
			return cartId;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Method to update item from items present in a customer's cart
	 * */
	private String updateItem(String cartId, ShopItem item) throws Exception {
		try {
			logger.info("Updating item from " + cartId);
			commands.updateItem(cartId, item);
			logger.info("Item updated from " + cartId);
			return cartId;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Method to remove item from customer's cart
	 * */
	private String removeItem(String cartId, ShopItem item) throws Exception {
		try {
			logger.info("Removing item from " + cartId);
			commands.removeItem(cartId, item);
			logger.info("Item removed from " + cartId);
			return cartId;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Method to find cart state by cartId
	 * */
	private ShoppingCart findCart(String cartId) throws Exception {
		try {
			logger.info("Fetching shopping cart " + cartId);
			ShoppingCart cart = query.findCart(cartId);
			logger.info("User's cart is " + cart.toString());
			return cart;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	private static <U> U newInstance(Class<U> type) {
		try {
			return type.getConstructor(ShoppingCart.class).newInstance(new ShoppingCart());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		TestClass test = new TestClass();
		try {
			String cartId = test.createCart();
			test.addItem(cartId, new ShopItem("I1", "VU TV", 1, 10000));
			test.addItem(cartId, new ShopItem("I2", "Watch", 2, 7000));
			test.addItem(cartId, new ShopItem("I3", "Painting", 10, 5000));
			test.addItem(cartId, new ShopItem("I4", "Table Lamp", 25, 3000));

			test.updateItem(cartId, new ShopItem("I2", "Item2", 20, 7000));
			test.removeItem(cartId, new ShopItem("I4"));

			ShoppingCart cart = test.findCart(cartId);
			ObjectMapper mapper = new ObjectMapper();
			logger.info("Current State Of Cart : " + mapper.writeValueAsString(cart));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
