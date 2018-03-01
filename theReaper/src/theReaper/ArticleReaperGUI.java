package theReaper;

import com.sun.xml.internal.bind.v2.TODO;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

public class ArticleReaperGUI extends JFrame {

	/**
	 * 
	 */

	//TODO increase font size, increase readeability, increase window height size,
	private static final long serialVersionUID = 1L;
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyy/MM/dd - HH:mm:ss");
	static ArticleReaperGUI instance;
	public static JTextArea console;
	static JTextField search;
	JButton searchButton;
	public static final String TITLE = "Article Reaper";
	static ArticleReaper reaper;
	static String result;
	

	public ArticleReaperGUI() throws UnsupportedEncodingException, IOException {

		createView();

		setTitle(TITLE);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(500, 500);
		setResizable(false);
	}

	public void createView() {
		JTextPane mainPanel = new JTextPane();
		getContentPane().add(mainPanel);
		mainPanel.setLayout(new FlowLayout());
		mainPanel.setEditable(false);

		JLabel searchBarLabel = new JLabel("Enter a word to search");
		mainPanel.add(searchBarLabel);

		search = new JTextField(35);
		mainPanel.add(search);
		searchButton = new JButton("search");
		mainPanel.getRootPane().setDefaultButton(searchButton);// Sets the defaultButton property, which determines the
																// current default button for this JRootPane.
		// The default button is the button which will be activated when a UI-defined
		// activation event (typically the Enter key)
		// occurs in the root pane regardless of whether or not the button has keyboard
		// focus (unless there is another component within
		// the root pane which consumes the activation event, such as a JTextPane). For
		// default activation to work, the button must be an
		// enabled descendant of the root pane when activation occurs. To remove a
		// default button from this root pane, set this property to null.
		mainPanel.add(searchButton, BorderLayout.EAST);
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				 ArticleReaperGUI.console.setText("");

				reaper = new ArticleReaper(search.getText());
				try {
					write();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (UnknownHostException e) {
					

				} catch (IOException e) {

				}
			}
		});

		// searchButton.addActionListener(new );

		console = new JTextArea(25, 42);
		console.setEditable(false);
		console.setLineWrap(true);
		console.setWrapStyleWord(true);// this makes the textArea wrap the lines at words boundary instead of character
										// boundaries that is the default.
		JScrollPane consoleScrolPane = new JScrollPane(console);

		// consoleScrolPane.setPreferredSize(new Dimension(200,0));
		consoleScrolPane.setBorder(BorderFactory.createTitledBorder(DATE_FORMAT.format(new Date())));
		mainPanel.add(consoleScrolPane);

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					instance = new ArticleReaperGUI();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnknownHostException e) {
					JOptionPane.showMessageDialog(console, "unkown host exception:\ncould be caused bu invalid URL or IP","Error Log", JOptionPane.ERROR_MESSAGE);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				instance.setVisible(true);
			}

		});
	}

	public static void write() throws UnsupportedEncodingException, IOException {
		// console.append(result);
		result = ArticleReaper.connectArticleTitleToAPI(ArticleReaper.editWithRegex(ArticleReaper.getTitle()));
		ArticleReaperGUI.console.append(result);

	}

	public static void writeExceptionToGUI(String msg) {
		JOptionPane.showMessageDialog(console, msg,"Error Log",
					JOptionPane.ERROR_MESSAGE);
	}


}
