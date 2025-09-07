package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RemoveEmployee extends JFrame implements ActionListener {
    Choice choiceEMPID;
    JButton delete, back;
    JLabel textName, textPhone, textEmail;

    RemoveEmployee() {
        JLabel label = new JLabel("Employee ID");
        label.setBounds(50, 50, 120, 30);
        label.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(label);

        choiceEMPID = new Choice();
        choiceEMPID.setBounds(200, 50, 150, 30);
        add(choiceEMPID);

        // Load employee IDs
        try {
            conn c = new conn();
            String query = "SELECT empId FROM employee";
            PreparedStatement pstmt = c.connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                choiceEMPID.add(rs.getString("empId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel labelName = new JLabel("Name");
        labelName.setBounds(50, 100, 100, 30);
        labelName.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(labelName);

        textName = new JLabel();
        textName.setBounds(200, 100, 200, 30);
        add(textName);

        JLabel labelPhone = new JLabel("Phone");
        labelPhone.setBounds(50, 150, 100, 30);
        labelPhone.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(labelPhone);

        textPhone = new JLabel();
        textPhone.setBounds(200, 150, 200, 30);
        add(textPhone);

        JLabel labelemail = new JLabel("Email");
        labelemail.setBounds(50, 200, 100, 30);
        labelemail.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(labelemail);

        textEmail = new JLabel();
        textEmail.setBounds(200, 200, 200, 30);
        add(textEmail);

        // Load initial employee details
        loadEmployeeDetails(choiceEMPID.getSelectedItem());

        // Update details when selection changes
        choiceEMPID.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                loadEmployeeDetails(choiceEMPID.getSelectedItem());
            }
        });

        delete = new JButton("Delete");
        delete.setBounds(80, 300, 100, 30);
        delete.setBackground(Color.black);
        delete.setForeground(Color.WHITE);
        delete.addActionListener(this);
        add(delete);

        back = new JButton("Back");
        back.setBounds(220, 300, 100, 30);
        back.setBackground(Color.black);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/delete.png"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        JLabel img = new JLabel(new ImageIcon(i2));
        img.setBounds(700, 80, 200, 200);
        add(img);

        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("icons/rback.png"));
        Image i22 = i11.getImage().getScaledInstance(1120, 630, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i22));
        image.setBounds(0, 0, 1120, 630);
        add(image);

        setSize(1000, 400);
        setLocation(300, 150);
        setLayout(null);
        setVisible(true);
    }

    // Helper method to load employee details
    private void loadEmployeeDetails(String empId) {
        if (empId == null)
            return;
        try {
            conn c = new conn();
            String query = "SELECT name, phone, email FROM employee WHERE empId = ?";
            PreparedStatement pstmt = c.connection.prepareStatement(query);
            pstmt.setString(1, empId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                textName.setText(rs.getString("name"));
                textPhone.setText(rs.getString("phone"));
                textEmail.setText(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == delete) {
            try {
                String empId = choiceEMPID.getSelectedItem();
                conn c = new conn();
                String query = "DELETE FROM employee WHERE empId = ?";
                PreparedStatement pstmt = c.connection.prepareStatement(query);
                pstmt.setString(1, empId);
                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Employee Deleted Successfully");

                // Refresh UI
                choiceEMPID.remove(empId);
                if (choiceEMPID.getItemCount() > 0) {
                    choiceEMPID.select(0);
                    loadEmployeeDetails(choiceEMPID.getSelectedItem());
                } else {
                    textName.setText("");
                    textPhone.setText("");
                    textEmail.setText("");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == back) {
            setVisible(false);
            new Main_class();
        }
    }

    public static void main(String[] args) {
        new RemoveEmployee();
    }
}
