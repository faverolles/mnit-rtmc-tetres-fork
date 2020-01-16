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
package ncrtes.results;

import common.infra.Infra;
import ncrtes.NCRTESConfig;
import common.util.FileHelper;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author "Chongmyung Park <chongmyung.park@gmail.com>"
 */
public class ResultListDialog extends javax.swing.JDialog {

    private String resultDir = NCRTESConfig.resultDir;

    /**
     * Creates new form ResultListDialog
     */
    public ResultListDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.init();
    }

    public void init() {
        this.setResultList();

        this.lstResults.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
                    int index = list.locationToIndex(evt.getPoint());
                    openFolder(index);
                }
            }
        });

    }

    private void openFolder(int index) {
        String dirName = this.lstResults.getModel().getElementAt(index);
        FileHelper.openDirectory(this.outputPath(dirName));
    }

    private void deleteItem() {
        String dirName = this.lstResults.getSelectedValue();
        if(dirName.isEmpty()) {
            return;
        }
        
        String outputPath = this.outputPath(dirName);        
        int reply = JOptionPane.showConfirmDialog(null, "Delete Result Directory\n(Path: "+outputPath+")");
        if(reply == JOptionPane.YES_OPTION) {
            FileHelper.deleteDirectory(this.outputPath(dirName));         
            this.setResultList();
        } else {
            return;
        }
    }

    private void setResultList() {
        String resultPath = this.outputPath(null);
        File outputDir = new File(resultPath);
        if(!outputDir.exists()) {
            return;
        }
        File[] listOfResults = outputDir.listFiles();
        if(listOfResults == null) {
            return;
        }
        DefaultListModel<String> model = new DefaultListModel();
        for (int i = 0; i < listOfResults.length; i++) {
            if (listOfResults[i].isDirectory()) {
                model.addElement(listOfResults[i].getName());
            }
        }
        this.lstResults.setModel(model);
    }

    private String outputPath(String resultDir) {
        String dataPath = Infra.getInstance().getDataPath();       
        if (resultDir == null) {
            return FileHelper.absolutePath(dataPath + "/" + this.resultDir);
        } else {
            return FileHelper.absolutePath(dataPath + "/" + this.resultDir + "/" + resultDir);
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

        btnClose = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstResults = new JList<>();
        jLabel2 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Estimation Results");

        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        jLabel1.setText("Estimation Results");

        jScrollPane1.setViewportView(lstResults);

        jLabel2.setText("(double-click item to see result files)");

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClose)
                    .addComponent(btnDelete))
                .addContainerGap())
        );

        pack();
    }private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {
        this.deleteItem();
    }


    // Variables declaration - do not modify
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnDelete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private JList<String> lstResults;
    // End of variables declaration

}
