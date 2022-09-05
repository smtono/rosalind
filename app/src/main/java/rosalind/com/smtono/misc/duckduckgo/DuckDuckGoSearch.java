package com.smtono.fun.duckduckgo;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DuckDuckGoSearch {
    private final static String DUCKDUCKGO_SEARCH_URL = "https://duckduckgo.com/html/?q=";

    public static String getSearchResults(String query){
        Document doc = null;
        StringBuilder output = new StringBuilder();

        try {
            doc = Jsoup.connect(DUCKDUCKGO_SEARCH_URL + query).get();
            Elements results = doc.getElementById("links").getElementsByClass("results_links");

            Element result = results.get(0);

            //for(Element result: results){

            Element title = result.getElementsByClass("links_main").first().getElementsByTag("a").first();

            output.append(title.attr("href"));

            //output.append("Title: ").append(title.text());
            //output.append("Snippet: ").append(result.getElementsByClass("result__snippet").first().text());

              /*
                System.out.println("\nURL: " + title.attr("href"));
                System.out.println("Title: " + title.text());
                System.out.println("Snippet: " + result.getElementsByClass("result__snippet").first().text());
              */
            // }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

   /*
        public static void main(String[] args) {
            String query = "cheese";
            getSearchResults(query);
        }
    */
}
