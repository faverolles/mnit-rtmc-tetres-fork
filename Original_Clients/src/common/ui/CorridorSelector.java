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
package common.ui;

import common.infra.Corridor;
import common.infra.Infra;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chong
 */
public class CorridorSelector extends javax.swing.JPanel {
    
    public interface CorridorSelectedListener {
        public void OnSelected(int selectedIndex, Corridor corridor);
    }
    
    protected List<CorridorSelectedListener> onSelected = new ArrayList<CorridorSelectedListener>();
    
    /**
     * Creates new form CorridorSelector
     */
    public CorridorSelector() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        cbxCorridors = new javax.swing.JComboBox();

        cbxCorridors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCorridorsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cbxCorridors, 0, 267, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cbxCorridors)
        );
    }private void cbxCorridorsActionPerformed(java.awt.event.ActionEvent evt) {
        if(this.onSelected != null) {
            int slt = this.cbxCorridors.getSelectedIndex();
            if(slt == 0){
                for(CorridorSelectedListener callback : this.onSelected) {
                    callback.OnSelected(slt, null);                    
                }
            }
            else {
                for(CorridorSelectedListener callback : this.onSelected) {
                    callback.OnSelected(slt, (Corridor)this.cbxCorridors.getSelectedItem());
                }                
            }
        }
    }

    public void init(Infra infra, CorridorSelectedListener callback) {
        this.cbxCorridors.addItem("Select Corridor");
        for (Corridor c : infra.getCorridors()) {
            this.cbxCorridors.addItem(c);
        }
        this.onSelected.add(callback);        
    }

    // Variables declaration - do not modify
    private javax.swing.JComboBox cbxCorridors;
    // End of variables declaration
}
