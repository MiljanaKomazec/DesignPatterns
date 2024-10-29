package sort;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Point;
import geometry.Rectangle;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;

public class FrmSort extends JFrame {
	
	private DefaultListModel<Rectangle> dlm = new DefaultListModel<Rectangle>();
	private JPanel contentPane;
	private ArrayList<Rectangle> list = new ArrayList<Rectangle>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmSort frame = new FrmSort();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrmSort() {
		setTitle("Miljana Komazec IT60/2020");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
//__________ North _____________
		
		JPanel pnlNorth = new JPanel();
		pnlNorth.setBackground(new Color(51, 204, 0));
		contentPane.add(pnlNorth, BorderLayout.NORTH);
		
		JLabel lblStack = new JLabel("Sort");
		lblStack.setFont(new Font("Times New Roman", Font.BOLD, 14));
		pnlNorth.add(lblStack);
		
//__________ Center ___________
		
		JPanel pnlCenter = new JPanel();
		pnlCenter.setBackground(new Color(51, 255, 51));
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		
		JScrollPane scrlRectangle = new JScrollPane();
		pnlCenter.add(scrlRectangle);
		
		JList listRectangle = new JList();
		listRectangle.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		listRectangle.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		listRectangle.setBackground(new Color(230, 230, 250));
		scrlRectangle.setViewportView(listRectangle);
		listRectangle.setModel(dlm);
		
//__________ South _____________
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.setBackground(new Color(51, 204, 0));
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		
//----------------------------- Add ------------------------------------
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnAdd.setForeground(new Color(0, 0, 0));
		btnAdd.setBackground(new Color(230, 230, 250));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				DlgAdd dlgAdd = new DlgAdd();
				dlgAdd.setVisible(true);
				if (dlgAdd.isConfirmation())
				{
					try
					{
						int x = Integer.parseInt(dlgAdd.getTxtX().getText());
						int y = Integer.parseInt(dlgAdd.getTxtY().getText());
						int width = Integer.parseInt(dlgAdd.getTxtWidth().getText());
						int height = Integer.parseInt(dlgAdd.getTxtHeight().getText());
						
						Rectangle r = new Rectangle(new Point(x,y), width, height);
						dlm.add(0,r);
					}
					catch (Exception NumberFormatException)
					{
						JOptionPane.showMessageDialog(null, "You didn't enter a number!");
					}
				}
			}
		});
		pnlSouth.add(btnAdd);
		
//----------------------------- Sort ------------------------------------
		
		JButton btnSort = new JButton("Sort");
		btnSort.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnSort.setBackground(new Color(230, 230, 250));
		btnSort.setForeground(new Color(0, 0, 0));
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				Rectangle r;
				if (dlm.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "List is empty!");
				}
				else
				{
					for (int i = 0; i < dlm.getSize(); i++)
					{
						list.add(dlm.getElementAt(i));
					}
					
					for (int i = list.size() - 1; i > 0; i--)
					{
						for (int j = 0; j < i; j++)
						{
							if (list.get(j).compareTo(list.get(j+1)) > 0)
							{
								r = list.get(j);
								list.set(j, list.get(j+1));
								list.set(j+1, r);
							}
						}
					}
					dlm.removeAllElements();
					
					for (int i = 0; i < list.size(); i++)
					{
						dlm.addElement(list.get(i));
					}
				}
				list.clear();
			}
		});
		pnlSouth.add(btnSort);
	}

	public DefaultListModel<Rectangle> getDlm() {
		return dlm;
	}

	public void setDlm(DefaultListModel<Rectangle> dlm) {
		this.dlm = dlm;
	}

	public ArrayList<Rectangle> getList() {
		return list;
	}

	public void setList(ArrayList<Rectangle> list) {
		this.list = list;
	}
}