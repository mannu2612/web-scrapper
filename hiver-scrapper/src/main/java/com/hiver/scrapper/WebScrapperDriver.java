package com.hiver.scrapper;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebScrapperDriver {
	private final static int DEFAULT_TOP_N_FREQUENT_WORDS = 5;
	private final static String DEFAULT_ADDRESS = "https://hiverhq.com//"; 
	public static void main(String[] args) {
		CommandLineParser parser = new DefaultParser();
	    Options options = new Options();

	    options.addOption("a", "address", true, "First Parameter");
	    options.addOption("t", "top", true, "Second Parameter");
		try {
		    CommandLine commandLine = parser.parse(options, args);
		    String address = commandLine.getOptionValue("a") != null? commandLine.getOptionValue("a") : DEFAULT_ADDRESS;
		    int topN = commandLine.getOptionValue("t") != null? Integer.parseInt(commandLine.getOptionValue("t")): DEFAULT_TOP_N_FREQUENT_WORDS;
			Document doc = Jsoup.connect(address).get();
			WordCounter counter = new WordCounter();
			displayWords(counter.getTopFrequentWords(doc.text()), topN);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private static void displayWords(Map<String, Integer> topFrequentWordsMap, int topN) {
		int wordColumnSize = 20;
		//Print title
		System.out.print("WORD");
		for(int i = 4; i < wordColumnSize; i++) {
			System.out.print(" ");
		}
		System.out.print("COUNT");
		System.out.println();
		System.out.println("============================");
		for(String key : topFrequentWordsMap.keySet()) {
			if(topN == 0) {
				break;
			}
			System.out.print(key);
			for(int i = key.length(); i < wordColumnSize; i++) {
				System.out.print(" ");
			}
			System.out.print(topFrequentWordsMap.get(key));
			System.out.println();
			topN--;
		}
		
		
	}

}
