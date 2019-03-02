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

	public Map<String , List<Trade>> getTradesByDate(String from, String to) {
		Map<String , List<Trade>> tradeMap = new HashMap<>();

		try {
			Date fromDate = DF.parse(from);
			Date toDate = DF.parse(to);
			BufferedReader br = readFile("trades.txt");
		
			int lineNo = 0;
			String line;
			while ((line = br.readLine()) != null) {
				if(lineNo !=0) {
					Trade tr = mapToTrade(line);
					if(tr.getTradeDate().after(fromDate) && tr.getTradeDate().before(toDate)) {
						if(tradeMap.get(tr.getIntId()) == null) {
							List<Trade> trads = new ArrayList<>();
							trads.add(tr);
							tradeMap.put(tr.getIntId(), trads);
						}else {
							tradeMap.get(tr.getIntId()).add(tr);
						}
						
					}
					
				}
				lineNo++;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tradeMap;
	}

	public List<Instrument> getMissingInstruments(String from, String to) {

		List<Instrument> instList = new ArrayList<>();
		List<Instrument> missingList = new ArrayList<>();

		try {
			
			BufferedReader br = readFile("inst.txt");

			instList = br.lines()
					     .skip(1)
					     .map(line -> mapToInstrument(line))
						 .collect(Collectors.toList());
			br.close();
			Map<String, List<Trade>> tradMap = getTradesByDate(from, to);
			for(Instrument inst : instList) {
				if(tradMap.get(inst.getInstId()) == null) {
					missingList.add(inst);
				}
			}
			return missingList;

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

	private Trade mapToTrade(String line) {

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
	
	private Instrument mapToInstrument(String line) {

		String[] record = line.split(COMMA);
		Instrument inst = new Instrument();
		inst.setInstId(record[0]);
		inst.setName(record[1]);
		
		return inst;
	};

}
