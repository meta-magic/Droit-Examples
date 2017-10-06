package com.metamagic.desire.examples;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metamagic.desire.examples.aggregate.ShopItem;
import com.metamagic.desire.examples.aggregate.ShoppingCart;
import com.metamagic.desire.examples.command.ShoppingCartCommands;
import com.metamagic.droit.eventsourcing.eventstore.EventStore;
import com.metamagic.droit.eventsourcing.eventstore.JDOEventStore;
import com.metamagic.droit.eventsourcing.repository.EventSourcingRepository;

/**
 * @author Mahesh Pardeshi
 *
 */
public class TestClass {

	private final ShoppingCartCommands commands;
	private static Logger logger;

	public TestClass() {
		super();
		EventStore eventStore = new JDOEventStore(PMFConfig.persistenceManagerFactory().getPersistenceManager());
		commands = new ShoppingCartCommands(new EventSourcingRepository<ShoppingCart>(ShoppingCart.class, eventStore));
		logger = LoggerFactory.getLogger(TestClass.class);
	}

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

	private ShoppingCart findCart(String cartId) throws Exception {
		try {
			logger.info("Fetching shopping cart " + cartId);
			ShoppingCart cart = commands.findCart(cartId);
			logger.info("User's cart is " + cart.toString());
			return cart;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
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
			logger.info(mapper.writeValueAsString(cart));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
