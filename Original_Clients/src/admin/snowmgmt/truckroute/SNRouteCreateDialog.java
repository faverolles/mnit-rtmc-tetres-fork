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
package admin.snowmgmt.truckroute;

import common.infra.RNode;
import common.pyticas.HttpResult;
import common.route.Route;
import common.route.RouteCreationHelper;
import admin.api.SnowRouteClient;
import admin.types.AbstractDataChangeListener;
import admin.types.SnowRouteInfo;
import javax.swing.JOptionPane;
import admin.TeTRESConfig;

/**
 *
 * @author Chongmyung Park <chongmyung.park@gmail.com>
 */
public class SNRouteCreateDialog extends javax.swing.JDialog {

    private RouteCreationHelper routeCreationHelper;
    private SnowRouteClient model;    

    /**
     * Creates new form RouteCreateDialog
     */
    public SNRouteCreateDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.init();
    }
    
    /**
     * initialize variables and UI
     */
    private void init() {
        this.model = new SnowRouteClient();
        this.routeCreationHelper = new RouteCreationHelper();
        this.routeCreationHelper.init(null, this.jmKit, this.cbxCorridors, this.lbxRoutes);

        this.model.addChangeListener(new AbstractDataChangeListener<SnowRouteInfo>() {

            @Override
            public void insertFailed(HttpResult result, SnowRouteInfo obj) {
                JOptionPane.showMessageDialog(rootPane, String.format("Fail to save route (%s)", obj.name));
            }

            @Override
            public void insertSuccess(Integer insertedId) {
                dispose();
            }
        });
    }
    
    /***
     * save snow route
     */
    private void saveSnowRoute() {
        if (!this.routeCreationHelper.isReady) {
            JOptionPane.showMessageDialog(TeTRESConfig.mainFrame, "Please click after finishing route creation");
            return;
        }

        String name = this.tbxName.getText();
        String desc = this.tbxDesc.getText();
        String prjId = this.tbxProjectId.getText();
        
        if (!this.checkName(name)) {
            return;
        }

        Route r = new Route(name, desc);
        for (RNode rn : this.routeCreationHelper.routePointList) {
            r.addRNode(rn);
        }
        
        // second parameter is null because route2 will be created in the server
        SnowRouteInfo ttri = new SnowRouteInfo(r, null);        
        ttri.prj_id = prjId;
        
        this.model.insert(ttri);
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
        return true;
    }
    
    /***
     * reset map and rnode list
     */
    private void reset() {
        this.routeCreationHelper.reset();
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
        cbxCorridors = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        btnSaveRoute = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lbxRoutes = new javax.swing.JList();
        tbxName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbxDesc = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        tbxProjectId = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jmKit = new org.jdesktop.swingx.JXMapKit();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New Snow Management Section");
        setType(Type.POPUP);

        jLabel2.setText("Select starting corridor :");

        cbxCorridors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCorridorsActionPerformed(evt);
            }
        });

        jLabel3.setText("Station and Ramps included in Section");

        btnSaveRoute.setText("Save Route");
        btnSaveRoute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveRouteActionPerformed(evt);
            }
        });

        btnReset.setText("Reset Route");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(lbxRoutes);

        jLabel4.setText("Name");

        tbxDesc.setColumns(20);
        tbxDesc.setRows(5);
        jScrollPane1.setViewportView(tbxDesc);

        jLabel5.setText("Description");

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jLabel6.setText("Truck Route ID (optional)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tbxName)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCancel)
                        .addGap(10, 10, 10)
                        .addComponent(btnReset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSaveRoute))
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1)
                    .addComponent(cbxCorridors, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(tbxProjectId, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbxName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbxProjectId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxCorridors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSaveRoute, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jmKit, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jmKit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }private void cbxCorridorsActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void btnSaveRouteActionPerformed(java.awt.event.ActionEvent evt) {
        saveSnowRoute();
    }

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {
        this.reset();
    }

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSaveRoute;
    private javax.swing.JComboBox cbxCorridors;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private org.jdesktop.swingx.JXMapKit jmKit;
    private javax.swing.JList lbxRoutes;
    private javax.swing.JTextArea tbxDesc;
    private javax.swing.JTextField tbxName;
    private javax.swing.JTextField tbxProjectId;
    // End of variables declaration
}
