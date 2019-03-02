import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.erika.Instrument;
import com.erika.Trade;
import com.erika.TradeApplication;
import com.sun.xml.internal.bind.v2.runtime.output.StAXExStreamWriterOutput;

public class TradeApplicationTest {
	
	private TradeApplication application;
	
	@Before
	public void setUp() {
		application = new TradeApplication();
	}
	
	@Test
	public void filterTradesByDate() throws Exception {
		Map<String , List<Trade>> trades = application.getTradesByDate("30/04/2017", "30/09/2017");
		System.out.println(trades.get("IN1"));
		System.out.println(trades.get("IN2"));
		/*assertEquals(2, trades.size());
		trades.values().forEach(t -> System.out.println(t));
		System.out.println("*******************************Test2**************************");
		trades = application.getTradesByDate("30/01/2017", "30/12/2017");
		trades.values().forEach(t -> System.out.println(t));
		assertEquals(3, trades.size());*/
	}
	
	@Test
	public void findMissingInstrumentsByDate() {
		List<Instrument> missingInstruments = application.getMissingInstruments("30/04/2017", "30/09/2017");
		System.out.println(missingInstruments);
	}

}
