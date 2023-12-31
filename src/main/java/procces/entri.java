/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package procces;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import role.util;
import dashoard.admin;
import dashoard.petugas;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
/**
 *
 * @author nadra
 */
public class entri extends javax.swing.JFrame {

    /**
     * Creates new form entri
     */
     Connection c;
    Statement st;
    String sql;
    ResultSet rs;
    private DefaultTableModel tbl;
    public entri() {
        initComponents();
        tampildata();
        combopetugas();
        combonisn();
        combospp();
        
        
    }
   

        // Close the result set and handle exceptions

   

//    private void populateMonthComboBox(String nisn) {
//    month.removeAllItems(); // Bersihkan item-item yang ada pada combo box bulan
//    List<String> allMonths = Arrays.asList("Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember");
//
//    try {
//        c = koneksi.getConnection();
//        st = c.createStatement();
//        sql = "SELECT bulan_bayar FROM pembayaran WHERE nisn = '" + nisn + "'"; // Ambil bulan-bulan yang sudah dibayar oleh NISN tertentu
//        rs = st.executeQuery(sql);
//
//        List<String> paidMonths = new ArrayList<>();
//        while (rs.next()) {
//            String[] months = rs.getString("bulan_bayar").split(" dan "); // Misal: "Januari dan Februari"
//            paidMonths.addAll(Arrays.asList(months));
//        }
//
//        // Hapus bulan-bulan yang sudah dibayar dari list semua bulan
//        allMonths.removeAll(paidMonths);
//
//        // Tambahkan bulan-bulan yang belum dibayar ke combo box bulan
//        for (String montha : allMonths) {
//            month.addItem(montha);
//        }
//    } catch (SQLException e) {
//        System.out.println(e.getMessage());
//    }
//}
    private void populateBulanBayarCombo() {
     String Nisn = (String) nisn.getSelectedItem();
    String Year = (String) years.getSelectedItem();

    try {
        java.sql.Statement statement = c.createStatement();
        String sql = "SELECT DISTINCT bulan_bayar " +
                     "FROM pembayaran " +
                     "WHERE nisn = '" + Nisn + "' " +
                     "AND tahun_dibayar = " + Year;

        java.sql.ResultSet res = statement.executeQuery(sql);

        // Create a HashSet to store the months that have been paid
        HashSet<String> paidMonths = new HashSet<>();
        while (res.next()) {
            String months = res.getString("bulan_bayar");
            paidMonths.add(months);
        }

        // Populate the bulanBayar combo box with all months in order
        month.removeAllItems(); // Clear existing items
        String[] allMonths = {"januari", "febuari", "maret", "april", "mei", "juni", "juli", "agustus", "september", "oktober", "november", "december"};
        month.setModel(new DefaultComboBoxModel<>(allMonths));

        // Remove paid months from the list
        if (!paidMonths.isEmpty()) {
            for (String paidMonth : paidMonths) {
                month.removeItem(paidMonth);
            }
        }

        // Close the result set
        res.close();

    } catch (SQLException e) {
        e.printStackTrace(); // Handle the exception properly
    }
}


    public void tampildata(){
        int no = 1;
        tbl = new DefaultTableModel();
        tbl.addColumn("id_pembayaran");
        tbl.addColumn("id_user");
        tbl.addColumn("nisn");
        tbl.addColumn("tgl_bayar");
        tbl.addColumn("bulan_bayar");
        tbl.addColumn("tahun_dibayar");
        tbl.addColumn("id_spp");
        tbl.addColumn("jumlah_bayar");
        table.setModel(tbl);
        c = koneksi.getConnection();
        try{
            c = koneksi.getConnection();
             st = c.createStatement();
             sql = "SELECT * FROM pembayaran";
             rs = st.executeQuery(sql);
             
             while(rs.next()){
             tbl.addRow(new Object[]{
                rs.getInt("id_pembayaran"),
                rs.getInt("id_user"),
                rs.getString("nisn"),
                rs.getString("tgl_bayar"),
                rs.getString("bulan_bayar"),
                rs.getString("tahun_dibayar"),
                rs.getInt("id_spp"),
                rs.getInt("jumlah_bayar"),
                
                
             
             });
             }
            
        }catch(SQLException e){
             System.out.println(e.getMessage());
            
        }
    }
   public void combospp() {
    idspp.removeAllItems(); // Clear existing items in the combo box
    try {
        c = koneksi.getConnection();
        st = c.createStatement();
        sql = "SELECT id_spp, nominal FROM data_spp"; // Change this query to fetch data from your database table
        rs = st.executeQuery(sql);

        while (rs.next()) {
            String idSpp = rs.getString("id_spp");
            idspp.addItem(idSpp); // Add each item to the combo box
            
            // Assuming nominal is a JTextField
            String nominalValue = rs.getString("nominal");
            idspp.addActionListener(new ActionListener() {
                
                public void actionPerformed(ActionEvent e) {
                    if (idspp.getSelectedItem() != null) {
                        String selectedId = idspp.getSelectedItem().toString();
                        if (selectedId.equals(idSpp)) {
                            nominal.setText(nominalValue);
                        }
                    }
                }
            });
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}

    public void combopetugas(){
       
    try {
        c = koneksi.getConnection();
        st = c.createStatement();
        sql = "SELECT id_user FROM user"; // Change this query to fetch data from your database table
        rs = st.executeQuery(sql);

        while (rs.next()) {
            iduser.addItem(rs.getString("id_user")); // Add each item to the combo box
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    } 
    }
    public void combonisn(){
      
    try {
        c = koneksi.getConnection();
        st = c.createStatement();
        sql = "SELECT nisn FROM siswa"; // Change this query to fetch data from your database table
        rs = st.executeQuery(sql);

        while (rs.next()) {
            nisn.addItem(rs.getString("nisn")); // Add each item to the combo box
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        iduser = new javax.swing.JComboBox<>();
        nisn = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        years1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        idspp = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        nominal = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        data = new javax.swing.JTextArea();
        date = new com.toedter.calendar.JDateChooser();
        month = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        years = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("petugas");

        jLabel2.setText("nisn");

        jLabel3.setText("tanggal");

        iduser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { }));
        iduser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iduserActionPerformed(evt);
            }
        });

        nisn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { }));
        nisn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nisnActionPerformed(evt);
            }
        });

        jLabel4.setText("bulan");

        jLabel5.setText("tahun");

        years1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                years1ActionPerformed(evt);
            }
        });

        jLabel6.setText("spp");

        idspp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { }));
        idspp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idsppActionPerformed(evt);
            }
        });

        jLabel7.setText("total");

        nominal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nominalActionPerformed(evt);
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

        jButton1.setText("entry");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        data.setColumns(20);
        data.setRows(5);
        jScrollPane2.setViewportView(data);

        month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { }));
        month.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthActionPerformed(evt);
            }
        });

        jButton2.setText("dashboard");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("cetak pdf");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        years.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2023", "2024"}));
        years.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(52, 52, 52)
                                .addComponent(idspp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(years1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(years, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(nisn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(29, 29, 29)
                            .addComponent(iduser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(34, 34, 34)
                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(44, 44, 44)
                        .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(38, 38, 38)
                        .addComponent(nominal, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(111, 111, 111)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)
                        .addComponent(jScrollPane2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(iduser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(nisn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(years1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(years, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(idspp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(nominal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addGap(0, 312, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void years1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_years1ActionPerformed
        // TODO add your handling code here:
        populateBulanBayarCombo();
    }//GEN-LAST:event_years1ActionPerformed

    private void nominalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nominalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nominalActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       // Inside your jButton1ActionPerformed method
try {
    String petugas = iduser.getSelectedItem().toString();
    String idnisn = nisn.getSelectedItem().toString();
    Date tanggal = date.getDate();
    SimpleDateFormat mysqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String dateStr = mysqlDateFormat.format(tanggal);
    String bulan = month.getSelectedItem().toString();
    String tahun = years1.getText();
    String spp = idspp.getSelectedItem().toString();
    String total = nominal.getText();
    
    String gabungB = bulan;
    String gabungN = total;

    // Get the current date
    LocalDate currentDate = LocalDate.now();

    // Convert the selected date to LocalDate
    LocalDate selectedDate = new java.sql.Date(tanggal.getTime()).toLocalDate();

    if (!selectedDate.isEqual(currentDate)) {
        JOptionPane.showMessageDialog(this, "Tanggal bukan hari ini, pembayaran tidak dapat dilakukan.");
    } else {
        String checkgabung = "SELECT * FROM pembayaran WHERE nisn = '" + idnisn + "' AND tgl_bayar = '" + dateStr + "'";
        rs = st.executeQuery(checkgabung);

        if (rs.next()) {
            // If a record with the same nisn and date exists
            String monthValue = rs.getString("bulan_bayar");
            gabungB = monthValue + " dan " + bulan;
            String existingTotal = rs.getString("jumlah_bayar");

            // Calculate the new total
            double newTotal = Double.parseDouble(existingTotal) + Double.parseDouble(total);

            // Update the database with the new bulan_bayar and jumlah_bayar
            String updateB = "UPDATE pembayaran SET bulan_bayar = '" + gabungB + "', jumlah_bayar = '" + newTotal + "' WHERE nisn = '" + idnisn + "' AND tgl_bayar = '" + dateStr + "'";
            int benarB = st.executeUpdate(updateB);

            if (benarB > 0) {
                JOptionPane.showMessageDialog(this, "Data berhasil diupdate");
            } else {
                JOptionPane.showMessageDialog(this, "Data tidak berhasil diupdate");
            }
            
            // Update the total in the 'kwitansi' text
            gabungN = String.valueOf(newTotal);
        } else {
            // If no record with the same nisn and date, insert a new one
            String insert = "INSERT INTO pembayaran (id_user, nisn, tgl_bayar, bulan_bayar, tahun_dibayar, id_spp, jumlah_bayar) " +
                    "VALUES ('" + petugas + "', '" + idnisn + "', '" + dateStr + "', '" + bulan + "', '" + tahun + "', '" + spp + "', '" + total + "')";
            
            int benar = st.executeUpdate(insert);
            if (benar > 0) {
                JOptionPane.showMessageDialog(this, "Data berhasil ditambah");
            } else {
                JOptionPane.showMessageDialog(this, "Data tidak berhasil ditambah");
            }
        }

        // Refresh the data in your table
        tampildata();

        // Prepare the kwitansi text
        String kwitansi = "Kwitansinya\n\n";
        kwitansi += "Petugas: " + petugas + "\n";
        kwitansi += "NISN: " + idnisn + "\n";
        kwitansi += "Tanggal Bayar: " + dateStr + "\n";
        kwitansi += "Bulan Bayar: " + gabungB + "\n";
        kwitansi += "Tahun Bayar: " + tahun + "\n";
        kwitansi += "ID SPP: " + spp + "\n";
        kwitansi += "Nominal: " + gabungN + "\n"; // Use the updated 'gabungN' for total

        // Display the updated 'kwitansi' text in the JTextArea
        data.setText(kwitansi);
    }
} catch (Exception ex) {
    ex.printStackTrace();
    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
}

    }//GEN-LAST:event_jButton1ActionPerformed

    private void iduserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iduserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_iduserActionPerformed

    private void idsppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idsppActionPerformed
        // TODO add your handling code here:
  
    }//GEN-LAST:event_idsppActionPerformed

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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         Document document = new Document();

    try {
        PdfWriter.getInstance(document, new FileOutputStream("output.pdf"));
        document.open();

        // Ambil teks dari JTextArea dan tulis ke file PDF
        String text = data.getText();
        document.add(new Paragraph(text));

        JOptionPane.showMessageDialog(this, "PDF berhasil dicetak!");
    } catch (DocumentException | FileNotFoundException e) {
        JOptionPane.showMessageDialog(this, "Terjadi kesalahan dalam mencetak PDF: " + e.getMessage());
    } finally {
        document.close();
    }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void monthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_monthActionPerformed

    private void nisnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nisnActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_nisnActionPerformed

    private void yearsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearsActionPerformed
        populateBulanBayarCombo();
    }//GEN-LAST:event_yearsActionPerformed

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
            java.util.logging.Logger.getLogger(entri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(entri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(entri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(entri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new entri().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea data;
    private com.toedter.calendar.JDateChooser date;
    private javax.swing.JComboBox<String> idspp;
    private javax.swing.JComboBox<String> iduser;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> month;
    private javax.swing.JComboBox<String> nisn;
    private javax.swing.JTextField nominal;
    private javax.swing.JTable table;
    private javax.swing.JComboBox<String> years;
    private javax.swing.JTextField years1;
    // End of variables declaration//GEN-END:variables
}
