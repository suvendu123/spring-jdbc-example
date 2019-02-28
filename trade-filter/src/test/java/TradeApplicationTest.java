import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.erika.Instrument;
import com.erika.Trade;
import com.erika.TradeApplication;

public class TradeApplicationTest {
	
	private TradeApplication application;
	
	@Before
	public void setUp() {
		application = new TradeApplication();
	}
	
	@Test
	public void filterTradesByDate() throws Exception {
		List<Trade> trades = application.getTradesByDate("30/04/2017", "30/09/2017");
		assertEquals(2, trades.size());
		trades.forEach(t -> System.out.println(t));
		System.out.println("*******************************Test2**************************");
		trades = application.getTradesByDate("30/01/2017", "30/12/2017");
		trades.forEach(t -> System.out.println(t));
		assertEquals(3, trades.size());
	}
	
	@Test
	public void findMissingInstrumentsByDate() {
		List<Instrument> missingInstruments = application.getMissingInstruments("30/04/2017", "30/09/2017");
	}

}
