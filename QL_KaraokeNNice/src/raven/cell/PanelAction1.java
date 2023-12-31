package raven.cell;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author RAVEN
 */
public class PanelAction1 extends javax.swing.JPanel {

    /**
     * Creates new form PanelAction
     */
    public PanelAction1() {
        initComponents();
    }

    public void initEvent(TableActionEvent1 event, int row) {
        cmdAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                event.onAdd(row);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        cmdAdd = new raven.cell.ActionButton();

        setPreferredSize(new java.awt.Dimension(25, 25));
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0};
        layout.rowHeights = new int[] {0};
        setLayout(layout);

        cmdAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/raven/cell/add.png"))); // NOI18N
        cmdAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(cmdAdd, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void cmdAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdAddActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private raven.cell.ActionButton cmdAdd;
    // End of variables declaration//GEN-END:variables
}
