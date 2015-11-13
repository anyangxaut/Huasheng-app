package com.anyang.util;

import org.jsoup.Jsoup;

/**
 * 该类作用：利用jsoup解析器解析html代码，此类尚未完成，后续还会修改。
 * 
 * @author anyang
 *
 */
public class jsouParse {
	
	public String parse(String html){
		
		String result = Jsoup.parse(html.replaceAll("(?i)<br[^>]*>", "br2n")).text();
		result = result.replaceAll("br2n", "\n");

		return result;
	}
	
}
