package com.erika;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TradeApplication {

	private static final String COMMA = ",";
	private static final DateFormat DF = new SimpleDateFormat("dd/MM/yyyy");

	public List<Trade> getTradesByDate(String from, String to) {
		List<Trade> tradeList = new ArrayList<Trade>();

		try {
			Date fromDate = DF.parse(from);
			Date toDate = DF.parse(to);
			BufferedReader br = readFile("trades.txt");

			tradeList = br.lines()
					      .skip(1)
					      .map(mapToTrade)
					      .filter(trade -> trade.getTradeDate().after(fromDate) && trade.getTradeDate().before(toDate))
					      .collect(Collectors.toList());
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return tradeList;
	}

	public List<Instrument> getMissingInstruments(String from, String to) {

		Map<String, String> instMap = new HashMap<String, String>();

		try {
			Date fromDate = DF.parse(from);
			Date toDate = DF.parse(to);
			BufferedReader br = readFile("inst.txt");

			instMap = br.lines()
					     .skip(1)
					     .map(mapToInstrument)
						 .collect(Collectors.toMap(Instrument::getInstId, Instrument::getName));
			br.close();
			List<Trade> tradList = getTradesByDate(from, to);
			System.out.println(instMap);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private BufferedReader readFile(String fileName) throws FileNotFoundException {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		InputStream inputFS = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
		return br;
	}

	private Function<String, Trade> mapToTrade = (line) -> {

		String[] record = line.split(COMMA);
		Trade trade = new Trade();

		try {
			trade.setTradeId(record[0]);
			trade.setTradeDate(DF.parse(record[1]));

			trade.setIntId(record[2]);
			trade.setQuantity(Integer.parseInt(record[3]));
			trade.setPrice(Integer.parseInt(record[4]));
			trade.setAmount(Integer.parseInt(record[5]));
			trade.setTrader(record[6]);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return trade;
	};
	
	private Function<String, Instrument> mapToInstrument = (line) -> {

		String[] record = line.split(COMMA);
		Instrument inst = new Instrument();
		inst.setInstId(record[0]);
		inst.setName(record[1]);
		
		return inst;
	};

}
