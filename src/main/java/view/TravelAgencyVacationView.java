package view;



import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import java.awt.*;

import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.List;

import model.Destination;
import model.VacationPackage;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class TravelAgencyVacationView extends AppFrame{

    private JTable vacations;
    private JScrollPane pane;
    private int[] rows;


    private JButton addVacation = new JButton("ADD");


    private JButton deleteVacation = new JButton("DELETE");
    private JButton updateVacation = new JButton("UPDATE");


    private JTextField nameText = new JTextField();

    private JTextField startDate = new JTextField();
    private JTextField endDate = new JTextField();
    private JTextField price = new JTextField();
    private JTextField availablePlaces = new JTextField();
    private JTextField destination = new JTextField();

    private JComboBox cbNames;



    public TravelAgencyVacationView(){
        this.setTitle("Agency View");
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
        pane.setBounds(15,50,770,500);
        panel.add(pane);
        JLabel name = new JLabel("name");
        name.setBounds(800,50,60,21);
        panel.add(name);

        JLabel startDate = new JLabel("start date");
        startDate.setBounds(800,110,60,21);
        panel.add(startDate);
        JLabel endDate = new JLabel("end date");
        endDate.setBounds(900,110,60,21);
        panel.add(endDate);
        JLabel price = new JLabel("price");
        price.setBounds(800,170,60,21);
        panel.add(price);
        JLabel availablePlaces = new JLabel("available places");
        availablePlaces.setBounds(800,230,200,21);
        panel.add(availablePlaces);
        JLabel destination = new JLabel("destination");
        destination.setBounds(900,50,200,21);
        panel.add(destination);


        nameText.setBounds(800,80,80,27);
        panel.add(nameText);
        this.startDate.setBounds(800,140,80,27);
        panel.add(this.startDate);
        this.endDate.setBounds(900,140,80,27);
        panel.add(this.endDate);
        this.price.setBounds(800,200,80,27);
        panel.add(this.price);
        this.availablePlaces.setBounds(800,260,80,27);
        panel.add(this.availablePlaces);


        addVacation.setBounds(800,300,150,27);
        panel.add(addVacation);
        updateVacation.setBounds(800,350,150,27);
        panel.add(updateVacation);
        deleteVacation.setBounds(800,400,150,27);
        panel.add(deleteVacation);
       /* searchNameText.setBounds(800,450,150,27);
        panel.add(searchNameText);
        searchByName.setBounds(800,500,150,27);
        panel.add(searchByName);*/

        String[] init = {};
        cbNames = new JComboBox(init);
        cbNames.setBounds(900,80,80,27);
        cbNames.setEditable(true);
        AutoCompleteDecorator.decorate(cbNames);
        panel.add(cbNames);


    }

    /**
     * returns a row from a table in the form of a String[]
     * @param row the nr of the row
     * @param table the table
     * @return the row in a String[]
     */
    public String[] getRowAt(int row, JTable table) {
        String[] result = new String[8];

        for (int i = 0; i < 8; i++) {
            result[i] = (String) table.getValueAt(row,i);
        }
        return result;

    }

    /**
     * method to initialize and update the table with data
     */
    public void initializeTable(List<VacationPackage> vacationPackages){
        String[] columnNames = {"Name","Destination","Start Date", "End Date", "Price", "Number of Places", "Number of Taken Places", "Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0){
            @Override
            public boolean isCellEditable(int row, int column)
            {
                if(column == 1 || column == 6 || column ==7)
                    return false;
                else return true;
            }};

        for(VacationPackage vacationPackage : vacationPackages){
            String[] row;
            row = new String[]{vacationPackage.getName(), vacationPackage.getDestination().getName(),
                    vacationPackage.getStartDate().toString(), vacationPackage.getEndDate().toString(),
                    vacationPackage.getPrice().toString(), vacationPackage.getAvailablePlaces().toString(),
                    vacationPackage.getTakenPlaces().toString(),
            vacationPackage.getStatus()};
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

    public void setComboBoxDestinations(List<Destination> destinations){
        cbNames.removeAllItems();
        int i = 0;
        for(Destination destination :destinations){
            cbNames.insertItemAt(destination.getName(),i);
            i++;
        }
    }


    public void addVacationActionListener(ActionListener actionListener){
        addVacation.addActionListener(actionListener);
    }

    public void deleteVacationActionListener(ActionListener actionListener){
        deleteVacation.addActionListener(actionListener);
    }

    public void updateVacationActionListener(ActionListener actionListener){
        updateVacation.addActionListener(actionListener);
    }



    public int[] getRows() {
        return rows;
    }

    public void setRows(int[] rows) {
        this.rows = rows;
    }

    public JTable getVacations() {
        return vacations;
    }



    public String getName(){
        return nameText.getText();
    }

    public String getStartDate(){
        return startDate.getText();
    }

    public String getEndDate(){
        return endDate.getText();
    }

    public String getPrice(){
        return price.getText();
    }
    public String getDestination(){
        return destination.getText();
    }

    public String getAvailablePlaces(){
        return availablePlaces.getText();
    }

    public String getSelectedDestination(){
        String name = ""+ cbNames.getItemAt(cbNames.getSelectedIndex());
        return name;
    }

}

