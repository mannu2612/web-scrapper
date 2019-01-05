package com.hiver.scrapper;

import java.util.HashMap;
import java.util.Map;

import com.hiver.utils.MapSorter;

public class WordCounter {

	public Map<String, Integer> getTopFrequentWords(String text) {
		Map<String, Integer> map = getWordCountMap(text);
		map = MapSorter.sortMapByValue(map, MapSorter.SortOrder.DESC);
		return map;
	}
	
	private Map<String, Integer> getWordCountMap(String text){
		Map<String, Integer> map = new HashMap<String, Integer>();
		String words[] = text.split("\\s");
		for (String word : words) {
			word = word.toLowerCase();
			int count = map.getOrDefault(word, 0) + 1;
			map.put(word, count);
		}
		return map;
	}
	
}
