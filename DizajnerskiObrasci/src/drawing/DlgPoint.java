package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import geometry.Line;
import geometry.Point;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgPoint extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private boolean confirmation;
	Color fill;
	private boolean colorConfirmation;
	private JButton btnColor;
	JButton btnColor_1;

	public static void main(String[] args) {
		try {
			DlgPoint dialog = new DlgPoint();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgPoint() {
		getContentPane().setBackground(new Color(0, 0, 0));
		setTitle("Modify");
		setModal(true);
		setBounds(100, 100, 300, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(51, 255, 51));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblX = new JLabel("Coordinate X:");
			lblX.setFont(new Font("Times New Roman", Font.BOLD, 12));
			lblX.setForeground(new Color(0, 0, 0));
			GridBagConstraints gbc_lblX = new GridBagConstraints();
			gbc_lblX.insets = new Insets(0, 0, 5, 5);
			gbc_lblX.gridx = 1;
			gbc_lblX.gridy = 1;
			contentPanel.add(lblX, gbc_lblX);
		}
		{
			txtX = new JTextField();
			txtX.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			txtX.setBackground(new Color(51, 204, 0));
			txtX.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, new Color(0, 0, 0)));
			GridBagConstraints gbc_txtX = new GridBagConstraints();
			gbc_txtX.gridwidth = 2;
			gbc_txtX.insets = new Insets(0, 0, 5, 5);
			gbc_txtX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtX.gridx = 3;
			gbc_txtX.gridy = 1;
			contentPanel.add(txtX, gbc_txtX);
			txtX.setColumns(10);
		}
		{
			JLabel lblY = new JLabel("Coordinate Y:");
			lblY.setFont(new Font("Times New Roman", Font.BOLD, 12));
			lblY.setForeground(new Color(0, 0, 0));
			GridBagConstraints gbc_lblY = new GridBagConstraints();
			gbc_lblY.insets = new Insets(0, 0, 5, 5);
			gbc_lblY.gridx = 1;
			gbc_lblY.gridy = 2;
			contentPanel.add(lblY, gbc_lblY);
		}
		{
			txtY = new JTextField();
			txtY.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			txtY.setBackground(new Color(51, 204, 0));
			txtY.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, new Color(0, 0, 0)));
			GridBagConstraints gbc_txtY = new GridBagConstraints();
			gbc_txtY.gridwidth = 2;
			gbc_txtY.insets = new Insets(0, 0, 5, 5);
			gbc_txtY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtY.gridx = 3;
			gbc_txtY.gridy = 2;
			contentPanel.add(txtY, gbc_txtY);
			txtY.setColumns(10);
		}
		
//___________ Color _____________
		
		{
			btnColor_1 = new JButton("Color");
			btnColor_1.setForeground(new Color(0, 0, 0));
			btnColor_1.setBackground(new Color(255, 255, 255));
			btnColor_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
			btnColor_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					fill = JColorChooser.showDialog(null, "Choose a color", fill);
					if (fill != null) {
						btnColor_1.setBackground(fill);
						colorConfirmation = true;
					}
				}
			});
			GridBagConstraints gbc_btnColor_1 = new GridBagConstraints();
			gbc_btnColor_1.insets = new Insets(0, 0, 5, 5);
			gbc_btnColor_1.gridx = 1;
			gbc_btnColor_1.gridy = 4;
			contentPanel.add(btnColor_1, gbc_btnColor_1);
		}	
		
//__________ OK / Cancel _____________
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(51, 204, 0));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setForeground(new Color(0, 0, 0));
				okButton.setBackground(new Color(230, 230, 250));
				okButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) 
					{
						try
						{
							int newX = Integer.parseInt(txtX.getText());
							int newY = Integer.parseInt(txtY.getText());
							
							
							if (newX < 0 || newY < 0 )
							{
								JOptionPane.showMessageDialog(null, "You did not enter the right value", "Error", JOptionPane.ERROR_MESSAGE);
							}else {
							Point p = new Point(newX, newY, false);
							confirmation = true;
							dispose();
							}
						}
						catch (Exception ex)
						{
							JOptionPane.showMessageDialog(null, "You did not enter the right value", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setForeground(new Color(0, 0, 0));
				cancelButton.setBackground(new Color(230, 230, 250));
				cancelButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) 
					{
						confirmation = false;
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}

//_________ Getters and Setters ____________
	
	public JTextField getTxtX() {
		return txtX;
	}

	public void setTxtX(JTextField txtX) {
		this.txtX = txtX;
	}

	public JTextField getTxtY() {
		return txtY;
	}

	public void setTxtY(JTextField txtY) {
		this.txtY = txtY;
	}

	public boolean isConfirmation() {
		return confirmation;
	}

	public void setConfirmation(boolean confirmation) {
		this.confirmation = confirmation;
	}

	public Color getFill() {
		return fill;
	}

	public void setFill(Color fill) {
		this.fill = fill;
		this.btnColor_1.setBackground(fill);
	}

	public boolean isColorConfirmation() {
		return colorConfirmation;
	}

	public void setColorConfirmation(boolean colorConfirmation) {
		this.colorConfirmation = colorConfirmation;
	}

	public JButton getBtnColor() {
		return btnColor;
	}

	public void setBtnColor(JButton btnColor) {
		this.btnColor = btnColor;
	}
}