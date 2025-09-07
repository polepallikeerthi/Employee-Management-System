package employee.management.system;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class ViewEmployee extends JFrame implements ActionListener {
    JTable table;
    Choice choiceEMP;
    JButton searchbtn, print, update, back;

    ViewEmployee() {
        getContentPane().setBackground(new Color(229, 190, 181));
        JLabel search = new JLabel("Search by employee id");
        search.setBounds(20, 20, 150, 20);
        add(search);

        choiceEMP = new Choice();
        choiceEMP.setBounds(180, 20, 150, 20);
        add(choiceEMP);

        try {
            conn c = new conn();
            String query = "SELECT empID FROM employee";
            java.sql.PreparedStatement pstmt = c.connection.prepareStatement(query);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                choiceEMP.add(resultSet.getString("empID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table = new JTable();
        try {
            conn c = new conn();
            String query = "SELECT * FROM employee";
            java.sql.PreparedStatement pstmt = c.connection.prepareStatement(query);
            ResultSet resultSet = pstmt.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }
        JScrollPane jp = new JScrollPane(table);
        jp.setBounds(0, 100, 900, 600);
        add(jp);

        searchbtn = new JButton("Search");
        searchbtn.setBounds(20, 70, 80, 20);
        searchbtn.addActionListener(this);
        add(searchbtn);

        print = new JButton("Print");
        print.setBounds(120, 70, 80, 20);
        print.addActionListener(this);
        add(print);

        update = new JButton("Update");
        update.setBounds(220, 70, 80, 20);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBounds(320, 70, 80, 20);
        back.addActionListener(this);
        add(back);

        setSize(900, 700);
        setLayout(null);
        setLocation(300, 100);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchbtn) {
            String selectedId = choiceEMP.getSelectedItem();
            try {
                conn c = new conn();
                String query = "SELECT * FROM employee WHERE empID = ?";
                java.sql.PreparedStatement pstmt = c.connection.prepareStatement(query);
                pstmt.setString(1, selectedId);
                ResultSet rs = pstmt.executeQuery();
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == print) {
            try {
                table.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == update) {
            String selectedId = choiceEMP.getSelectedItem();
            setVisible(false);
            new UpdateEmployee(selectedId);
        } else if (e.getSource() == back) {
            setVisible(false);
            new Main_class();
        } else {
            setVisible(false);
            new Main_class();
        }
    }

    public static void main(String[] args) {
        new ViewEmployee();
    }
}
