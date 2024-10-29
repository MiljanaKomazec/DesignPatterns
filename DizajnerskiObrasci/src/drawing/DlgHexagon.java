package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import adapter_pattern.CustomHexagon;

public class DlgHexagon extends JDialog {
	private final JPanel contentPanel = new JPanel();
    private JTextField txtX;
    private JTextField txtY;
    private JTextField txtR;
    private boolean confirmation;
    private boolean innerColorConfirmation;
    private boolean borderColorConfirmation;
    Color innerFill;
    Color borderFill;
    JButton btnInnerColor;
	JButton btnBorderColor;
    private CustomHexagon customHexagon;

    public static void main(String[] args) {
        try {
            DlgHexagon dialog = new DlgHexagon();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DlgHexagon() {
        getContentPane().setBackground(new Color(0, 0, 0));
        setTitle("Add/Modify Hexagon");
        setModal(true);
        setBounds(100, 100, 300, 300);
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
            lblX.setBackground(new Color(255, 240, 245));
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
            lblY.setBackground(new Color(240, 240, 240));
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
        {
            JLabel lblR = new JLabel("Radius:");
            lblR.setBackground(new Color(255, 240, 245));
            lblR.setForeground(new Color(0, 0, 0));
            lblR.setFont(new Font("Times New Roman", Font.BOLD, 12));
            GridBagConstraints gbc_lblR = new GridBagConstraints();
            gbc_lblR.insets = new Insets(0, 0, 5, 5);
            gbc_lblR.gridx = 1;
            gbc_lblR.gridy = 3;
            contentPanel.add(lblR, gbc_lblR);
        }
        {   
            txtR = new JTextField();
            txtR.setFont(new Font("Times New Roman", Font.PLAIN, 12));
            txtR.setBackground(new Color(51, 204, 0));
            txtR.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, new Color(0, 0, 0)));
            GridBagConstraints gbc_txtR = new GridBagConstraints();
            gbc_txtR.gridwidth = 2;
            gbc_txtR.insets = new Insets(0, 0, 5, 5);
            gbc_txtR.fill = GridBagConstraints.HORIZONTAL;
            gbc_txtR.gridx = 3;
            gbc_txtR.gridy = 3;
            contentPanel.add(txtR, gbc_txtR);
            txtR.setColumns(10);
        }
        
//________ Inner / Border color __________
        
        {
            btnBorderColor = new JButton("Border color");
            btnBorderColor.setForeground(new Color(0, 0, 0));
            btnBorderColor.setFont(new Font("Times New Roman", Font.BOLD, 12));
            btnBorderColor.setBackground(new Color(255, 255, 255));
            btnBorderColor.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent arg0) 
				{
						borderFill = JColorChooser.showDialog(null, "Choose a border color", borderFill);
						if (borderFill != null) {
							btnBorderColor.setBackground(borderFill);
							borderColorConfirmation = true;
						}
				}
			});
            btnBorderColor.setHorizontalAlignment(SwingConstants.LEFT);
            GridBagConstraints gbc_btnBorderColor = new GridBagConstraints();
            gbc_btnBorderColor.gridwidth = 2;
            gbc_btnBorderColor.insets = new Insets(0, 0, 0, 5);
            gbc_btnBorderColor.gridx = 1;
            gbc_btnBorderColor.gridy = 6;
            contentPanel.add(btnBorderColor, gbc_btnBorderColor);
        }
            
        {
            btnInnerColor = new JButton("Inner color");
            btnInnerColor.setForeground(new Color(0, 0, 0));
            btnInnerColor.setFont(new Font("Times New Roman", Font.BOLD, 12));
            btnInnerColor.setBackground(new Color(255, 255, 255));
            btnInnerColor.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent arg0) 
				{
					innerFill = JColorChooser.showDialog(null, "Choose a inner color", innerFill);
					if (innerFill != null) {
						btnInnerColor.setBackground(innerFill);
						innerColorConfirmation = true;
					}
				}
			});
            GridBagConstraints gbc_btnInnerColor = new GridBagConstraints();
            gbc_btnInnerColor.gridwidth = 2;
            gbc_btnInnerColor.insets = new Insets(0, 0, 0, 5);
            gbc_btnInnerColor.gridx = 4;
            gbc_btnInnerColor.gridy = 6;
            contentPanel.add(btnInnerColor, gbc_btnInnerColor);
        }
        
//__________ OK / Cancel _____________

        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBackground(new Color(51, 204, 0));
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            
            {
                JButton okButton = new JButton("OK");
                okButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
                okButton.setForeground(new Color(0, 0, 0));
                okButton.setBackground(new Color(255, 255, 255));
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) 
                    {
                        try
                        {
                            int newX = Integer.parseInt(txtX.getText());
                            int newY = Integer.parseInt(txtY.getText());
                            int newR = Integer.parseInt(txtR.getText());
                            
                            
                            if (newX < 0 || newY < 0 || newR < 1)
                            {
                                JOptionPane.showMessageDialog(null, "You did not enter the right value", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                customHexagon = new CustomHexagon(newX, newY, newR, false, borderFill, innerFill);
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
                cancelButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
                cancelButton.setForeground(new Color(0, 0, 0));
                cancelButton.setBackground(new Color(255, 255, 255));
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) 
                    {
                        dispose();
                    }
                });
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }
    
//__________ Getters and Setters ___________

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

    public JTextField getTxtR() {
        return txtR;
    }

    public void setTxtR(JTextField txtR) {
        this.txtR = txtR;
    }

    public boolean isConfirmation() {
        return confirmation;
    }

    public void setConfirmation(boolean confirmation) {
        this.confirmation = confirmation;
    }

    public Color getInnerFill() {
        return innerFill;
    }

    public void setInnerFill(Color innerFill) {
        this.innerFill = innerFill;
        this.btnInnerColor.setBackground(innerFill);
    }

    public Color getBorderFill() {
        return borderFill;
    }

    public void setBorderFill(Color borderFill) {
        this.borderFill = borderFill;
        this.btnBorderColor.setBackground(borderFill);
    }

    public boolean isInnerColorConfirmation() {
        return innerColorConfirmation;
    }

    public void setInnerColorConfirmation(boolean innerColorConfirmation) {
        this.innerColorConfirmation = innerColorConfirmation;
    }

    public boolean isBorderColorConfirmation() {
        return borderColorConfirmation;
    }

    public void setBorderColorConfirmation(boolean borderColorConfirmation) {
        this.borderColorConfirmation = borderColorConfirmation;
    }

    public CustomHexagon getCustomHexagon() {
        return customHexagon;
    }
}
