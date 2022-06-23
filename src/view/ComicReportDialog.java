package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ComicController;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ComicReportDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCollectionName;
	private String language = MainWindow.language;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ComicReportDialog dialog = new ComicReportDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ComicReportDialog() {
		setResizable(false);
		setBounds(100, 100, 483, 167);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblCollectionName = new JLabel("Colecci\u00F3n");
		lblCollectionName.setBounds(143, 44, 70, 13);
		contentPanel.add(lblCollectionName);
		
		txtCollectionName = new JTextField();
		txtCollectionName.setBounds(223, 41, 141, 19);
		contentPanel.add(txtCollectionName);
		txtCollectionName.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnShowReport = new JButton("Ver report");
				btnShowReport.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String collectionName = txtCollectionName.getText().trim();
						ComicController comicController = new ComicController();
						comicController.showComicReportByCollection(language, collectionName);
					}
				});
				btnShowReport.setActionCommand("OK");
				buttonPane.add(btnShowReport);
				getRootPane().setDefaultButton(btnShowReport);
			}
			{
				JButton btnCancel = new JButton("Cancelar");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
	}
}
