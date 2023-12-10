/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package procces;
<<<<<<< Updated upstream
import koneksi.koneksi;
import login.login;
import dashoard.admin;
import javax.swing.JOptionPane;
import dashoard.petugas;
import role.util;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.List;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JFormattedTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
=======
>>>>>>> Stashed changes

/**
 *
 * @author admin
 */
public class viewAdmin extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
     Connection c;
    Statement st;
    String sql;
    ResultSet rs;
    private DefaultTableModel tbl;
    public viewAdmin() {
        initComponents();
        comboboxkelas();
        comboboxdate();
        this.tampildata();
        
        
    }
     public void tampildata(){
         int no = 1;
    tbl = new DefaultTableModel();
    tbl.addColumn("id_pembayaran");
    tbl.addColumn("id_user");
    tbl.addColumn("nisn");
    tbl.addColumn("tgl_bayar");
    tbl.addColumn("bulan_bayar");
    tbl.addColumn("tahun_bayar");
    tbl.addColumn("id_spp");
    tbl.addColumn("jumlah_bayar");
    
    table.setModel(tbl); // Set the table model here

    c = koneksi.getConnection();
    try {
        st = c.createStatement();
        sql = "SELECT * FROM pembayaran";
        rs = st.executeQuery(sql);

        while (rs.next()) {
            tbl.addRow(new Object[]{
                rs.getInt("id_pembayaran"),
                rs.getInt("id_user"),
                rs.getString("nisn"),
                rs.getString("tgl_bayar"),
                rs.getString("bulan_bayar"),
                rs.getString("tahun_dibayar"),
                rs.getInt("id_spp"),
                rs.getInt("jumlah_bayar")
            });
        }
        
        tbl.fireTableDataChanged(); // Refresh the table display
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    public void comboboxkelas() {
   
    try {
        c = koneksi.getConnection();
        st = c.createStatement();
        sql = "SELECT nisn FROM siswa"; // Change this query to fetch data from your database table
        rs = st.executeQuery(sql);

        while (rs.next()) {
            idkelas.addItem(rs.getString("nisn")); // Add each item to the combo box
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    }
    public void comboboxdate() {
   
    try {
        c = koneksi.getConnection();
        st = c.createStatement();
        sql = "SELECT 	tgl_bayar FROM pembayaran"; // Change this query to fetch data from your database table
        rs = st.executeQuery(sql);

        while (rs.next()) {
            date.addItem(rs.getString("tgl_bayar")); // Add each item to the combo box
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    }
    public void filter(String selectedKelas) {
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
    table.setRowSorter(sorter);

    RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter(selectedKelas, 2); // 2 adalah indeks kolom kelas
    sorter.setRowFilter(rowFilter);
}
    public void filterDate(String selectedDate) {
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
    table.setRowSorter(sorter);

    RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter(selectedDate, 3); // 3 adalah indeks kolom tanggal
    sorter.setRowFilter(rowFilter);
}
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

<<<<<<< Updated upstream
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        date = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        idkelas = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("view");

        jLabel2.setText("nisn");

        jButton1.setText("generate laporan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("dashboard");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        date.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "pilih"}));
        date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateActionPerformed(evt);
            }
        });

        jLabel3.setText("tanggal");

        idkelas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "pilih"}));
        idkelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idkelasActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id_pembayaran", "id_user", "nisn", "tgl_bayar", "bulan_bayar", "tahun_bayar", "id_spp", "jumlah_bayar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

=======
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

>>>>>>> Stashed changes
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
<<<<<<< Updated upstream
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(353, 353, 353)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jButton2)
                        .addGap(150, 150, 150)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel2))
                            .addComponent(jLabel3))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idkelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(155, 155, 155)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(142, 142, 142))
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jButton2)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel2)
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(idkelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(89, 89, 89)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
=======
            .addGap(0, 468, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 315, Short.MAX_VALUE)
>>>>>>> Stashed changes
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

<<<<<<< Updated upstream
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        util role = util.getInstance();
        String user = role.getRole();
        
        if (user.equals("admin")) {
            admin fo = new admin();
            fo.show();
            this.dispose();
    } else if (user.equals("petugas")) {
    // Lakukan sesuatu untuk peran "User"
        petugas fa = new petugas();
            fa.show();
            this.dispose();
    } else {
    // Handle peran lainnya atau tampilkan pesan kesalahan
        JOptionPane.showMessageDialog(this, "Anda tidak memiliki izin untuk melihat ini.");
    }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Check the user's role
    util role = util.getInstance();
    String userRole = role.getRole();

    // Only allow admin to generate the report
    if ("admin".equals(userRole)) {
    try {
        // Define the output file name
        String outputFile = "laporan_pembayaran.pdf";

        // Create a Document for the PDF
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(outputFile));
        document.open();

        // Add a title to the PDF
        document.add(new Paragraph("Laporan Pembayaran"));

        // Create PDF table with the same columns as the JTable
        PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
        
        // Add headers from JTable to PDF table
        for (int i = 0; i < table.getColumnCount(); i++) {
            pdfTable.addCell(new Phrase(table.getColumnName(i)));
        }

        // Add data from the JTable to the PDF table
        for (int i = 0; i < table.getRowCount(); i++) {
            for (int j = 0; j < table.getColumnCount(); j++) {
                Object cellValue = table.getValueAt(i, j);
                pdfTable.addCell(new Phrase(String.valueOf(cellValue)));
            }
        }

        // Add PDF table to the document
        document.add(pdfTable);

        document.close();

        JOptionPane.showMessageDialog(this, "Laporan telah berhasil dibuat: " + outputFile);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat membuat laporan: " + e.getMessage());
    }

} else {
        JOptionPane.showMessageDialog(this, "Anda tidak memiliki izin untuk generate laporan.");
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void idkelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idkelasActionPerformed
        // TODO add your handling code here:
        String selectedKelas = idkelas.getSelectedItem().toString();
    filter(selectedKelas);
        
    }//GEN-LAST:event_idkelasActionPerformed

    private void dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateActionPerformed
        // TODO add your handling code here:
        String selectedDate = date.getSelectedItem().toString();
    filterDate(selectedDate);
    }//GEN-LAST:event_dateActionPerformed

=======
>>>>>>> Stashed changes
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
            java.util.logging.Logger.getLogger(viewAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(viewAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(viewAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(viewAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new viewAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
<<<<<<< Updated upstream
    private javax.swing.JComboBox<String> date;
    private javax.swing.JComboBox<String> idkelas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
=======
>>>>>>> Stashed changes
    // End of variables declaration//GEN-END:variables
}
