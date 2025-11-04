/*
 /*************************************************************************************************
*   ITC-5201-RNA – Assignment 4                                                                                                                                *

*  I declare that this assignment is my own work in accordance with Humber Academic Policy.        *

*  No part of this assignment has been copied manually or electronically from any other source       *

*  (including web sites) or distributed to other students/social media.                                                       *
                                                                                                                                                                             
*  Name: Mustafa Udegadhwala.  Student ID: N01414702 Date: 16-03-2022  

 */
package sem2_assignment4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 *
 * @author mustafa
 */
public class StaffSimulator extends JFrame {

    public StaffSimulator() {
        setTitle("Staff Information ");
        setSize(1000, 900);
        createTabbedPane();
    }
    Connection con;
    int flag = 0;

    private void createTabbedPane() {
        try {
            Class.forName(MyOracleInfo.DRIVER_CLASS_ORACLE);
            System.out.println(" Driver loaded");
            con = DriverManager.getConnection(MyOracleInfo.URL, MyOracleInfo.USERNAME, MyOracleInfo.PASSWORD);
            System.out.println("Database is connected");
            flag = 1;

            //creating new JPannels
            JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JPanel mainPanel = new JPanel();
            JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JPanel panel5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JPanel panel6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JPanel panel7 = new JPanel(new FlowLayout(FlowLayout.LEFT));

            //Creating the required labels
            JLabel ID = new JLabel("ID:");
            JLabel LastName = new JLabel("Last Name:");
            JLabel FirstName = new JLabel("First Name:");
            JLabel Mi = new JLabel("mi:");
            JLabel Address = new JLabel("Address:");
            JLabel City = new JLabel("City:");
            JLabel State = new JLabel("State:");
            JLabel Telephone = new JLabel("Telephone:");
            JLabel errorlabel = new JLabel("");
            errorlabel.setText("Database Connected");

            //Creating the required Text Fields
            JTextField IDField = new JTextField(10);
            JTextField LastNameField = new JTextField(10);
            JTextField FirstNameField = new JTextField(10);
            JTextField MiField = new JTextField(10);
            JTextField AddressField = new JTextField(10);
            JTextField CityField = new JTextField(10);
            JTextField StateField = new JTextField(10);
            JTextField TelephoneField = new JTextField(10);

            //Creating required JButtons
            JButton viewButton = new JButton("view");
            JButton updateButton = new JButton("update");
            JButton insertButton = new JButton("insert");
            JButton clearButton = new JButton("clear");

            //Action Listener to clear fields
            ActionListener clear = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    IDField.setText("");
                    LastNameField.setText("");
                    FirstNameField.setText("");
                    MiField.setText("");
                    AddressField.setText("");
                    CityField.setText("");
                    StateField.setText("");
                    TelephoneField.setText("");
                    errorlabel.setText("");
                }
            };
            clearButton.addActionListener(clear);

            //ActionListener to view a record
            viewButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        //validating id field is not empty
                        if (IDField.getText().equals("")) {
                            //printing error message in label if id field is empty
                            errorlabel.setText("Please enter valid ID");
                        } else {
                            //checking if connection is not closed
                            if (con == null || flag == 0) {
                                con = DriverManager.getConnection(MyOracleInfo.URL, MyOracleInfo.USERNAME, MyOracleInfo.PASSWORD);
                                System.out.println("Database is connected");
                                flag = 1;
                            }
                            //taking input from user through field
                            String id = IDField.getText();
                            //writting the query
                            String query = "Select * from staff where ID = ?";
                            PreparedStatement mystmt = con.prepareStatement(query);
                            //Adding parameter to prepared statement
                            mystmt.setString(1, id);
                            //Executing query
                            ResultSet myset = mystmt.executeQuery();
                            String str = "";
                            //Loops untill resultset has records
                            while (myset.next()) {
                                for (int i = 1; i <= 8; i++) {
                                    //pints on console
                                    System.out.print(myset.getString(i) + ",");
                                    //stores the values in a variable
                                    str = str + myset.getString(i) + ",";
                                }
                            }
                            //prints the value of variable in GUI
                            errorlabel.setText(str);
                            con.close();
                            System.out.println("Connection closed");
                            flag = 0;
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }

            });
            //Action Listener for insert Button
            insertButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    try {
                        //Data validation checks id field is not empty
                        if (IDField.getText().equals("")) {
                            errorlabel.setText("Please enter valid ID");
                        } //Data validation checks lastname field is not empty
                        else if (LastNameField.getText().equals("")) {
                            errorlabel.setText("Please enter LastName");
                        } //Data validation checks firstname field is not empty
                        else if (FirstNameField.getText().equals("")) {
                            errorlabel.setText("Please enter FirstName");
                        } //Data validation checks Mi field is not empty
                        else if (MiField.getText().equals("")) {
                            errorlabel.setText("Please enter Mi");
                        } //Data validation checks Address field is not empty
                        else if (AddressField.getText().equals("")) {
                            errorlabel.setText("Please enter Address");
                        } //Data validation checks City field is not empty
                        else if (CityField.getText().equals("")) {
                            errorlabel.setText("Please enter valid city");
                        } //Data validation checks state field is not empty
                        else if (StateField.getText().equals("")) {
                            errorlabel.setText("Please enter valid State");
                        } //Data validation checks state field has only two char
                        else if (StateField.getText().length() != 2) {
                            errorlabel.setText("Please enter only 2 characters for State name");
                        } //Data validation checks Telephone field is not empty
                        else if (TelephoneField.getText().equals("")) {
                            errorlabel.setText("Please enter Telephone no");
                        } //Data validation checks Telephone field has only 10 numbers
                        else if (TelephoneField.getText().length() != 10) {
                            errorlabel.setText("Please enter valid Telephone number with 10 digits");
                        } else {
                            //checking if connection is not closed
                            if (con == null || flag == 0) {
                                con = DriverManager.getConnection(MyOracleInfo.URL, MyOracleInfo.USERNAME, MyOracleInfo.PASSWORD);
                                System.out.println("Database is connected");
                                flag = 1;
                            }
                            //Storing the values from GUI in variables
                            String id = IDField.getText();
                            String lname = LastNameField.getText();
                            String fname = FirstNameField.getText();
                            String mi = MiField.getText();
                            String address = AddressField.getText();
                            String city = CityField.getText();
                            String state = StateField.getText();
                            String phone = TelephoneField.getText();

                            //writting the query
                            String query = "insert into staff values(?,?,?,?,?,?,?,?)";
                            PreparedStatement mystmt = con.prepareStatement(query);
                            //Adding parameter to prepared statement
                            mystmt.setString(1, id);
                            mystmt.setString(2, lname);
                            mystmt.setString(3, fname);
                            mystmt.setString(4, mi);
                            mystmt.setString(5, address);
                            mystmt.setString(6, city);
                            mystmt.setString(7, state);
                            mystmt.setString(8, phone);
                            int rowno = mystmt.executeUpdate();
                            //Giving message to GUI
                            errorlabel.setText("Record Inserted");
                            con.close();
                            System.out.println("Connection closed");
                            flag = 0;
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            //Action Listener for Update Button
            updateButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    try {
                        //Data validation checks id field is not empty
                        if (IDField.getText().equals("")) {
                            errorlabel.setText("Please enter valid ID");
                        } //Data validation checks lastname field is not empty
                        else if (LastNameField.getText().equals("")) {
                            errorlabel.setText("Please enter LastName");
                        } //Data validation checks firstname field is not empty
                        else if (FirstNameField.getText().equals("")) {
                            errorlabel.setText("Please enter FirstName");
                        } //Data validation checks Mi field is not empty
                        else if (MiField.getText().equals("")) {
                            errorlabel.setText("Please enter Mi");
                        } //Data validation checks Address field is not empty
                        else if (AddressField.getText().equals("")) {
                            errorlabel.setText("Please enter Address");
                        } //Data validation checks City field is not empty
                        else if (CityField.getText().equals("")) {
                            errorlabel.setText("Please enter valid city");
                        } //Data validation checks state field is not empty
                        else if (StateField.getText().equals("")) {
                            errorlabel.setText("Please enter valid State");
                        } //Data validation checks state field has only two char
                        else if (StateField.getText().length() != 2) {
                            errorlabel.setText("Please enter only 2 characters for State name");
                        } //Data validation checks Telephone field is not empty
                        else if (TelephoneField.getText().equals("")) {
                            errorlabel.setText("Please enter Telephone no");
                        } //Data validation checks Telephone field has only 10 numbers
                        else if (TelephoneField.getText().length() != 10) {
                            errorlabel.setText("Please enter valid Telephone number with 10 digits");
                        } else {
                            //checking if connection is not closed
                            if (con == null || flag == 0) {
                                con = DriverManager.getConnection(MyOracleInfo.URL, MyOracleInfo.USERNAME, MyOracleInfo.PASSWORD);
                                System.out.println("Database is connected");
                                flag = 1;
                            }
                            //Storing the values from GUI in variables
                            String id = IDField.getText();
                            String lname = LastNameField.getText();
                            String fname = FirstNameField.getText();
                            String mi = MiField.getText();
                            String address = AddressField.getText();
                            String city = CityField.getText();
                            String state = StateField.getText();
                            String phone = TelephoneField.getText();

                            //writting the query
                            String query = "UPDATE staff SET LastName = ?, FirstName = ?, Mi = ?, Address = ?, City = ?, State = ?, Telephone = ? WHERE ID = ?";
                            PreparedStatement mystmt = con.prepareStatement(query);
                            //Adding parameter to prepared statement
                            mystmt.setString(1, lname);
                            mystmt.setString(2, fname);
                            mystmt.setString(3, mi);
                            mystmt.setString(4, address);
                            mystmt.setString(5, city);
                            mystmt.setString(6, state);
                            mystmt.setString(7, phone);
                            mystmt.setString(8, id);
                            int rowno = mystmt.executeUpdate();
                            //Giving message to GUI
                            errorlabel.setText("Record Updated");
                            con.close();
                            System.out.println("Connection closed");
                            flag = 0;
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            //Setting Font style and size of elements of Panels
            ID.setFont(new Font("Oswald", Font.PLAIN, 24));
            IDField.setFont(new Font("Oswald", Font.PLAIN, 18));
            IDField.setBackground(Color.YELLOW);
            LastName.setFont(new Font("Oswald", Font.PLAIN, 24));
            LastNameField.setFont(new Font("Oswald", Font.PLAIN, 18));
            FirstName.setFont(new Font("Oswald", Font.PLAIN, 24));
            FirstNameField.setFont(new Font("Oswald", Font.PLAIN, 18));
            Address.setFont(new Font("Oswald", Font.PLAIN, 24));
            AddressField.setFont(new Font("Oswald", Font.PLAIN, 18));
            City.setFont(new Font("Oswald", Font.PLAIN, 24));
            CityField.setFont(new Font("Oswald", Font.PLAIN, 18));
            State.setFont(new Font("Oswald", Font.PLAIN, 24));
            StateField.setFont(new Font("Oswald", Font.PLAIN, 18));
            Telephone.setFont(new Font("Oswald", Font.PLAIN, 24));
            TelephoneField.setFont(new Font("Oswald", Font.PLAIN, 18));
            Mi.setFont(new Font("Oswald", Font.PLAIN, 24));
            MiField.setFont(new Font("Oswald", Font.PLAIN, 18));
            viewButton.setFont(new Font("Oswald", Font.PLAIN, 24));
            updateButton.setFont(new Font("Oswald", Font.PLAIN, 24));
            insertButton.setFont(new Font("Oswald", Font.PLAIN, 24));
            clearButton.setFont(new Font("Oswald", Font.PLAIN, 24));
            errorlabel.setFont(new Font("Oswald", Font.PLAIN, 24));

            //Adding elements to panel
            add(topPanel);
            panel1.add(ID);
            panel1.add(IDField);

            panel2.add(LastName);
            panel2.add(LastNameField);
            panel2.add(FirstName);
            panel2.add(FirstNameField);
            panel2.add(Mi);
            panel2.add(MiField);

            panel3.add(Address);
            panel3.add(AddressField);

            panel4.add(City);
            panel4.add(CityField);
            panel4.add(State);
            panel4.add(StateField);

            panel5.add(Telephone);
            panel5.add(TelephoneField);

            panel6.add(viewButton);
            panel6.add(updateButton);
            panel6.add(insertButton);
            panel6.add(clearButton);

            panel7.add(errorlabel);

            mainPanel.add(panel1);
            mainPanel.add(panel2);
            mainPanel.add(panel3);
            mainPanel.add(panel4);
            mainPanel.add(panel5);
            mainPanel.setLayout(new GridLayout(5, 1));
            TitledBorder title;
            title = BorderFactory.createTitledBorder("Staff Information");
            mainPanel.setBorder(title);
            topPanel.add(mainPanel);
            topPanel.add(panel6);
            topPanel.add(panel7);
            topPanel.setLayout(new GridLayout(3, 1));
            topPanel.setBackground(Color.LIGHT_GRAY);
        } catch (Exception e) {
            System.out.println("Exception Caught");
            e.printStackTrace();
        }
    }
}
