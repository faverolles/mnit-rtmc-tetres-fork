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
package ncrtes.targetstation.map;

import ncrtes.targetstation.ISetTargetStation;

/**
 *
 * @author "Chongmyung Park <chongmyung.park@gmail.com>"
 */
public class TSMap extends javax.swing.JPanel {

    private TSSelectionHelper tsSelectionHelper;
    public TSMapHelper mapHelper;

    /**
     * Creates new form TargetStationMap
     */
    public TSMap() {
        initComponents();
    }
    
    public void init(ISetTargetStation tsSetCallback) {
        tsSelectionHelper = new TSSelectionHelper();
        tsSelectionHelper.init(this, this.jmKit, tsSetCallback);        
        mapHelper = tsSelectionHelper.mapHelper;        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jmKit = new org.jdesktop.swingx.JXMapKit();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jmKit, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jmKit, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// Variables declaration - do not modify
    private org.jdesktop.swingx.JXMapKit jmKit;
    // End of variables declaration
}