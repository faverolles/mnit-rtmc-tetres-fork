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
package user;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import common.ui.IInitializable;
import user.panels.estimation.PanelEstimation;
import user.panels.operatingconditions.PanelOperatingConditions;
import user.panels.routegroup.PanelRouteGroup;

/**
 *
 * @author Chongmyung Park <chongmyung.park@gmail.com>
 */
public class TeTRESPanel extends javax.swing.JPanel implements IInitializable {

    private List<Integer> initializedTabs = new ArrayList<Integer>();
    private PanelOperatingConditions panOperatingConditions;
    private PanelOperatingConditions panelOperatingConditions;
    private PanelRouteGroup panelRouteGroup;
    private PanelEstimation panelEstimation;

    /**
     * Creates new form TeTRES Main Panel
     */
    public TeTRESPanel() {
        initComponents();
    }

    public TeTRESPanel(JFrame mainFrame) {
        initComponents();
    }

    @Override
    public void init() {        
       this.panelEstimation = new PanelEstimation();
       this.panelOperatingConditions = new PanelOperatingConditions();
       this.panelRouteGroup = new PanelRouteGroup();
       
       this.mainTab.add("Travel Time Reliability Estimation", this.panelEstimation);
       this.mainTab.add("Route Identification Configuration", this.panelRouteGroup);
       this.mainTab.add("Operating Condition Configuration", this.panelOperatingConditions);
       
        TeTRESInfoLoader.getInstance().init(new TeTRESInfoLoader.ICallback() {
            @Override
            public void ready() {
                panelEstimation.init();
                initializedTabs.add(0);
                ChangeListener changeListener = new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent changeEvent) {
                        Integer idx = mainTab.getSelectedIndex();
                        if (initializedTabs.contains(idx)) {
                            return;
                        }
                        initializedTabs.add(idx);
                        IInitializable tab = (IInitializable) mainTab.getSelectedComponent();
                        tab.init();
                    }
                };
                mainTab.addChangeListener(changeListener);                
            }

            @Override
            public void fail() {
                JOptionPane.showMessageDialog(null, "Fail to connect to TeTRES server");
            }
        });

    }
    
    @Override
    public void refresh() {
        // do nothing
    }    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        mainTab = new javax.swing.JTabbedPane();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainTab)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainTab)
                .addContainerGap())
        );
    }// Variables declaration - do not modify
    private javax.swing.JTabbedPane mainTab;
    // End of variables declaration
}
