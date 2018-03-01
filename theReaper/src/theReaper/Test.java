/**
 * 14 Jan 2018
olalekan
 */

package theReaper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author olalekan
 *
 */
public class Test {
	static ArticleReaper reaper;
	static ArticleReaperGUI reaperGUI;
	public static void testArticleTitle(ArticleReaper reaper) {
		
		if(reaper instanceof ArticleReaper) {
			try {
				if (ArticleReaper.getTitle() != null) {
					System.out.println("this is the output of getTitle(): "+ArticleReaper.getTitle());
				}else 
					System.out.println("the shit returns nothing");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
	}
	public static void testArticleAPIConn(ArticleReaper reaper) {
		if(reaper instanceof ArticleReaper) {
			try {
				if (ArticleReaper.connectArticleTitleToAPI(ArticleReaper.getTitle()) != null) {
					System.out.println("this is the output of connectArticleTitleToAPI(): "+ArticleReaper.connectArticleTitleToAPI(ArticleReaper.getTitle()));
				}else 
					System.out.println("the shit returns nothing");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}
	public static void jTextFieldResponse(ArticleReaperGUI reaperGUI) {
		if (reaperGUI instanceof ArticleReaperGUI) {
			if(ArticleReaperGUI.search !=null) {
				System.out.println("this is the search phrase in the JTextField; "+ ArticleReaperGUI.search.getText());
			}else
				System.out.println("JTextField sends an empty messgae");
			if(ArticleReaperGUI.result != null) {
				System.out.println("result : "+ ArticleReaperGUI.result);
			}else{
				System.out.println("result is empty");
			}
		}
	}
	public static void main(String[] args) {
		reaper = new ArticleReaper(ArticleReaper.searchValue);
		
		//testArticleTitle(reaper);
		
		//testArticleAPIConn(reaper);
		
		try {
			reaperGUI = new ArticleReaperGUI();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jTextFieldResponse(reaperGUI);
	}

}
