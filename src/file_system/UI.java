package file_system;

import helper.UIHelper;

import javax.swing.JFrame;

public class UI {

	public UI() {

	}
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		JFrame frame = new JFrame("File Manager");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new UIHelper(frame));
		frame.setVisible(true);
		frame.pack();
		frame.show();
	}
}
