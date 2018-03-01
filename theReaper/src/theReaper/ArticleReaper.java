/**
 * 11 Jan 2018
olalekan
 */

package theReaper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @author olalekan
 *
 */
public class ArticleReaper {
	private static String encoding = "utf-8";
	private static String SearchWithAPI = null;
	static String searchValue;
	static ArticleReaperGUI guiInstance;
	
	public ArticleReaper (String title) {
		ArticleReaper.searchValue = title;
	}
	public static String getTitle() throws UnsupportedEncodingException, IOException {
		String siteLinksByTag = null;
		searchValue = searchValue + " wikipedia";
		try {
			Document doc = Jsoup.connect("https://www.google.com.ng/search?dcr=0&source=hp&ei=5-cIWuZ30cCwB7aUhrAN&q="
					+ URLEncoder.encode(searchValue, encoding)).userAgent("\"Mozilla/5.0 (Windows NT 6.0) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.121 Safari/535.20").ignoreHttpErrors(true).ignoreContentType(true).get();
			 siteLinksByTag = doc.getElementsByTag("cite").get(0).text();
		}catch (UnknownHostException e) {
			//we handle unknownHostException which will be thrown if the url cannot bind to the specified ip
			//possibly caused by invalid url or no data Internet connection; by calling a method that write the error message to the GUI.
			ArticleReaperGUI.writeExceptionToGUI("unkown host exception:\ncould be caused by invalid URL or Disconnected internet connection..");
		} catch (IOException e) {
			ArticleReaperGUI.writeExceptionToGUI("Oops...this is embarassing..Unable to connect");
		}
		
		

		return siteLinksByTag;

	}
	public static String connectArticleTitleToAPI(String siteLinksByTag) {

		String result = null;
		try {
			SearchWithAPI = "https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&titles="
					+ URLEncoder.encode(siteLinksByTag.replaceAll("https://en.wikipedia.org/wiki/", ""), encoding);

			HttpURLConnection httpconn = (HttpURLConnection) new URL(SearchWithAPI).openConnection();
			httpconn.addRequestProperty("userAgent", "Mozilla/5.0 (Windows NT 6.0) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.121 Safari/535.2");

			BufferedReader bf = new BufferedReader(new InputStreamReader(httpconn.getInputStream()));

			String response = bf.lines().collect(Collectors.joining());
			result = response.split("\"extract\":\"")[1];
		}catch ( java.net.UnknownHostException e) {
			System.out.println(e.getClass() + " in the connectArticletoAPI() method");
		} catch (IOException e) {
			e.getClass();
		}catch(NullPointerException e) {
			
		}
		return result;
	}

	public static String editWithRegex( String article) {
		Matcher regexMatcher = null;
		 String refinedArticle = null;
		Pattern regexPattern = Pattern.compile("(\\})");
		try {
			 regexMatcher = regexPattern.matcher(article);
			  refinedArticle =regexMatcher.replaceAll("");
		}catch (NullPointerException e) {
			
		}
		
		
		
		
		return refinedArticle;
	}
	
}

