package view;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import java.awt.*;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.List;

import model.VacationPackage;

public class UserViewBookedView extends AppFrame{

    private JTable vacations;
    private JScrollPane pane;


    public UserViewBookedView(){
        this.setTitle("User View");
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        initializeForm(panel);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        panel.setBackground(new Color(199,201,255));
        this.setContentPane(panel);
        this.setVisible(true);

    }

    private void initializeForm(JPanel panel){
        pane = new JScrollPane();
        pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        pane.setBounds(15,50,950,500);
        panel.add(pane);
    }

    /**
     * returns a row from a table in the form of a String[]
     * @param row the nr of the row
     * @param table the table
     * @return the row in a String[]
     */

    /**
     * method to initialize and update the table with data
     */
    public void initializeTable(List<VacationPackage> vacationPackages){
        String[] columnNames = {"Name","Destination","Start Date", "End Date", "Price", "Number of Places", "Number of Taken Places"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0){
            @Override
            public boolean isCellEditable(int row, int column)
            {
                    return false;

            }};

        for(VacationPackage vacationPackage : vacationPackages){
            String[] row;
            row = new String[]{vacationPackage.getName(), vacationPackage.getDestination().getName(),
                    vacationPackage.getStartDate().toString(), vacationPackage.getEndDate().toString(),
                    vacationPackage.getPrice().toString(), vacationPackage.getAvailablePlaces().toString(),
                    vacationPackage.getTakenPlaces().toString()};
            model.addRow(row);
        }
        vacations = new JTable(model);
        vacations.setRowSelectionAllowed(true);
        pane.getViewport().add(vacations);
        vacations.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();
                    System.out.println(row);

                }
            }
        });

    }


}

