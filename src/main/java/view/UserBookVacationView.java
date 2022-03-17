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

public class UserBookVacationView extends AppFrame{

    private JTable vacations;
    private JScrollPane pane;
    private int[] rows;


    private JComboBox cbNames;


    private JButton addVacation = new JButton("BOOK VACATION");
    private JButton filter = new JButton("FILTER");


    private JTextField startDate = new JTextField();
    private JTextField endDate = new JTextField();
    private JTextField price = new JTextField();
    private JTextField price2 = new JTextField();




    public UserBookVacationView(){
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
        pane.setBounds(15,50,770,500);
        panel.add(pane);

        JLabel destination = new JLabel("destination");
        destination.setBounds(800,50,200,21);
        panel.add(destination);
        JLabel startDate = new JLabel("start date");
        startDate.setBounds(800,110,60,21);
        panel.add(startDate);
        JLabel endDate = new JLabel("end date");
        endDate.setBounds(900,110,60,21);
        panel.add(endDate);
        JLabel price = new JLabel("price min");
        price.setBounds(800,170,60,21);
        panel.add(price);
        JLabel price2 = new JLabel("price max");
        price2.setBounds(900,170,60,21);
        panel.add(price2);


        this.startDate.setBounds(800,140,80,27);
        panel.add(this.startDate);
        this.endDate.setBounds(900,140,80,27);
        panel.add(this.endDate);
        this.price.setBounds(800,200,80,27);
        panel.add(this.price);
        this.price2.setBounds(900,200,80,27);
        panel.add(this.price2);
        addVacation.setBounds(800,300,150,27);
        panel.add(addVacation);
        filter.setBounds(800,250,150,27);
        panel.add(filter);


        String[] init = {};
        cbNames = new JComboBox(init);
        cbNames.setBounds(800,80,80,27);
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
        String[] result = new String[7];

        for (int i = 0; i < 7; i++) {
            result[i] = (String) table.getValueAt(row,i);
        }
        return result;

    }

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

    public void filterActionListener(ActionListener actionListener){
        filter.addActionListener(actionListener);
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


    public String getStartDate(){
        return startDate.getText();
    }

    public String getEndDate(){
        return endDate.getText();
    }

    public String getPrice(){
        return price.getText();
    }

    public String getPrice2(){return price2.getText();}

    public String getSelectedDestination(){
        String name = ""+ cbNames.getItemAt(cbNames.getSelectedIndex());
        return name;
    }




}

