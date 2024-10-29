package stack;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.dnd.DragGestureListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Rectangle;
import geometry.Point;

import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

public class FrmStack extends JFrame {

	private DefaultListModel<Rectangle> dlm = new DefaultListModel<Rectangle>();
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() 
			{
				try {
					FrmStack frame = new FrmStack();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrmStack() {
		setTitle("Miljana Komazec IT 60/2020");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0,0,0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
//__________ North _____________
		
		JPanel pnlNorth = new JPanel();
		pnlNorth.setBackground(new Color(51, 204, 0));
		contentPane.add(pnlNorth, BorderLayout.NORTH);
		
		JLabel lblStack = new JLabel("Stack");
		lblStack.setFont(new Font("Times New Roman", Font.BOLD, 14));
		pnlNorth.add(lblStack);
		
//__________ Center ___________
		
		JPanel pnlCenter = new JPanel();
		pnlCenter.setBackground(new Color(51, 255, 51));
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		
		JScrollPane scrlRectangle = new JScrollPane();
		pnlCenter.add(scrlRectangle);
		
		JList listRectangle = new JList();
		listRectangle.setForeground(new Color(0, 0, 0));
		listRectangle.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		listRectangle.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		listRectangle.setBackground(new Color(240, 248, 255));
		scrlRectangle.setViewportView(listRectangle); 
		listRectangle.setModel(dlm);
		
//__________ South _____________
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.setBackground(new Color(51, 204, 0));
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
	
//----------------------------- Add ------------------------------------
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setForeground(new Color(0,0,0));
		btnAdd.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnAdd.setBackground(new Color(230, 230, 250));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				DlgAdd dlgAdd = new DlgAdd();
				dlgAdd.setVisible(true);
				if(dlgAdd.isConfirmation())
				{
					try 
					{
						int x = Integer.parseInt(dlgAdd.getTxtX().getText());
						int y = Integer.parseInt(dlgAdd.getTxtY().getText());
						int width = Integer.parseInt(dlgAdd.getTxtWidth().getText());
						int height = Integer.parseInt(dlgAdd.getTxtHeight().getText());
						
						Rectangle r = new Rectangle(new Point(x,y),width,height);
						
						dlm.add(0,r);
					} 
					catch(Exception NumberFormatException)
					{
						JOptionPane.showMessageDialog(null, "You didn't enter a number!");
					
					}
				}
			}
		});
		pnlSouth.add(btnAdd);
		
//---------------------------- Delete -------------------------------
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnDelete.setBackground(new Color(230, 230, 250));
		btnDelete.setForeground(new Color(0,0,0));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				if (dlm.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "List is empty!");
				}
				else
				{
					DlgAdd dlgAdd = new DlgAdd();
					Point p = dlm.getElementAt(0).getUpperLeftPoint();
					int width = dlm.getElementAt(0).getWidth();
					int height = dlm.getElementAt(0).getHeight();
					
					dlgAdd.getTxtX().setText(Integer.toString(p.getX()));
					dlgAdd.getTxtY().setText(Integer.toString(p.getY()));
					dlgAdd.getTxtWidth().setText(Integer.toString(width));
					dlgAdd.getTxtHeight().setText(Integer.toString(height));
					dlgAdd.setVisible(true);
					
					if (dlgAdd.isConfirmation())
					{
						dlm.removeElementAt(0);
					}
				}
			}
		});
		pnlSouth.add(btnDelete);
	}
}
