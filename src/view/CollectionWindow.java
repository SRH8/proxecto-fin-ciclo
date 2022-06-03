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
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * Pantalla para las operaciones con los cómics
 * 
 * @author Sergio Fraga
 */
public class CollectionWindow extends JDialog {
	private JTable collectionTable;
	private JButton btnDelete;
	private JButton btnEdit;
	private JButton btnAdd;

	/**
	 * Lanza la pantalla
	 */
	public static void main(String[] args) {
		try {
			CollectionWindow dialog = new CollectionWindow();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Crea el diálogo
	 */
	public CollectionWindow() {
		setTitle("Colecciones");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel topPanel = new JPanel();
			getContentPane().add(topPanel, BorderLayout.NORTH);
			topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			{
				collectionTable = new JTable();
				collectionTable.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int row = collectionTable.rowAtPoint(e.getPoint());
						int col = collectionTable.columnAtPoint(e.getPoint());
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
				scrollPane.setViewportView(collectionTable);
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
			{
				JMenu mnReports = new JMenu("Informes");
				menuBar.add(mnReports);
				{
					JMenuItem mntmViewReports = new JMenuItem("Ver informes");
					mnReports.add(mntmViewReports);
				}
			}
		}
		
	}

}
