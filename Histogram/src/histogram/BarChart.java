/*
 * University of Central Florida
 * COP3330 - Fall 2015
 * Author:  Kelsey Cameron
 */

package histogram;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BarChart {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		HistogramPanel panel = new HistogramPanel();
		frame.add(panel, BorderLayout.CENTER);
		JPanel topPanel = new JPanel();
		JButton button = new JButton("Enter Text");
		
		
	}

}
