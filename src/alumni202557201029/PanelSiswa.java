/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package alumni202557201029;

import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author Al
 */
public class PanelSiswa extends javax.swing.JPanel {

    /**
     * Creates new form PanelDashboard
     */
    public PanelSiswa() {
        initComponents();
        
        //memanggil method reset untuk mengosongkan semua input pada form
        reset();
        
        //memanggil method untuk menampilkan data siswa ke dalam tabel
        load_tabel_siswa();
        
        //memanggil method utk mengisi c box kelas dari db
        comboKelas();
    }
    
    
    // Method untuk mengosongkan semua input pada form siswa
    void reset() {

        // Mengosongkan field NIS
        tNis.setText(null);
        
        tNis.setEditable(true);

        // Mengosongkan field Nama Siswa
        tNamaSiswa.setText(null);

        // Mengosongkan pilihan pada combo box Jenis Kelamin
        cGender.setSelectedItem(null);

        // Mengosongkan field Tempat Lahir
        tTempatLahir.setText(null);

        // Mengosongkan pilihan pada komponen kalender Tanggal Lahir
        tTgl.setCalendar(null);

        // Mengosongkan field Nomor HP
        tNoHp.setText(null);

        // Mengosongkan pilihan pada combo box Kelas
        cKelas.setSelectedItem(null);

        // Mengosongkan field Alamat
        tAlamat.setText(null);

        // Menghapus icon pada label foto
        lblFoto.setIcon(null);

        // Mengatur teks label foto menjadi "Foto"
        lblFoto.setText("Foto");

        // Mengosongkan path file foto yang disimpan
        tPathFoto.setText(null);
    }
    
    
    // Method untuk mengisi combo box kelas dari tabel 'kelas' di database
    void comboKelas() {
        try {
            // Query SQL untuk mengambil semua data kelas
            String sql = "SELECT * FROM kelas";

            // Membuka koneksi ke database
            Connection conn = Koneksi.konek();

            // Membuat statement untuk menjalankan query
            Statement statement = conn.createStatement();

            // Menjalankan query dan menyimpan hasilnya
            ResultSet resultSet = statement.executeQuery(sql);

            // Mengambil data satu per satu dan menambahkannya ke combo box
            while (resultSet.next()) {
                cKelas.addItem(resultSet.getString("id_kelas"));
            }
        } catch (SQLException e) {
            // Jika terjadi kesalahan, tampilkan pesan error
        }

        // Mengosongkan pilihan combo box setelah diisi
        cKelas.setSelectedItem(null);
    }
    
    
    //membuar method load tabel siswa
    void load_tabel_siswa(){
        
        //membuat model tabel baru
        DefaultTableModel  model = new DefaultTableModel();
        
        //menambahkan kolom ke dalam model tabel
        model.addColumn("NIS");
        model.addColumn("Nama Siswa");
        model.addColumn("L/P");
        model.addColumn("Tempat Lahir");
        model.addColumn("Tgl Lahir");
        model.addColumn("Kelas");
        model.addColumn("HP");
        
        //query SQL utk mengambil semua data siswa
        String sql = "SELECT * FROM siswa";
        
        try {
            //membuka koneksi ke database
            Connection conn = Koneksi.konek();

            //membuat statement SQL
            Statement st = conn.createStatement();

            //menjalankan query dan mengambil hasilnya
            ResultSet rs = st.executeQuery(sql);

            //melakukan iterasi utk setiap baris asil query
            while (rs.next()) {

                //mengambil data dari setiap kolom
                String nis = rs.getString("nis");
                String namaSiswa = rs.getString("nama_siswa");
                String jenisKelamin = rs.getString("gender");
                String tempatLahir = rs.getString("tempat_lahir");
                String tglLahir = rs.getString("tgl_lahir");
                String kelas = rs.getString("id_kelas");
                String hp = rs.getString("no_hp");
                
                //menyimpan data kedalam array
                Object[] baris = {nis, namaSiswa, jenisKelamin, tempatLahir, tglLahir, kelas, hp};
                
                //menambhakan data sbg baris baru di model tabel
                model.addRow(baris);
            }
        } catch (SQLException sQLException) {
            
            //menampilkan pesan error jika terjadi kegagalan saat mengambil data
            JOptionPane.showMessageDialog(null, "gagal mengambil data!");
        }
        
        //menampilkan model yang sudah diisi ke dalam tabel GUI
        tblSiswa.setModel(model);
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
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        btnHapus = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        lblFoto = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        tNis = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tNamaSiswa = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cGender = new javax.swing.JComboBox<>();
        jPanel14 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        tTempatLahir = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        tTgl = new com.toedter.calendar.JDateChooser();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        tNoHp = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        cKelas = new javax.swing.JComboBox<>();
        jPanel20 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tAlamat = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSiswa = new javax.swing.JTable();
        tPathFoto = new javax.swing.JLabel();

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
        jLabel1.setText("Data Siswa");
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

        jPanel3.setBackground(new java.awt.Color(219, 243, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 24, 0, 24));
        jPanel3.setPreferredSize(new java.awt.Dimension(100, 350));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(219, 243, 255));
        jPanel5.setMinimumSize(new java.awt.Dimension(200, 80));
        jPanel5.setPreferredSize(new java.awt.Dimension(200, 70));
        jPanel5.setLayout(new java.awt.BorderLayout(0, 10));

        jPanel7.setOpaque(false);
        jPanel7.setPreferredSize(new java.awt.Dimension(504, 45));
        jPanel7.setLayout(new java.awt.GridLayout(1, 4, 12, 0));

        btnHapus.setBackground(new java.awt.Color(231, 35, 35));
        btnHapus.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        btnHapus.setForeground(new java.awt.Color(255, 255, 255));
        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/alumni202557201029/img/hapus.png"))); // NOI18N
        btnHapus.setText("DELETE");
        btnHapus.setIconTextGap(10);
        btnHapus.addActionListener(this::btnHapusActionPerformed);
        jPanel7.add(btnHapus);

        btnEdit.setBackground(new java.awt.Color(245, 140, 38));
        btnEdit.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/alumni202557201029/img/edit.png"))); // NOI18N
        btnEdit.setText("EDIT");
        btnEdit.setIconTextGap(10);
        btnEdit.addActionListener(this::btnEditActionPerformed);
        jPanel7.add(btnEdit);

        btnTambah.setBackground(new java.awt.Color(82, 201, 93));
        btnTambah.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        btnTambah.setForeground(new java.awt.Color(255, 255, 255));
        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/alumni202557201029/img/tambah.png"))); // NOI18N
        btnTambah.setText("TAMBAH");
        btnTambah.setIconTextGap(10);
        btnTambah.addActionListener(this::btnTambahActionPerformed);
        jPanel7.add(btnTambah);

        btnReset.setBackground(new java.awt.Color(31, 123, 246));
        btnReset.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/alumni202557201029/img/reset.png"))); // NOI18N
        btnReset.setText("RESET");
        btnReset.setIconTextGap(10);
        btnReset.addActionListener(this::btnResetActionPerformed);
        jPanel7.add(btnReset);

        jPanel5.add(jPanel7, java.awt.BorderLayout.PAGE_END);

        jPanel18.setBackground(new java.awt.Color(219, 243, 255));
        jPanel18.setLayout(new java.awt.BorderLayout(24, 15));

        lblFoto.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 18)); // NOI18N
        lblFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFoto.setText("Foto");
        lblFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblFoto.setPreferredSize(new java.awt.Dimension(210, 25));
        lblFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFotoMouseClicked(evt);
            }
        });
        jPanel18.add(lblFoto, java.awt.BorderLayout.LINE_START);

        jPanel8.setBackground(new java.awt.Color(219, 243, 255));
        jPanel8.setOpaque(false);
        jPanel8.setLayout(new java.awt.GridLayout(1, 0, 24, 0));

        jPanel6.setOpaque(false);
        jPanel6.setLayout(new java.awt.GridLayout(5, 1, 0, 3));

        jPanel9.setBackground(new java.awt.Color(219, 243, 255));
        jPanel9.setLayout(new java.awt.BorderLayout(0, 3));

        jLabel3.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        jLabel3.setText(" Nis");
        jPanel9.add(jLabel3, java.awt.BorderLayout.PAGE_START);

        tNis.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        tNis.addActionListener(this::tNisActionPerformed);
        jPanel9.add(tNis, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel9);

        jPanel11.setBackground(new java.awt.Color(219, 243, 255));
        jPanel11.setLayout(new java.awt.BorderLayout(0, 3));

        jLabel4.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        jLabel4.setText(" Nama");
        jPanel11.add(jLabel4, java.awt.BorderLayout.PAGE_START);

        tNamaSiswa.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        tNamaSiswa.addActionListener(this::tNamaSiswaActionPerformed);
        jPanel11.add(tNamaSiswa, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel11);

        jPanel13.setBackground(new java.awt.Color(219, 243, 255));
        jPanel13.setLayout(new java.awt.BorderLayout(0, 3));

        jLabel5.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        jLabel5.setText(" Jenis Kelamin");
        jPanel13.add(jLabel5, java.awt.BorderLayout.PAGE_START);

        cGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki - laki", "Perempuan" }));
        jPanel13.add(cGender, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel13);

        jPanel14.setBackground(new java.awt.Color(219, 243, 255));
        jPanel14.setLayout(new java.awt.BorderLayout(0, 3));

        jLabel6.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        jLabel6.setText(" Tempat Lahir");
        jPanel14.add(jLabel6, java.awt.BorderLayout.PAGE_START);

        tTempatLahir.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        tTempatLahir.addActionListener(this::tTempatLahirActionPerformed);
        jPanel14.add(tTempatLahir, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel14);

        jPanel15.setBackground(new java.awt.Color(219, 243, 255));
        jPanel15.setLayout(new java.awt.BorderLayout(0, 3));

        jLabel7.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        jLabel7.setText(" Tanggal Lahir");
        jPanel15.add(jLabel7, java.awt.BorderLayout.PAGE_START);

        tTgl.setDateFormatString("yyyy-MM-dd");
        tTgl.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        jPanel15.add(tTgl, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel15);

        jPanel8.add(jPanel6);

        jPanel16.setOpaque(false);

        jPanel17.setBackground(new java.awt.Color(219, 243, 255));
        jPanel17.setLayout(new java.awt.BorderLayout(0, 5));

        jLabel8.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        jLabel8.setText(" Hp");
        jPanel17.add(jLabel8, java.awt.BorderLayout.PAGE_START);

        tNoHp.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        tNoHp.addActionListener(this::tNoHpActionPerformed);
        jPanel17.add(tNoHp, java.awt.BorderLayout.CENTER);

        jPanel19.setBackground(new java.awt.Color(219, 243, 255));
        jPanel19.setLayout(new java.awt.BorderLayout(0, 5));

        jLabel10.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        jLabel10.setText(" Kelas");
        jPanel19.add(jLabel10, java.awt.BorderLayout.PAGE_START);

        cKelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cKelasMouseClicked(evt);
            }
        });
        jPanel19.add(cKelas, java.awt.BorderLayout.CENTER);

        jPanel20.setBackground(new java.awt.Color(219, 243, 255));
        jPanel20.setLayout(new java.awt.BorderLayout(0, 5));

        jLabel11.setFont(new java.awt.Font("Plus Jakarta Sans SemiBold", 0, 12)); // NOI18N
        jLabel11.setText(" Alamat");
        jPanel20.add(jLabel11, java.awt.BorderLayout.PAGE_START);

        tAlamat.setColumns(20);
        tAlamat.setRows(5);
        jScrollPane2.setViewportView(tAlamat);

        jPanel20.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
        );

        jPanel8.add(jPanel16);

        jPanel18.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel18, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel4.setBackground(new java.awt.Color(219, 243, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 24, 24, 24));
        jPanel4.setPreferredSize(new java.awt.Dimension(500, 430));
        jPanel4.setLayout(new java.awt.BorderLayout(0, 10));

        tblSiswa.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSiswaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSiswa);

        jPanel4.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        tPathFoto.setFont(new java.awt.Font("Plus Jakarta Sans", 0, 14)); // NOI18N
        tPathFoto.setText("jLabel2");
        jPanel4.add(tPathFoto, java.awt.BorderLayout.PAGE_END);

        jPanel2.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        add(jPanel1, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void lblCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_lblCloseMouseClicked

    private void tNoHpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tNoHpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tNoHpActionPerformed

    private void tTempatLahirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tTempatLahirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tTempatLahirActionPerformed

    private void tNamaSiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tNamaSiswaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tNamaSiswaActionPerformed

    private void tNisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tNisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tNisActionPerformed

    private void tblSiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSiswaMouseClicked
        // TODO add your handling code here:
        // Mengambil indeks baris yang diklik pada tabel siswa
        int baris = tblSiswa.rowAtPoint(evt.getPoint());

        // Mengambil nilai dari kolom pertama (NIS) pada baris yang diklik dan mengubah ke String
        String nis = tblSiswa.getValueAt(baris, 0).toString();

        // Mengambil nilai dari kolom kedua (Nama Siswa)
        String namaSiswa = tblSiswa.getValueAt(baris, 1).toString();

        // Mengambil objek dari kolom ketiga (Jenis Kelamin)
        Object jkObj = tblSiswa.getValueAt(baris, 2);

        // Mengambil objek dari kolom keempat (Tempat Lahir)
        Object tempatObj = tblSiswa.getValueAt(baris, 3);

        // Mengambil objek dari kolom kelima (Tanggal Lahir)
        Object tglObj = tblSiswa.getValueAt(baris, 4);

        // Mengambil objek dari kolom keenam (Kelas)
        Object kelasObj = tblSiswa.getValueAt(baris, 5);

        // Mengambil objek dari kolom ketujuh (Nomor HP)
        Object hpObj = tblSiswa.getValueAt(baris, 6);
        

        // Menampilkan nilai NIS pada field input dan membuatnya tidak bisa diubah
        tNis.setText(nis);
        tNis.setEditable(false);

        // Menampilkan nama siswa ke field input
        tNamaSiswa.setText(namaSiswa);

        // Mengonversi objek menjadi string, jika null maka hasilnya null atau string kosong
        String jenisKelamin = (jkObj != null) ? jkObj.toString() : "";
        String tempatLahir = (tempatObj != null) ? tempatObj.toString() : "";
        String tglLahir = (tglObj != null) ? tglObj.toString() : null;
        String idKelas = (kelasObj != null) ? kelasObj.toString() : null;
        String noHP = (hpObj != null) ? hpObj.toString() : "";

        // Menampilkan tempat lahir, no HP, dan memilih kelas sesuai data
        tTempatLahir.setText(tempatLahir);
        tNoHp.setText(noHP);
        cKelas.setSelectedItem(idKelas);

        // Jika tanggal lahir tidak null dan tidak kosong, ubah ke format Date dan tampilkan di komponen kalender
        if (tglLahir != null && !tglLahir.isEmpty()) {
            try {
                tTgl.setDate(java.sql.Date.valueOf(tglLahir));
            } catch (IllegalArgumentException e) {
                // Jika gagal parsing tanggal, kosongkan field tanggal
                tTgl.setDate(null);
            }
        } else {
            tTgl.setDate(null);
        }

        // Konversi kode jenis kelamin ke bentuk tampilan yang dipahami pengguna
        switch (jenisKelamin) {
            case "L":
                cGender.setSelectedItem("Laki - laki");
                break;
            case "P":
                cGender.setSelectedItem("Perempuan");
                break;
            default:
                cGender.setSelectedItem(null);
                break;
        }

        try {
            // Query untuk mengambil data alamat dan foto berdasarkan NIS
            String sql = "SELECT alamat, foto FROM siswa WHERE nis = ?";

            // Membuka koneksi ke database
            Connection conn = Koneksi.konek();

            // Menyiapkan statement SQL dengan parameter
            PreparedStatement ps = conn.prepareStatement(sql);

            // Mengisi parameter dengan NIS
            ps.setString(1, nis);

            // Menjalankan query dan menyimpan hasilnya
            ResultSet rs = ps.executeQuery();

            // Jika data ditemukan
            if (rs.next()) {
                // Mengambil alamat dan foto dari hasil query
                String alamat = rs.getString("alamat");
                String foto = rs.getString("foto");

                // Menampilkan alamat ke field input
                tAlamat.setText(alamat);

                // Jika path foto tidak kosong, tampilkan gambar ke label foto
                if (foto != null && !foto.isEmpty()) {
                    ImageIcon icon = new ImageIcon(foto);
                    Image image = icon.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_SMOOTH);
                    tPathFoto.setText(foto);
                    lblFoto.setText(null);
                    lblFoto.setIcon(new ImageIcon(image));
                } else {
                    // Jika tidak ada foto, set teks "Foto" dan hapus icon
                    lblFoto.setText("Foto");
                    lblFoto.setIcon(null);
                }
            }
        } catch (SQLException e) {
            // Menampilkan error ke konsol jika terjadi kesalahan SQL
            System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_tblSiswaMouseClicked

    private void lblFotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFotoMouseClicked
        // TODO add your handling code here:
        
        
        
        // Blok try digunakan untuk menangani kemungkinan error saat memilih dan memuat file gambar
        try {
            

            // Membuat objek JFileChooser untuk membuka dialog pemilihan file
            JFileChooser chooser = new JFileChooser();

            // Menampilkan dialog dan menyimpan hasil aksi pengguna (OK atau Cancel)
            int result = chooser.showOpenDialog(null);

            // Mengecek apakah pengguna menekan tombol "Open" (OK)
            if (result == JFileChooser.APPROVE_OPTION) {

                // Mengambil file yang dipilih oleh pengguna
                File file = chooser.getSelectedFile();

                // Mengecek apakah file yang dipilih tidak null
                if (file != null) {

                    // Membuat objek ImageIcon dari file gambar yang dipilih
                    ImageIcon icon = new ImageIcon(file.toString());

                    // Mengubah ukuran gambar agar sesuai dengan label tFoto
                    Image image = icon.getImage().getScaledInstance(
                            lblFoto.getWidth(),
                            lblFoto.getHeight(),
                            Image.SCALE_SMOOTH
                    );

                    // Membuat ImageIcon baru dari gambar yang telah diubah ukurannya
                    ImageIcon ic = new ImageIcon(image);

                    // Menghapus teks pada label foto
                    lblFoto.setText(null);

                    // Menampilkan gambar (icon) ke dalam label tFoto
                    lblFoto.setIcon(ic);

                    // Mengambil path absolut dari file gambar dan menyimpannya ke field tFotoPath
                    String filename = file.getAbsolutePath();
                    tPathFoto.setText(filename);
                }

            } else {

                // Jika pengguna menekan tombol Cancel, tampilkan pesan ke konsol
                System.out.println("Pemilihan file dibatalkan oleh pengguna.");
            }

        } catch (HeadlessException e) {

            // Menangani error jika terjadi kesalahan saat memilih atau memuat file gambar
            JOptionPane.showMessageDialog(null, "Error Upload: " + e.getMessage());
        }
    }//GEN-LAST:event_lblFotoMouseClicked

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        // Mengambil teks dari field NIS
        String nis = tNis.getText();

        // Mengambil teks dari field Nama Siswa
        String namaSiswa = tNamaSiswa.getText();

        // Mengambil item yang dipilih dari combo box jenis kelamin dan mengubahnya menjadi string
        String jenisKelamin = cGender.getSelectedItem().toString();

        // Variabel untuk menyimpan hasil konversi jenis kelamin (L/P)
        String jK = null;

        // Mengambil teks dari field Tempat Lahir
        String tempatLahir = tTempatLahir.getText();

        // Mengambil tanggal dari komponen kalender
        Date tglLahirDate = tTgl.getDate();

        // Mengubah tanggal lahir menjadi format "yyyy-MM-dd"
        String tglLahir = new SimpleDateFormat("yyyy-MM-dd").format(tglLahirDate);

        // Mengambil teks dari field nomor HP
        String hp = tNoHp.getText();

        // Mengambil item yang dipilih dari combo box kelas
        String kelas = cKelas.getSelectedItem().toString();

        // Mengambil teks dari field alamat
        String alamat = tAlamat.getText();

        // Mengambil path file dari label path foto
        Object filePathObj = tPathFoto.getText();
        
        String filePath = (filePathObj != null) ? filePathObj.toString() : "";
        

        // Konversi jenis kelamin dari teks menjadi kode (L atau P)
        switch (jenisKelamin) {
            case "Laki - laki":
                jK = "L";
                break;
            case "Perempuan":
                jK = "P";
                break;
            default:
                jK = null;
                break;
        }

        // Variabel untuk menyimpan path file foto tujuan
        String foto = null;

        // Mengecek apakah ada path file foto yang dipilih
        if (filePath.length() != 0) {
            try {
                // Menyimpan path sumber file
                String sourcePath = filePath;
                File sourceFile = new File(sourcePath);

                // Menentukan folder tujuan untuk menyimpan foto
                String destinationFolderPath = "src/foto/";
                File destinationFolder = new File(destinationFolderPath);

                // Jika folder tujuan belum ada, buat folder tersebut
                if (!destinationFolder.exists()) {
                    destinationFolder.mkdir();
                }

                // Mengambil ekstensi file (contoh: jpg, png, dll)
                String extension = sourcePath.substring(sourcePath.lastIndexOf('.') + 1);

                // Membuat nama file baru yang unik berdasarkan timestamp
                String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                String destinationFileName = "foto-" + timestamp + "." + extension;

                // Membuat file tujuan dengan path dan nama file baru
                File destinationFile = new File(destinationFolderPath + destinationFileName);

                // Menyalin file dari sumber ke tujuan
                Files.copy(sourceFile.toPath(), destinationFile.toPath());

                // Menyimpan path foto yang telah dipindahkan
                foto = destinationFile.getPath();

            } catch (IOException e) {
                // Menampilkan pesan error jika gagal mengupload file
                JOptionPane.showMessageDialog(null, "Gagal upload file: " + e.getMessage());
            }
        } else {
            // Jika tidak ada file yang dipilih, set null
            foto = null;
        }

        try {
            // Query SQL untuk menyimpan data siswa ke database
            String sql = "INSERT INTO siswa(nis,nama_siswa,gender,tempat_lahir,tgl_lahir,alamat,no_hp,id_kelas,foto)"
                    + " VALUES(?,?,?,?,?,?,?,?,?)";

            // Membuka koneksi ke database
            Connection conn = Koneksi.konek();

            // Menyiapkan statement SQL dengan parameter
            PreparedStatement statement = conn.prepareStatement(sql);

            // Mengisi nilai parameter satu per satu
            statement.setString(1, nis);
            statement.setString(2, namaSiswa);
            statement.setString(3, jK);
            statement.setString(4, tempatLahir);
            statement.setString(5, tglLahir);
            statement.setString(6, alamat);
            statement.setString(7, hp);
            statement.setString(8, kelas);
            statement.setString(9, foto);

            // Menjalankan query penyimpanan
            statement.execute();

            // Menampilkan pesan bahwa data berhasil disimpan
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
        } catch (SQLException e) {
            // Menampilkan pesan jika terjadi kesalahan saat menyimpan data
            //JOptionPane.showMessageDialog(null, "Data gagal disimpan!");
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // Memuat ulang data siswa ke tabel
        load_tabel_siswa();

        // Mengosongkan semua input form setelah data disimpan
        reset();
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        // Mengambil NIS dari field input
        String nis = tNis.getText();

        // Mengambil Nama Siswa dari field input
        String namaSiswa = tNamaSiswa.getText();

        // Mengambil nilai dari combo box jenis kelamin dan mengubah menjadi String
        String jenisKelamin = cGender.getSelectedItem().toString();

        // Variabel untuk menyimpan kode jenis kelamin ('L' atau 'P')
        String jK = null;

        // Mengambil Tempat Lahir dari field input
        String tempatLahir = tTempatLahir.getText();

        // Mengambil tanggal lahir dari komponen kalender
        Date tglLahirDate = tTgl.getDate();

        // Mengubah tanggal lahir menjadi format "yyyy-MM-dd"
        String tglLahir = new SimpleDateFormat("yyyy-MM-dd").format(tglLahirDate);

        // Mengambil Nomor HP dari field input
        String hp = tNoHp.getText();

        // Mengambil Kelas dari combo box
        String kelas = cKelas.getSelectedItem().toString();

        // Mengambil Alamat dari field input
        String alamat = tAlamat.getText();

        // Mengambil path file foto dari field input tersembunyi
        String filePath = tPathFoto .getText();

        // Mengonversi pilihan jenis kelamin ke kode (L/P)
        switch (jenisKelamin) {
            case "Laki - laki":
                jK = "L";
                break;
            case "Perempuan":
                jK = "P";
                break;
            default:
                jK = null;
                break;
        }

        // Variabel untuk menyimpan path foto asli yang tersimpan di database
        String fotoAsli = null;

        try {
            // Query untuk mengambil path foto berdasarkan NIS
            String sql = "SELECT foto FROM siswa WHERE nis = ?";
            Connection conn = Koneksi.konek();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nis);
            ResultSet rs = ps.executeQuery();

            // Jika data ditemukan, simpan path foto ke variabel fotoAsli
            if (rs.next()) {
                fotoAsli = rs.getString("foto");
            }
        } catch (SQLException e) {
            // Tampilkan pesan jika gagal mengambil foto dari database
            JOptionPane.showMessageDialog(null, "Gagal mengambil foto asli: " + e.getMessage());
        }

        // Menentukan apakah foto diubah oleh pengguna
        boolean fotoDiubah = (fotoAsli == null && !filePath.isEmpty())
                || (fotoAsli != null && !fotoAsli.equals(filePath));

        // Jika foto diubah, variabel 'foto' akan diisi dengan path baru
        String foto = fotoAsli;

        if (fotoDiubah) {
            try {
                // Ambil file dari path baru
                File sourceFile = new File(filePath);

                // Dapatkan ekstensi file
                String extension = filePath.substring(filePath.lastIndexOf('.') + 1);

                // Buat nama file baru berdasarkan waktu agar unik
                String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                String destinationPath = "src/foto/foto-" + timestamp + "." + extension;

                // Salin file ke folder tujuan
                File destFile = new File(destinationPath);
                Files.copy(sourceFile.toPath(), destFile.toPath());

                // Simpan path tujuan ke variabel 'foto'
                foto = destinationPath;

            } catch (Exception e) {
                // Tampilkan pesan jika gagal upload file
                JOptionPane.showMessageDialog(null, "Gagal upload file: " + e.getMessage());
            }
        }

        try {
            // Query SQL berbeda tergantung apakah foto diubah atau tidak
            String sql2;
            if (fotoDiubah) {
                sql2 = "UPDATE siswa SET nama_siswa=?, gender=?, tempat_lahir=?, "
                        + "tgl_lahir=?, alamat=?, no_hp=?, id_kelas=?, foto=? WHERE nis=?";
            } else {
                sql2 = "UPDATE siswa SET nama_siswa=?, gender=?, tempat_lahir=?, "
                        + "tgl_lahir=?, alamat=?, no_hp=?, id_kelas=? WHERE nis=?";
            }

            // Membuka koneksi ke database
            Connection conn = Koneksi.konek();

            // Menyiapkan statement untuk eksekusi SQL
            PreparedStatement statement = conn.prepareStatement(sql2);

            // Menetapkan parameter umum
            statement.setString(1, namaSiswa);
            statement.setString(2, jK);
            statement.setString(3, tempatLahir);
            statement.setString(4, tglLahir);
            statement.setString(5, alamat);
            statement.setString(6, hp);
            statement.setString(7, kelas);

            // Jika foto diubah, tetapkan parameter tambahan
            if (fotoDiubah) {
                statement.setString(8, foto);
                statement.setString(9, nis);
            } else {
                statement.setString(8, nis);
            }

            // Eksekusi perintah update
            statement.execute();

            // Tampilkan pesan sukses
            JOptionPane.showMessageDialog(null, "Data berhasil diubah!");

        } catch (SQLException e) {
            // Tampilkan pesan jika update gagal
            JOptionPane.showMessageDialog(null, "Gagal memperbarui data: " + e.getMessage());
        }

        // Muat ulang tabel agar perubahan terlihat
        load_tabel_siswa();

        // Kosongkan form setelah proses selesai
        reset();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        // Mengambil nilai NIS dari field input
        String nis = tNis.getText();

        // Menyiapkan perintah SQL untuk menghapus data siswa berdasarkan NIS
        String sql = "DELETE FROM siswa WHERE nis=?";

        try {
            // Membuka koneksi ke database
            Connection conn = Koneksi.konek();

            // Menyiapkan statement SQL untuk dieksekusi
            PreparedStatement statement = conn.prepareStatement(sql);

            // Mengisi parameter pertama (tanda ?) dengan nilai NIS
            statement.setString(1, nis);

            // Menjalankan perintah DELETE
            statement.execute();

            // Menampilkan pesan bahwa data berhasil dihapus
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");

        } catch (SQLException e) {
            // Menampilkan pesan jika terjadi kesalahan saat menghapus
            JOptionPane.showMessageDialog(null, "Gagal menghapus data: " + e.getMessage());
        }

        // Memuat ulang data tabel agar tampilan diperbarui
        load_tabel_siswa();

        // Mengosongkan semua input form setelah data dihapus
        reset();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_btnResetActionPerformed

    private void cKelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cKelasMouseClicked
        // TODO add your handling code here:
        comboKelas();
    }//GEN-LAST:event_cKelasMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnTambah;
    private javax.swing.JComboBox<String> cGender;
    private javax.swing.JComboBox<String> cKelas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JTextArea tAlamat;
    private javax.swing.JTextField tNamaSiswa;
    private javax.swing.JTextField tNis;
    private javax.swing.JTextField tNoHp;
    private javax.swing.JLabel tPathFoto;
    private javax.swing.JTextField tTempatLahir;
    private com.toedter.calendar.JDateChooser tTgl;
    private javax.swing.JTable tblSiswa;
    // End of variables declaration//GEN-END:variables
}
