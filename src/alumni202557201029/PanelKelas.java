/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package alumni202557201029;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Al
 */
public class PanelKelas extends javax.swing.JPanel {

    /**
     * Creates new form PanelDashboard
     */
    public PanelKelas() {
        initComponents();
        
        //mengosongkan semua input from
        reset();    
        
        //menampilkan data kelas ke tabel
        load_tabel_kelas();
        
        //mengisi combobox jurusan dari databse
        comboJurusan();
        
        //mengisi combo box wali kelas dari db
        comboWali();
    }
    
    
    //membuat method untuk mengosongkan semua input form
    void reset(){
        //kosongkan field kode kelas
        tKodeKel.setText(null);
        
        //aktifkan kembali input kode kelas agar bisa diubah
        tKodeKel.setEditable(true);
        
        //kosongkan field nama kelas
        tNamaKel.setText(null);
        
        //kosongkan pilihan pada combo box tingkatan
        cTingkatan.setSelectedItem(null);
        
        //kosongkan pilihan pd combo box jurusan
        cJurusan.setSelectedItem(null);
        
        //kosongkan pilihan pd combo box walikelas
        cWali.setSelectedItem(null);
    }
    
    
    //membuat method untuk menampilkan semua data kelas ke dalam tabel
    void load_tabel_kelas(){
        // buat model tabel baru untuk menyimpan data yang akan ditampilkan
        DefaultTableModel model = new DefaultTableModel();
        
        //tambahkan nama nama kolom ke model tabel
        model.addColumn("Kode Kelas");
        model.addColumn("Nama Kelas");
        model.addColumn("Tingkatan");
        model.addColumn("Jurusan");
        model.addColumn("Wali Kelas");
        
        //buat query SQL untuk mengambil data kelas beerta jurusan dan wali kelas
        String sql = "SELECT k.id_kelas, k.nama_kelas, k.tingkatan, j.nama_jur, g.nama_guru "
                + "FROM kelas k "
                + "LEFT JOIN jurusan j ON k.kode_jur = j.kode_jur "
                + "LEFT JOIN guru g ON k.nip_wali_kelas = g.nip;";
     
        try {
            // buka koneksi ke database
            Connection conn = Koneksi.konek();

            // buat statement untuk menjalankan query
            Statement st = conn.createStatement();

            // jalankan query dan simpan hasilnya
            ResultSet rs = st.executeQuery(sql);

            // baca setiap data hasil query
            while (rs.next()) {

                // ambil data dari setiap kolom
                String kodeKelas = rs.getString("id_kelas");
                String namaKelas = rs.getString("nama_kelas");
                String tingkatan = rs.getString("tingkatan");
                String jurusan = rs.getString("nama_jur");
                String waliKelas = rs.getString("nama_guru");

                // simpan data ke dalam array
                Object[] baris = {kodeKelas, namaKelas, tingkatan, jurusan, waliKelas};

                // tambahkan data ke model tabel
                model.addRow(baris);
            }

        } catch (SQLException sqlException) {

            // tampilkan pesan jika terjadi error
            JOptionPane.showMessageDialog(null, "gagal mengambil data");
        }

        // tampilkan data pada jtable
        tblKelas.setModel(model);

    }
    
    
    //method utk menisi c box jurusan dari db
    void comboJurusan(){
        
        try {
            //query utk mengambil semua data dari tabel jursan
            String sql = "SELECT * FROM jurusan";

            //buka koneksi ke database
            Connection conn = Koneksi.konek();

            //siapkan perintah sql
            Statement statement = conn.createStatement();

            //jlankn query dan ambil hasilnya
            ResultSet resultSet = statement.executeQuery(sql);

            //tambahkan setiap nama juruan ke dalam c box
            while (resultSet.next()) {
                cJurusan.addItem(resultSet.getString("nama_jur"));
            }
        } catch (SQLException e) {
            // kosongkan (tidak ada penanganan kesalahan)
        }
        
        //kosongkan pilihan default combobox
        cJurusan.setSelectedItem(null);
    }
    
    
    //method utk menisi c box wali kelas dari db
    void comboWali(){
        
        try {
            //query utk mengambil semua data dari tabel guru
            String sql = "SELECT * FROM guru";

            //buka koneksi ke database
            Connection conn = Koneksi.konek();

            //siapkan peritah sql
            Statement statement = conn.createStatement();

            //jlankn query dan ambil hasilnya
            ResultSet resultSet = statement.executeQuery(sql);

            //tambahkan setiap nama juruan ke dalam c box
            while (resultSet.next()) {
                cWali.addItem(resultSet.getString("nama_guru"));
            }
        } catch (SQLException e) {
            // kosongkan (tidak ada penanganan kesalahan)
        }
        
        //kosongkan pilihan default combobox
        cWali.setSelectedItem(null);
    }
    
    // Method untuk mengambil kode jurusan berdasarkan nama jurusan
    String KodeJurusan(String NamaJurusan) {
        try {
            // Query dengan parameter untuk mencari jurusan berdasarkan nama
            String sql = "SELECT * FROM jurusan WHERE nama_jur = ?";

            // Buka koneksi ke database
            Connection conn = Koneksi.konek();

            // Siapkan prepared statement
            PreparedStatement ps = conn.prepareStatement(sql);

            // Isi parameter query dengan nama jurusan
            ps.setString(1, NamaJurusan);

            // Jalankan query dan ambil hasilnya
            ResultSet resultSet = ps.executeQuery();

            // Jika data ditemukan, kembalikan kode jurusan
            while (resultSet.next()) {
                return resultSet.getString("kode_jur");
            }
        } catch (SQLException e) {
            // Jika error, kembalikan string kosong
            return "";
        }

        // Jika tidak ditemukan, kembalikan string kosong
        return "";
    }
    
    // Method untuk mengambil NIP berdasarkan nama guru
    String NIP(String NamaGuru) {
        try {
            // Query dengan parameter untuk mencari guru berdasarkan nama
            String sql = "SELECT * FROM guru WHERE nama_guru = ?";

            // Buka koneksi ke database
            Connection conn = Koneksi.konek();

            // Siapkan prepared statement
            PreparedStatement ps = conn.prepareStatement(sql);

            // Isi parameter query dengan nama guru
            ps.setString(1, NamaGuru);

            // Jalankan query dan ambil hasilnya
            ResultSet resultSet = ps.executeQuery();

            // Jika data ditemukan, kembalikan NIP guru
            while (resultSet.next()) {
                return resultSet.getString("nip");
            }
        } catch (SQLException e) {
            // Jika error, kembalikan string kosong
            return "";
        }

        // Jika tidak ditemukan, kembalikan string kosong
        return "";
    }
    
    

    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblClose = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tKodeKel = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        tNamaKel = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cTingkatan = new javax.swing.JComboBox<>();
        jPanel13 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cJurusan = new javax.swing.JComboBox<>();
        jPanel14 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cWali = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnDelete = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKelas = new javax.swing.JTable();

        setBackground(new java.awt.Color(153, 220, 255));
        setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 2, new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(24, 24, 24, 24)));
        setMinimumSize(new java.awt.Dimension(930, 590));
        setPreferredSize(new java.awt.Dimension(930, 590));
        setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(219, 243, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel10.setBackground(new java.awt.Color(101, 202, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jPanel10.setPreferredSize(new java.awt.Dimension(100, 60));
        jPanel10.setLayout(new java.awt.GridLayout(1, 0));

        jLabel1.setFont(new java.awt.Font("Plus Jakarta Sans ExtraBold", 1, 18)); // NOI18N
        jLabel1.setText("Data Kelas");
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 24, 0, 0));
        jPanel10.add(jLabel1);

        lblClose.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/alumni202557201029/img/Close Window.png"))); // NOI18N
        lblClose.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 24));
        lblClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCloseMouseClicked(evt);
            }
        });
        jPanel10.add(lblClose);

        jPanel1.add(jPanel10, java.awt.BorderLayout.PAGE_START);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(219, 243, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(22, 24, 24, 24));
        jPanel6.setPreferredSize(new java.awt.Dimension(320, 606));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel8.setBackground(new java.awt.Color(219, 243, 255));
        jPanel8.setPreferredSize(new java.awt.Dimension(102, 410));
        jPanel8.setLayout(new java.awt.GridLayout(5, 1, 0, 18));

        jPanel9.setBackground(new java.awt.Color(219, 243, 255));
        jPanel9.setLayout(new java.awt.BorderLayout(0, 5));

        jLabel2.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 18)); // NOI18N
        jLabel2.setText(" Kode Kelas");
        jPanel9.add(jLabel2, java.awt.BorderLayout.PAGE_START);

        tKodeKel.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        tKodeKel.setText("jTextField1");
        tKodeKel.addActionListener(this::tKodeKelActionPerformed);
        jPanel9.add(tKodeKel, java.awt.BorderLayout.CENTER);

        jPanel8.add(jPanel9);

        jPanel11.setBackground(new java.awt.Color(219, 243, 255));
        jPanel11.setLayout(new java.awt.BorderLayout(0, 5));

        jLabel3.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 18)); // NOI18N
        jLabel3.setText(" Nama Kelas");
        jPanel11.add(jLabel3, java.awt.BorderLayout.PAGE_START);

        tNamaKel.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        tNamaKel.setText("jTextField1");
        tNamaKel.addActionListener(this::tNamaKelActionPerformed);
        jPanel11.add(tNamaKel, java.awt.BorderLayout.CENTER);

        jPanel8.add(jPanel11);

        jPanel12.setBackground(new java.awt.Color(219, 243, 255));
        jPanel12.setLayout(new java.awt.BorderLayout(0, 5));

        jLabel4.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 18)); // NOI18N
        jLabel4.setText(" Tingkatan");
        jPanel12.add(jLabel4, java.awt.BorderLayout.PAGE_START);

        cTingkatan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel12.add(cTingkatan, java.awt.BorderLayout.CENTER);

        jPanel8.add(jPanel12);

        jPanel13.setBackground(new java.awt.Color(219, 243, 255));
        jPanel13.setLayout(new java.awt.BorderLayout(0, 5));

        jLabel5.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 18)); // NOI18N
        jLabel5.setText("Jurusan");
        jPanel13.add(jLabel5, java.awt.BorderLayout.PAGE_START);

        cJurusan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel13.add(cJurusan, java.awt.BorderLayout.CENTER);

        jPanel8.add(jPanel13);

        jPanel14.setBackground(new java.awt.Color(219, 243, 255));
        jPanel14.setLayout(new java.awt.BorderLayout(0, 5));

        jLabel6.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 18)); // NOI18N
        jLabel6.setText(" Wali Kelas");
        jPanel14.add(jLabel6, java.awt.BorderLayout.PAGE_START);

        cWali.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel14.add(cWali, java.awt.BorderLayout.CENTER);

        jPanel8.add(jPanel14);

        jPanel6.add(jPanel8, java.awt.BorderLayout.PAGE_START);

        jPanel2.add(jPanel6, java.awt.BorderLayout.LINE_START);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(219, 243, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 24, 24));
        jPanel4.setPreferredSize(new java.awt.Dimension(576, 75));
        jPanel4.setLayout(new java.awt.GridLayout(1, 4, 12, 0));

        btnDelete.setBackground(new java.awt.Color(231, 35, 35));
        btnDelete.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/alumni202557201029/img/hapus.png"))); // NOI18N
        btnDelete.setText("DELETE");
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.setIconTextGap(10);
        btnDelete.addActionListener(this::btnDeleteActionPerformed);
        jPanel4.add(btnDelete);

        btnEdit.setBackground(new java.awt.Color(245, 140, 38));
        btnEdit.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/alumni202557201029/img/edit.png"))); // NOI18N
        btnEdit.setText("EDIT");
        btnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEdit.setIconTextGap(10);
        btnEdit.addActionListener(this::btnEditActionPerformed);
        jPanel4.add(btnEdit);

        btnTambah.setBackground(new java.awt.Color(82, 201, 93));
        btnTambah.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        btnTambah.setForeground(new java.awt.Color(255, 255, 255));
        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/alumni202557201029/img/tambah.png"))); // NOI18N
        btnTambah.setText("TAMBAH");
        btnTambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTambah.setIconTextGap(10);
        btnTambah.addActionListener(this::btnTambahActionPerformed);
        jPanel4.add(btnTambah);

        btnReset.setBackground(new java.awt.Color(31, 123, 246));
        btnReset.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/alumni202557201029/img/reset.png"))); // NOI18N
        btnReset.setText("RESET");
        btnReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReset.setIconTextGap(10);
        btnReset.addActionListener(this::btnResetActionPerformed);
        jPanel4.add(btnReset);

        jPanel5.add(jPanel4, java.awt.BorderLayout.PAGE_END);

        jPanel7.setBackground(new java.awt.Color(219, 243, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 0, 24, 24));
        jPanel7.setLayout(new java.awt.CardLayout());

        tblKelas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblKelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKelasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKelas);

        jPanel7.add(jScrollPane1, "card2");

        jPanel5.add(jPanel7, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        add(jPanel1, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void tKodeKelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tKodeKelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tKodeKelActionPerformed

    private void tNamaKelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tNamaKelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tNamaKelActionPerformed

    private void lblCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_lblCloseMouseClicked

    private void tblKelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKelasMouseClicked
        // TODO add your handling code here:

        // Ambil baris yang diklik oleh pengguna pada tabel kelas
        int barisYangDipilih = tblKelas.rowAtPoint(evt.getPoint());

        // Ambil nilai dari kolom ke-0 (kode kelas) pada baris yang dipilih
        String KodeKelas = tblKelas.getValueAt(barisYangDipilih, 0).toString();

        // Ambil nilai dari kolom ke-1 (nama kelas)
        String NamaKelas = tblKelas.getValueAt(barisYangDipilih, 1).toString();

        // Ambil nilai dari kolom ke-2 (tingkatan kelas)
        String Tingkatan = tblKelas.getValueAt(barisYangDipilih, 2).toString();

        // Ambil nilai dari kolom ke-3 (nama jurusan)
        String Jurusan = tblKelas.getValueAt(barisYangDipilih, 3).toString();

        // Buat variabel untuk menyimpan nama wali kelas
        String WaliKelas;

        // Cek apakah kolom ke-4 (wali kelas) berisi data atau tidak
        if (tblKelas.getValueAt(barisYangDipilih, 4) != null) {
            // Jika ada data, ambil dan ubah menjadi string
            WaliKelas = tblKelas.getValueAt(barisYangDipilih, 4).toString();
        } else {
            // Jika kosong (null), set nilai WaliKelas juga null
            WaliKelas = null;
        }

        // Tampilkan kode kelas ke dalam input text (tidak bisa diedit)
        tKodeKel.setText(KodeKelas);
        tKodeKel.setEditable(false);

        // Tampilkan nama kelas ke text field
        tNamaKel.setText(NamaKelas);

        // Tampilkan tingkatan ke combo box tingkatan
        cTingkatan.setSelectedItem(Tingkatan);

        // Tampilkan jurusan ke combo box jurusan
        cJurusan.setSelectedItem(Jurusan);

        // Tampilkan nama wali kelas ke combo box wali
        cWali.setSelectedItem(WaliKelas);
    }//GEN-LAST:event_tblKelasMouseClicked

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        // Ambil input dari field kode kelas
        String KodeKelas = tKodeKel.getText();

        // Ambil input dari field nama kelas
        String NamaKelas = tNamaKel.getText();

        // Ambil nilai yang dipilih dari combo box tingkatan
        String Tingkatan = cTingkatan.getSelectedItem().toString();

        // Ambil nama jurusan dari combo box lalu ubah ke kode jurusan menggunakan method KodeJurusan()
        String Jurusan = KodeJurusan(cJurusan.getSelectedItem().toString());

        // Ambil nama wali kelas dari combo box lalu ubah ke NIP menggunakan method NIP()
        String WaliKelas = NIP(cWali.getSelectedItem().toString());

        try {
            // Query SQL untuk menyimpan data ke tabel kelas
            String sql = "INSERT INTO kelas(id_kelas,nama_kelas,tingkatan,kode_jur,nip_wali_kelas) VALUES(?,?,?,?,?)";

            // Buka koneksi ke database
            Connection conn = Koneksi.konek();

            // Siapkan statement SQL dengan parameter
            PreparedStatement statement = conn.prepareStatement(sql);

            // Masukkan data ke parameter query (urutan sesuai dengan tanda tanya di query)
            statement.setString(1, KodeKelas);
            statement.setString(2, NamaKelas);
            statement.setString(3, Tingkatan);
            statement.setString(4, Jurusan);
            statement.setString(5, WaliKelas);

            // Jalankan query untuk menyimpan data ke database
            statement.execute();

            // Tampilkan pesan bahwa data berhasil disimpan
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");

        } catch (SQLException e) {
            // Jika terjadi error saat menyimpan, tampilkan pesan gagal
            JOptionPane.showMessageDialog(null, "Data gagal disimpan!");
        }

        // Tampilkan kembali data kelas terbaru di tabel
        load_tabel_kelas();

        // Kosongkan semua input di form
        reset();
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        
        
        // Ambil nilai kode kelas dari input field
        String KodeKelas = tKodeKel.getText();

        // Ambil nilai nama kelas dari input field
        String NamaKelas = tNamaKel.getText();

        // Ambil pilihan tingkatan dari combo box dan ubah menjadi String
        String Tingkatan = cTingkatan.getSelectedItem().toString();

        // Ambil nama jurusan dari combo box, lalu konversi ke kode jurusan lewat method KodeJurusan()
        String Jurusan = KodeJurusan(cJurusan.getSelectedItem().toString());

        // Ambil nama wali kelas dari combo box, lalu konversi ke NIP guru lewat method NIP()
        String WaliKelas = NIP(cWali.getSelectedItem().toString());

        try {
            // Buat query SQL untuk mengubah data kelas berdasarkan id_kelas
            String sql = "UPDATE kelas SET nama_kelas=?, tingkatan=?, kode_jur=?, nip_wali_kelas=? WHERE id_kelas=?";

            // Buka koneksi ke database
            Connection conn = Koneksi.konek();

            // Siapkan perintah SQL yang akan diisi nilai-nilainya
            PreparedStatement statement = conn.prepareStatement(sql);

            // Isi nilai-nilai parameter (tanda tanya di query) sesuai urutannya
            statement.setString(1, NamaKelas);
            statement.setString(2, Tingkatan);
            statement.setString(3, Jurusan);
            statement.setString(4, WaliKelas);
            statement.setString(5, KodeKelas);

            // Jalankan query untuk melakukan update data
            statement.execute();

            // Tampilkan pesan bahwa data berhasil diubah
            JOptionPane.showMessageDialog(null, "Data berhasil diubah!");

        } catch (SQLException e) {
            // Jika terjadi kesalahan saat update, tampilkan pesan gagal
            JOptionPane.showMessageDialog(null, "Data gagal diubah!");
        }

        // Setelah data diubah, tampilkan ulang data ke dalam tabel
        load_tabel_kelas();

        // Kosongkan inputan agar siap diisi data baru
        reset();

    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        
        // Ambil nilai kode kelas dari inputan field tKodeKelas
        String KodeKelas = tKodeKel.getText();

        try {
            // Buat perintah SQL untuk menghapus data dari tabel 'kelas' berdasarkan id_kelas
            String sql = "DELETE FROM kelas WHERE id_kelas=?";

            // Buka koneksi ke database
            Connection conn = Koneksi.konek();

            // Siapkan pernyataan SQL yang mendukung parameter (?)
            PreparedStatement statement = conn.prepareStatement(sql);

            // Isi nilai parameter pertama (id_kelas) dengan KodeKelas yang diinput
            statement.setString(1, KodeKelas);

            // Jalankan perintah untuk menghapus data dari database
            statement.execute();

            // Tampilkan pesan bahwa data berhasil dihapus
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");

        } catch (SQLException e) {
            // Tampilkan pesan jika terjadi kesalahan saat menghapus data
            JOptionPane.showMessageDialog(null, "Data gagal dihapus!");
        }

        // Tampilkan ulang data ke tabel agar data terbaru muncul
        load_tabel_kelas();

        // Kosongkan semua inputan pada form
        reset();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_btnResetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnTambah;
    private javax.swing.JComboBox<String> cJurusan;
    private javax.swing.JComboBox<String> cTingkatan;
    private javax.swing.JComboBox<String> cWali;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblClose;
    private javax.swing.JTextField tKodeKel;
    private javax.swing.JTextField tNamaKel;
    private javax.swing.JTable tblKelas;
    // End of variables declaration//GEN-END:variables
}
