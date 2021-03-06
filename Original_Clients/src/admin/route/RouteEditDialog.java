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
package admin.route;

import common.pyticas.HttpResult;
import admin.TeTRESConfig;
import admin.api.ReliabilityRouteClient;
import admin.types.AbstractDataChangeListener;
import admin.types.ReliabilityRouteInfo;
import java.awt.Frame;
import java.util.Objects;
import javax.swing.JOptionPane;

/**
 *
 * @author Chongmyung Park
 */
public class RouteEditDialog extends javax.swing.JDialog {
    private ReliabilityRouteInfo ttri;
    public String route_name;
    private ReliabilityRouteClient model;

    /**
     *
     * @param parent
     * @param rnodes
     */
    public RouteEditDialog(Frame parent, ReliabilityRouteInfo ttri) {
        super(parent, true);
        initComponents();
        init(ttri);
    }
    
    /**
     * initialize UI
     * 
     * @param ttri travel time route information
     */
    private void init(ReliabilityRouteInfo ttri) {
        this.ttri = ttri;
        this.model = new ReliabilityRouteClient();
        this.route_name = ttri.name;
        this.tbxRouteDesc.setText(ttri.description);
        this.tbxRouteName.setText(ttri.name);        
        
        this.model.addChangeListener(new AbstractDataChangeListener<ReliabilityRouteInfo>() {
            @Override
            public void updateFailed(HttpResult result, ReliabilityRouteInfo obj) {
                JOptionPane.showMessageDialog(TeTRESConfig.mainFrame, String.format("Fail to save route (%s)", result.res_msg));
            }

            @Override
            public void updateSuccess(int id) {
                dispose();
            }
            
        });
    }
    
    /***
     * update travel time route information
     */
    private void updateRoute() {
        
        String name = this.tbxRouteName.getText();        
        String desc = this.tbxRouteDesc.getText();
        if (!checkName(name)) {
            return;
        }
        ReliabilityRouteInfo ex_ttri = this.ttri.clone();        
        this.ttri.name = name;
        this.ttri.description = desc;
        this.ttri.route.name = name;
        this.ttri.route.desc = desc;
        this.model.update(ex_ttri, this.ttri);       
    }

    /**
     * check if route name is valid
     * 
     * @param name route name
     * @return true if route name is valid, else false
     */
    private boolean checkName(String name) {
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(TeTRESConfig.mainFrame, "Fail : Route name is required");
            return false;
        }
        if (name.length() > 100) {
            JOptionPane.showMessageDialog(TeTRESConfig.mainFrame, "Fail : Route name is long. (100 characters limit)");
            return false;
        }
        ReliabilityRouteInfo exRoute = this.model.getByNameSynced(name);
        if (exRoute != null && !Objects.equals(exRoute.id, this.ttri.id)) {
            JOptionPane.showMessageDialog(TeTRESConfig.mainFrame, "Route name already exists.");
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tbxRouteName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbxRouteDesc = new javax.swing.JTextArea();
        btnUpdate = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit");

        jLabel2.setText("Route Name");

        jLabel3.setText("Description");

        tbxRouteDesc.setColumns(20);
        tbxRouteDesc.setRows(5);
        jScrollPane1.setViewportView(tbxRouteDesc);

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tbxRouteName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbxRouteName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {
        updateRoute();
    }

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea tbxRouteDesc;
    private javax.swing.JTextField tbxRouteName;
    // End of variables declaration

}
