package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Pantalla para las operaciones con los c�mics
 * 
 * @author Sergio Fraga
 */
public class ComicWindow extends JDialog {
	private JTable comicTable;
	private JButton btnDelete;
	private JButton btnEdit;
	private JButton btnAdd;

	/**
	 * Lanza la pantalla
	 */
	public static void main(String[] args) {
		try {
			ComicWindow dialog = new ComicWindow();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Crea el di�logo
	 */
	public ComicWindow() {
		setTitle("C\u00F3mics");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel topPanel = new JPanel();
			getContentPane().add(topPanel, BorderLayout.NORTH);
			topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			{
				JButton btnSearch = new JButton("Buscar");
				topPanel.add(btnSearch);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			{
				comicTable = new JTable();
				comicTable.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int row = comicTable.rowAtPoint(e.getPoint());
						int col = comicTable.columnAtPoint(e.getPoint());
						if(row >= 0 && col >= 0) {				
							btnAdd.setVisible(true);
							btnEdit.setVisible(true);
							btnDelete.setVisible(true);
						} else {
							btnAdd.setVisible(false);
							btnEdit.setVisible(false);
							btnDelete.setVisible(false);
						}
					}
				});
				scrollPane.setViewportView(comicTable);
			}
		}
		{
			JPanel bottomPane = new JPanel();
			getContentPane().add(bottomPane, BorderLayout.SOUTH);
			bottomPane.setLayout(new BorderLayout(0, 0));
			{
				JPanel actionPane = new JPanel();
				bottomPane.add(actionPane, BorderLayout.NORTH);
				{
					btnAdd = new JButton("A\u00F1adir");
					btnAdd.setVisible(false);
					actionPane.add(btnAdd);
				}
				{
					btnEdit = new JButton("Editar");
					btnEdit.setVisible(false);
					actionPane.add(btnEdit);
				}
				{
					btnDelete = new JButton("Eliminar");
					btnDelete.setVisible(false);
					actionPane.add(btnDelete);
				}
			}
			{
				JPanel cancelPane = new JPanel();
				bottomPane.add(cancelPane, BorderLayout.SOUTH);
				cancelPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
				{
					JButton btnClose = new JButton("Cerrar");
					btnClose.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dispose();
						}
					});
					cancelPane.add(btnClose);
				}
			}
		}
		{
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);
		}
		
	}

}