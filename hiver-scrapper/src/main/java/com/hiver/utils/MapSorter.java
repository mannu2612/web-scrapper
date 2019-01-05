package com.hiver.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class MapSorter {
	
	public enum SortOrder{
		ASC, DESC
	}
	
	public static Map<String, Integer> sortMapByValue(Map<String, Integer> map, final SortOrder order) {
		Comparator<Entry<String, Integer>> comparator = Comparator.comparing(Entry:: getValue);
		if(order == SortOrder.DESC) {
			comparator = Collections.reverseOrder(comparator);
		}
		//Secondary sort by word's lexical order
		comparator = comparator.thenComparing(Comparator.comparing(Entry:: getKey));

		map = map.entrySet()
		.stream().parallel()
		.sorted(comparator)
		.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
		LinkedHashMap::new));
		return map;
	}

}
