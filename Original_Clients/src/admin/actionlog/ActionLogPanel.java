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

import javax.swing.JPanel;
import common.ui.IInitializable;
import admin.actionlog.ActionLogListPanel;

/**
 *
 * @author Chongmyung Park <chongmyung.park@gmail.com>
 */
public class ActionLogPanel extends JPanel implements IInitializable {
    private ActionLogListPanel panActionLog;

    /**
     * Creates new form SpecialEventPanel
     */
    public ActionLogPanel() {
        initComponents();        
    }
    
    @Override
    public void init() {
        this.panActionLog = new ActionLogListPanel();
        this.panActionLog.init();
        this.add(this.panActionLog);
    }

    @Override
    public void refresh() {
        this.panActionLog.refresh();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// Variables declaration - do not modify
    // End of variables declaration
}
