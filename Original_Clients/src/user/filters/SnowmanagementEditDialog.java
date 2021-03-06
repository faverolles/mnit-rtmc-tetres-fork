/*
 * Copyright (C) 2018 NATSRL @ UMD (University Minnesota Duluth)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package user.filters;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author "Chongmyung Park <chongmyung.park@gmail.com>"
 */
public final class SnowmanagementEditDialog extends FilterEditDialog {

    private final String ROAD_CONDITION_LOST = "Lost";
    private final String ROAD_CONDITION_REGAINED = "Regained";
    private final String ROAD_CONDITION_ANY = "Any";
    private final String[] roadConditions;
    
    /**
     * Creates new form WeatherEditDialog
     *
     * @param parent
     * @param modal
     */
    public SnowmanagementEditDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
                
        this.cbxRoadCondition.removeAllItems();
        this.cbxRoadCondition.addItem(ROAD_CONDITION_LOST);
        this.cbxRoadCondition.addItem(ROAD_CONDITION_REGAINED);
        this.cbxRoadCondition.addItem(ROAD_CONDITION_ANY);
        
        this.roadConditions = new String[]{ ROAD_CONDITION_LOST, ROAD_CONDITION_REGAINED };
    }

    @Override
    public void reset() {
        this.cbxRoadCondition.setSelectedIndex(0);
        this.setLocationRelativeTo(this.getParent());
        this.isChanged = false;
    }

    @Override
    public void updateDataTable() {
        String road_condition = this.cbxRoadCondition.getSelectedItem().toString();

        DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        
        List<String> targetRoadConditions = new ArrayList<String>();      
        if(ROAD_CONDITION_ANY.equals(road_condition)) {           
            for(String _road_condition : this.roadConditions) {
                targetRoadConditions.add(_road_condition);
            }
        } else {
            targetRoadConditions.add(road_condition);
        }
       
        int nAdded = 0;
        for(String _road_condition : targetRoadConditions) {
            if(this.hasValueInTable(road_condition)) {
                continue;
            }
            nAdded += 1;
            model.addRow(new Object[]{ _road_condition});
        }
       
        if(nAdded == 0) {
//            JOptionPane.showMessageDialog(this, "Fail to add");
            return;
        }   
        
        this.isChanged = true;
    }

    private boolean hasValueInTable(String road_condition) {
        for (int i = 0; i < this.table.getRowCount(); i++) {
            String _road_condition = this.table.getValueAt(i, 0).toString().trim();
            if(_road_condition.equals(road_condition)) {
                return true;
            }
        }
        return false;
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbxRoadCondition = new javax.swing.JComboBox<>();
        btnOK = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Weather Filter Dialog");

        jLabel2.setText("Road Condition");

        cbxRoadCondition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lost", "Regained" }));

        btnOK.setText("Ok");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(btnClose))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnOK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxRoadCondition, 0, 283, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbxRoadCondition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOK)
                    .addComponent(btnClose))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {
        this.updateDataTable();
    }

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {
        this.reset();
        this.setVisible(false);
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnOK;
    private javax.swing.JComboBox<String> cbxRoadCondition;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration

}
