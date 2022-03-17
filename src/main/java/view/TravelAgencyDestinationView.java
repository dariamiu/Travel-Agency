package view;



import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import java.awt.*;

import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.List;

import model.Destination;

public class TravelAgencyDestinationView extends AppFrame{

    private JTable destinationsTable;
    private JScrollPane pane;
    private int[] rows;


    private JButton addDestination = new JButton("ADD");

    private JButton deleteDestination = new JButton("DELETE");
    private JButton updateDestination = new JButton("UPDATE");


    private JTextField nameText = new JTextField();
    private JTextArea detailsText = new JTextArea(20,20);




    public TravelAgencyDestinationView(){
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

        JLabel proteins = new JLabel("detail");
        proteins.setBounds(800,110,60,21);
        panel.add(proteins);


        nameText.setBounds(800,80,80,27);
        panel.add(nameText);
        detailsText.setBounds(800,140,150,150);
        panel.add(detailsText);

        addDestination.setBounds(800,300,150,27);
        panel.add(addDestination);
        updateDestination.setBounds(800,350,150,27);
        panel.add(updateDestination);
        deleteDestination.setBounds(800,400,150,27);
        panel.add(deleteDestination);
        /*searchNameText.setBounds(800,450,150,27);
        panel.add(searchNameText);
        searchByName.setBounds(800,500,150,27);
        panel.add(searchByName);*/


    }

    /**
     * returns a row from a table in the form of a String[]
     * @param row the nr of the row
     * @param table the table
     * @return the row in a String[]
     */
    public String[] getRowAt(int row, JTable table) {
        String[] result = new String[2];

        for (int i = 0; i < 2; i++) {
            result[i] = (String) table.getValueAt(row,i);
        }
        return result;

    }

    /**
     * method to initialize and update the table with data
     */
    public void initializeTable(List<Destination> destinations){
        String[] columnNames = {"Name","Description"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0){
            @Override
            public boolean isCellEditable(int row, int column)
            {
                if(column == 0)
                    return false;
                else return true;
            }
        };

        for(Destination destination: destinations){
            String[] row;
            row = new String[]{destination.getName(),destination.getDescription() , " "};
            model.addRow(row);
        }
        destinationsTable = new JTable(model);
        destinationsTable.setRowSelectionAllowed(true);
        pane.getViewport().add(destinationsTable);
        destinationsTable.addMouseListener(new MouseAdapter() {
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

   public String getDetails(){
        return detailsText.getText();
   }


    public void addDestinationActionListener(ActionListener actionListener){
        addDestination.addActionListener(actionListener);
    }

    public void deleteActionListener(ActionListener actionListener){
        deleteDestination.addActionListener(actionListener);
    }

    public void updateDestinationActionListener(ActionListener actionListener){
        updateDestination.addActionListener(actionListener);
    }



    public int[] getRows() {
        return rows;
    }

    public void setRows(int[] rows) {
        this.rows = rows;
    }

    public JTable getDestinationsTable() {
        return destinationsTable;
    }


    public String getName(){
        return nameText.getText();
    }





}

