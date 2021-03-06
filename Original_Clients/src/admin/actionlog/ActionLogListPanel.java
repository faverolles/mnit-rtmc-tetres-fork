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
package admin.actionlog;

import common.log.TICASLogger;
import common.pyticas.HttpResult;
import admin.TeTRESConfig;
import admin.api.ActionLogClient;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import admin.types.AbstractDataChangeListener;
import admin.types.ActionLogInfo;
import org.apache.logging.log4j.core.Logger;

/**
 *
 * @author Chongmyung Park
 */
public class ActionLogListPanel extends JPanel {

    private List<ActionLogInfo> alList = new ArrayList<>();
    private ActionLogClient model;
    private Logger logger;

    public ActionLogListPanel() {
        initComponents();
    }

    /**
     * *
     * initialize variables and UI
     */
    public void init() {
        this.logger = TICASLogger.getLogger(this.getClass().getName());
        this.model = new ActionLogClient();

        // data change listener
        this.model.addChangeListener(new AbstractDataChangeListener<ActionLogInfo>() {

            @Override
            public void listFailed(HttpResult result) {
                JOptionPane.showMessageDialog(TeTRESConfig.mainFrame, "Fail to get list of data change logs");
            }

            @Override
            public void listSuccess(List<ActionLogInfo> list) {
                alList = list;
                setTable();
            }
        });
        this.requestList();
    }
    
    public void requestList() {
        Integer limit = Integer.parseInt((String) this.cbxLimit.getSelectedItem());
        this.model.list(limit);
    }
    
    public void refresh() {
        this.requestList();
    }
    
    private void requestRefresh() {
        this.refresh();
    }

    private void requestProceed() {
        this.model.proceed();
    }    
    
    /**
     * *
     * set list table
     */
    protected void setTable() {
        List<ActionLogInfo> list = new ArrayList<ActionLogInfo>(this.alList);
        final DefaultTableModel tm = (DefaultTableModel) this.tblActionLog.getModel();
        tm.getDataVector().removeAllElements();
        tm.fireTableDataChanged();
        for (ActionLogInfo s : list) {
            tm.addRow(new Object[]{s.reg_date, s.action_type, s.data_desc, s.handled, s.getStatus()});
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        asyncRequestAdapter1 = new org.jdesktop.http.async.event.AsyncRequestAdapter();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblActionLog = new javax.swing.JTable();
        btnRefresh = new javax.swing.JButton();
        btnProceed = new javax.swing.JButton();
        cbxLimit = new javax.swing.JComboBox<>();

        jLabel2.setText("items");

        tblActionLog.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Time", "Action", "Data", "Finished", "Status"
            }
        ) {
            Class[] types = new Class [] {
                String.class, String.class, String.class, Boolean.class, String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblActionLog.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jScrollPane3.setViewportView(tblActionLog);
        if (tblActionLog.getColumnModel().getColumnCount() > 0) {
            tblActionLog.getColumnModel().getColumn(0).setMinWidth(120);
            tblActionLog.getColumnModel().getColumn(0).setPreferredWidth(120);
            tblActionLog.getColumnModel().getColumn(0).setMaxWidth(200);
            tblActionLog.getColumnModel().getColumn(1).setMinWidth(80);
            tblActionLog.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblActionLog.getColumnModel().getColumn(1).setMaxWidth(200);
            tblActionLog.getColumnModel().getColumn(3).setMinWidth(50);
            tblActionLog.getColumnModel().getColumn(3).setPreferredWidth(50);
            tblActionLog.getColumnModel().getColumn(3).setMaxWidth(100);
            tblActionLog.getColumnModel().getColumn(4).setMinWidth(300);
            tblActionLog.getColumnModel().getColumn(4).setPreferredWidth(300);
            tblActionLog.getColumnModel().getColumn(4).setMaxWidth(1500);
        }

        btnRefresh.setText("Refresh Data Change Status");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnProceed.setText("Update Database with Changed Data");
        btnProceed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProceedActionPerformed(evt);
            }
        });

        cbxLimit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "100", "200", "300", "400", "500", "600", "700", "800", "900", "1000" }));
        cbxLimit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxLimitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(cbxLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnProceed, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbxLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnProceed, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {
        this.requestRefresh();
    }

    private void btnProceedActionPerformed(java.awt.event.ActionEvent evt) {
        this.requestProceed();
    }

    private void cbxLimitActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }


    // Variables declaration - do not modify
    private org.jdesktop.http.async.event.AsyncRequestAdapter asyncRequestAdapter1;
    private javax.swing.JButton btnProceed;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JComboBox<String> cbxLimit;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblActionLog;
    // End of variables declaration

}
