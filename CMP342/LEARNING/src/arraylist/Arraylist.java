package arraylist;

import java.util.ArrayList;

public class Arraylist {

	public static void main(String[] args) {
		ArrayList<RssItem> stock_list = new ArrayList<RssItem>();
		RssItem a = new RssItem("a,b","c");
		RssItem b = new RssItem("c","d");
		stock_list.add(a);
		stock_list.add(b);
		
		RssItem[] stockArra = new RssItem[stock_list.size()];
		System.out.println(stockArra.length);
		stockArra = stock_list.
		System.out.println(stockArra[0].title);
		System.out.println(stockArra[1].description);
		
		//System.out.println(stockArra.length);
		//System.out.println(stock_list.size());
	}

}
