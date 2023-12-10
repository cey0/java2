/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package edit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import view.*;

/**
 *
 * @author nadra
 */
public class editsiswa extends javax.swing.JFrame {

    /**
     * Creates new form crudsiswa
     */
   Connection c;
    Statement st;
    String sql;
    ResultSet rs;
     private DefaultTableModel tbl;
        public editsiswa() {
            initComponents();
            this.tampildata();
            this.combokelas(); // Corrected method name
            this.combospp();   // Corrected method name
            
}
    public void tampildata(){
        int no = 1;
        tbl = new DefaultTableModel();
         tbl.addColumn("nisn");
         tbl.addColumn("nis");
         tbl.addColumn("nama");
         tbl.addColumn("id_kelas");
         tbl.addColumn("alamat");
         tbl.addColumn("no_telp");
         tbl.addColumn("id_spp");
         table.setModel(tbl);
         c = koneksi.getConnection();
         try{
              c = koneksi.getConnection();
             st = c.createStatement();
             sql = "SELECT * FROM siswa";
             rs = st.executeQuery(sql);
             
             while(rs.next()){
             tbl.addRow(new Object[]{
                rs.getString("nisn"),
                rs.getString("nis"),
                rs.getString("nama"),
                rs.getInt("id_kelas"),
                rs.getString("alamat"),
                rs.getString("no_telp"),
                rs.getInt("id_spp"),
             
             });
             }
         }catch(SQLException e){
             System.out.println(e.getMessage());
         }
    }
    public void combokelas() {
    idkelas.removeAllItems(); // Clear existing items in the combo box
    try {
        c = koneksi.getConnection();
        st = c.createStatement();
        sql = "SELECT id_kelas FROM kelas"; // Change this query to fetch data from your database table
        rs = st.executeQuery(sql);

        while (rs.next()) {
            idkelas.addItem(rs.getString("id_kelas")); // Add each item to the combo box
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}
    public void combospp() {
    idspp.removeAllItems(); // Clear existing items in the combo box
    try {
        c = koneksi.getConnection();
        st = c.createStatement();
        sql = "SELECT id_spp FROM data_spp"; // Change this query to fetch data from your database table
        rs = st.executeQuery(sql);

        while (rs.next()) {
            idspp.addItem(rs.getString("id_spp")); // Add each item to the combo box
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    
}
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        notelp = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        idkelas = new javax.swing.JComboBox<>();
        idspp = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Nnisn = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Nnis = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Nnama = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        alamat = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        delete = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        iduser = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel5.setText("no_telp");

        jLabel6.setText("id_spp");

        notelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notelpActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "nisn", "nis", "nama", "id_kelas", "alamat", "no_telp", "id_spp"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tableMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        idkelas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        idspp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setText("edit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("nisn");

        Nnisn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NnisnActionPerformed(evt);
            }
        });

        jLabel2.setText("nis");

        jLabel3.setText("nama");

        Nnama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NnamaActionPerformed(evt);
            }
        });

        jLabel7.setText("alamat");

        jButton3.setText("<");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel4.setText("id_kelas");

        delete.setText("delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        jLabel8.setText("id_user");

        iduser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        iduser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iduserActionPerformed(evt);
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
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Nnis, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Nnisn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addGap(18, 18, 18)
                                    .addComponent(iduser, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel7))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(notelp, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                                        .addComponent(Nnama)
                                        .addComponent(idkelas, 0, 93, Short.MAX_VALUE)
                                        .addComponent(idspp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(alamat)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(delete)
                                .addGap(17, 17, 17))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(delete))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(Nnisn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(Nnis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(Nnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(idkelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(alamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(notelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idspp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(iduser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // Kembali ke halaman sebelumnya (tanpa menginstansiasi ulang)
        crudsiswa cs = new crudsiswa();
        cs.show();
        this.dispose(); // Tutup halaman edit
    }//GEN-LAST:event_jButton3ActionPerformed

    private void NnamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NnamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NnamaActionPerformed

    private void NnisnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NnisnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NnisnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
 try {
        c = koneksi.getConnection();

        String selectedIdKelas = (String) idkelas.getSelectedItem();
        String selectedIdSpp = (String) idspp.getSelectedItem();
        String selectediduser = (String) iduser.getSelectedItem();

        try {
            int idKelas = Integer.parseInt(selectedIdKelas);
            int idSpp = Integer.parseInt(selectedIdSpp);
            int idUser = Integer.parseInt(selectediduser);

            String sql = "UPDATE siswa SET id_user=? nis=?, nama=?, id_kelas=?, alamat=?, no_telp=?, id_spp=? WHERE nisn=?";
            PreparedStatement st = c.prepareStatement(sql);
            st.setInt(1,idUser);
            st.setString(1, Nnis.getText()); // Update nis
            st.setString(2, Nnama.getText()); // Update nama
            st.setInt(3, idKelas); // Update id_kelas as an integer
            st.setString(4, alamat.getText()); // Update alamat
            st.setString(5, notelp.getText()); // Update no_telp
            st.setInt(6, idSpp); // Update id_spp as an integer
            st.setString(7, Nnisn.getText()); // Update based on nisn

            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            tampildata();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid id_kelas or id_spp value. Please select a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void notelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notelpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_notelpActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        try {
        c = koneksi.getConnection();
        String sql = "DELETE FROM siswa WHERE nisn=?";
        PreparedStatement st = c.prepareStatement(sql);
        st.setString(1, Nnisn.getText()); // Delete based on nisn
        st.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data berhasil dihapus", "Pesan", JOptionPane.INFORMATION_MESSAGE);
        tampildata(); // Refresh the table
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    }//GEN-LAST:event_deleteActionPerformed

    private void tableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tableMouseEntered

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        int baris = table.getSelectedRow();
        Nnisn.setText(tbl.getValueAt(baris, 0).toString());
        Nnis.setText(tbl.getValueAt(baris, 1).toString());
        Nnama.setText(tbl.getValueAt(baris, 2).toString());
        idkelas.setSelectedItem(tbl.getValueAt(baris, 3).toString());
        alamat.setText(tbl.getValueAt(baris, 4).toString());
        notelp.setText(tbl.getValueAt(baris, 5).toString());
        idspp.setSelectedItem(tbl.getValueAt(baris, 6).toString());
    }//GEN-LAST:event_tableMouseClicked

    private void iduserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iduserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_iduserActionPerformed
private String selectedIdTransaksi;
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(editsiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(editsiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(editsiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(editsiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new editsiswa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Nnama;
    private javax.swing.JTextField Nnis;
    private javax.swing.JTextField Nnisn;
    private javax.swing.JTextField alamat;
    private javax.swing.JButton delete;
    private javax.swing.JComboBox<String> idkelas;
    private javax.swing.JComboBox<String> idspp;
    private javax.swing.JComboBox<String> iduser;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField notelp;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
