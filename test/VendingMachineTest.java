import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest {
	VendingMachine vendingMachine;

	@Before
	public void setup() {
		vendingMachine = new VendingMachine();
	}

	@Test
	public void whenThereAreNoCoinsTheMachineDisplaysInsertCoin() {
		assertEquals("INSERT COIN", vendingMachine.calculateDisplay());
	}

	@Test
	public void whenAValidCoinIsInsertedTheMachineDisplaysTheAmountOfTheCoin() {
		vendingMachine.insertCoin(Coin.NICKEL);
		assertEquals("0.05", vendingMachine.calculateDisplay());
	}

	@Test
	public void whenMultipleValidCoinsAreInsertedTheMachineDisplaysTheSumOfTheAmountOfTheCoins() {
		vendingMachine.insertCoin(Coin.NICKEL);
		vendingMachine.insertCoin(Coin.DIME);
		vendingMachine.insertCoin(Coin.QUARTER);
		assertEquals("0.40", vendingMachine.calculateDisplay());
	}

	@Test
	public void whenAnInvalidCoinIsInsertedItIsPlacedInTheCoinReturn() {
		vendingMachine.insertCoin(Coin.PENNY);
		assertEquals("INSERT COIN", vendingMachine.calculateDisplay());
		assertEquals("0.01", vendingMachine.calculateCoinReturn());
	}

	@Test
	public void whenMultipleInvalidCoinsAreInsertedTheSumOfTheirAmountIsPlacedInTheCoinReturn() {
		vendingMachine.insertCoin(Coin.PENNY);
		vendingMachine.insertCoin(Coin.PENNY);
		vendingMachine.insertCoin(Coin.PENNY);
		assertEquals("0.03", vendingMachine.calculateCoinReturn());
	}

	@Test
	public void whenAProductIsSelectedAndEnoughMoneyIsInsertedTheProductIsDispensedAndTheMachineDisplaysThankYou() {
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.insertCoin(Coin.QUARTER);

		vendingMachine.buy("cola");

		assertEquals("THANK YOU", vendingMachine.calculateDisplay());
	}

	@Test
	public void whenAProductIsSelectedAndNotEnoughMoneyIsInsertedTheMachineDisplaysTheProductPrice() {
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.buy("cola");
		assertEquals("1.00", vendingMachine.calculateDisplay());
	}

	@Test
	public void whenChipsIsSelectedAndNotEnoughMoneyIsInsertedTheMachineDisplaysFiftyCents() {
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.buy("chips");
		assertEquals("0.50", vendingMachine.calculateDisplay());
	}

	@Test
	public void whenAnInvalidProductIsSelectedAndThereIsMoneyInsertedTheMachineDisplaysCurrentAmount() {
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.buy("chocolate");
		assertEquals("0.25", vendingMachine.calculateDisplay());
	}

	@Test
	public void whenAnInvalidProductIsSelectedAndThereIsNoMoneyInsertedTheMachineDisplaysInsertCoin() {
		vendingMachine.buy("chocolate");
		assertEquals("INSERT COIN", vendingMachine.calculateDisplay());
	}

	@Test
	public void whenTheDisplayIsCheckedAgainAfterAProductIsDispensedItDisplaysInsertCoin() {
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.insertCoin(Coin.DIME);
		vendingMachine.insertCoin(Coin.NICKEL);

		vendingMachine.buy("candy");
		vendingMachine.calculateDisplay();

		assertEquals("INSERT COIN", vendingMachine.calculateDisplay());
	}

	@Test
	public void whenTheDisplayIsCheckedAgainAfterPriceOfProductWasDisplayedAndThereIsMoneyInsertedItDisplaysCurrentAmount() {
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.insertCoin(Coin.QUARTER);

		vendingMachine.buy("candy");
		vendingMachine.calculateDisplay();

		assertEquals("0.50", vendingMachine.calculateDisplay());
	}

	@Test
	public void whenTheDisplayIsCheckedAgainAfterPriceOfProductWasDisplayedAndThereIsNoMoneyInsertedItDisplaysInsertCoin() {
		vendingMachine.buy("candy");
		vendingMachine.calculateDisplay();

		assertEquals("INSERT COIN", vendingMachine.calculateDisplay());
	}

	@Test
	public void whenCurrentAmountIsCheckedAfterAProductIsDispensedItIsZero() {
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.insertCoin(Coin.DIME);
		vendingMachine.insertCoin(Coin.NICKEL);

		vendingMachine.buy("candy");

		assertEquals(0, vendingMachine.getCurrentAmount(), 0);
	}
}
