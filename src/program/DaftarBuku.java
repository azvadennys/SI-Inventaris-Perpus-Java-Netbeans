package program;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DaftarBuku extends javax.swing.JFrame {
    
    /*database buku menggunakan array 2D, dimensi pertama sebagai
    jumlah data maksimal yang ditampung. dimensi kedua adalah
    jumlah data pada kolomnya*/
    static String[][]buku = new String[100][5];
    //untuk cek kondisi, nantinya berguna saat menambah/menghapus/mengedit data
    static boolean cek = false; 
    //panjang array yang sudah terisi oleh data yang diinput
    static int top =7;
    
    //isi array sementara biar gak capek menambahkan data dulu :)
    public static void isiSementara(){
        buku[0][0] = "008";
        buku[0][1] = "Dasar Pemrograman Java";
        buku[0][2] = "Ady Wicaksono";
        buku[0][3] = "Elex Media Komputindo";
        buku[0][4] = "2002";
        buku[1][0] = "002";
        buku[1][1] = "Pemrograman Pascal I";
        buku[1][2] = "Abdul Khadir";
        buku[1][3] = "Andi";
        buku[1][4] = "1999";
        buku[2][0] = "010";
        buku[2][1] = "Kumpulan Solusi Pemrograman Phyton";
        buku[2][2] = "Budi Raharjo";
        buku[2][3] = "Informatika";
        buku[2][4] = "2019";
        buku[3][0] = "090";
        buku[3][1] = "Computers and Programming";
        buku[3][2] = "Francis Scheid";
        buku[3][3] = "McGraww-Hill";
        buku[3][4] = "1983";
        buku[4][0] = "034";
        buku[4][1] = "Matematika Diskrit";
        buku[4][2] = "Rinaldi Munir";
        buku[4][3] = "Informatika";
        buku[4][4] = "2012";
        buku[5][0] = "033";
        buku[5][1] = "Statistik Industri";
        buku[5][2] = "Akhmad Fauzi";
        buku[5][3] = "Erlangga";
        buku[5][4] = "2008";
        buku[6][0] = "059";
        buku[6][1] = "Turbo Pascal: Teori dan Aplikasi Program Komputer";
        buku[6][2] = "Jogiyanto";
        buku[6][3] = "Andi Offset";
        buku[6][4] = "1999";
    }
    
    //method untuk meng-update tampilan dari array ke table
    public static void updateTabel(String[][]array){
        DefaultTableModel model = (DefaultTableModel)tabelBuku.getModel();
        int jumlahBaris = model.getRowCount();
        //destroy isi table
        for(int i=jumlahBaris-1; i>=0; i--){
            model.removeRow(i);
        }
        //mengisi table lagi yg sudah diurutkan
        for(int j=0; j<top; j++){
            /*seleksi if agar array yang array yang sudah dihapus(array diisi 0"
            tidak ditampilkan*/
            if(array[j][0].equals("0") && array[j][1].equals("0")&&array[j][2].equals("0")&&array[j][3].equals("0")&&array[j][4].equals("0")){
                //intinya yang berisi 0 tidak ditampilkan
            }else{
                Object[]row = {array[j][0],array[j][1],array[j][2],array[j][3],array[j][4]};
                model.addRow(row);
            }
        }
        //atur model tabelBuku sesuai DefaultTableModel
        tabelBuku.setModel(model);
    }
    
    //method untuk menambahkan buku dengan parameter yang berisi data untuk kolomnya
    public static void tambahBuku(String id,String judul,String pengarang,String penerbit,String tahun){
        try {    
            DefaultTableModel model = (DefaultTableModel)tabelBuku.getModel();
            buku [top][0] = id;
            buku [top][1] = judul;
            buku [top][2] = pengarang;
            buku [top][3] = penerbit;
            buku [top][4] = tahun;        
            Object[]row ={buku [top][0],buku [top][1],buku [top][2],buku [top][3],buku [top][4]};
            model.addRow(row);
            top++;
            tabelBuku.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error! "+e);}
    }
    
    //method untuk menghapus buku dengan parameter id(ID Buku) yang ingin dihapus
    public static void hapusBuku(String id){
        DefaultTableModel model = (DefaultTableModel)tabelBuku.getModel();
        int index =0;
        //gunakan loop untuk mencari id yang sesuai di array
        while(index < top){
            if(id.equals(buku[index][0])){
                //kalau dapat, ingat di array elemen tidak bisa dihapus.
                //berarti kita isi saja datanya dengan 0
                buku[index][0]="0";
                buku[index][1]="0";
                buku[index][2]="0";
                buku[index][3]="0";
                buku[index][4]="0";
                model.removeRow(index);
                cek = true;
                break;
            }else{
                cek = false;
            }
            index++;
        }
        //panggil method updateTabel untuk mengatur ulang tampilan tabelnya
        updateTabel(buku);
    }
    
    //method untuk edit buku berparameter kolom-kolom yang akan diedit
    public static void editBuku(String id,String judul,String pengarang,String penerbit,String tahun){
        DefaultTableModel model = (DefaultTableModel)tabelBuku.getModel();
        int index =0;
        while(index < top){
            if(id.equals(buku[index][0])){
                buku[index][0]=id;
                buku[index][1]=judul;
                buku[index][2]=pengarang;
                buku[index][3]=penerbit;
                buku[index][4]=tahun;
                cek = true;
                break;
            }else{
                cek = false;
            }
            index++;
        }
        //panggil method updateTabel untuk mengatur ulang tampilan tabelnya
        updateTabel(buku);
    }
    
    /*method insertionSort untuk mengurutkan arraynya dengan parameter
    array yang akan diurutkan dan "angka" yaitu kolom yang dijadikan key(patokan pengurutan)*/
    public static void insertionSort(String[][] array, String angka){
        int indexInt = Integer.parseInt(angka);
        for(int i=0; i<top; i++){ //pakai top agar seluruh memori array tidak ikut di loop
            for(int j=i; j>0; j--){
                /*kondisi jika array setelahnya lebih kecil dari sebelum(konversi dari String 
                ke integer agar dapat dibandingkan)*/
                if(Integer.parseInt(array[j][indexInt]) < Integer.parseInt(array[j-1][indexInt])){
                    //jika kondisi terpenuhi maka swap array-nya
                    //digunakan loop lagi karena untuk swap yang dimensi kedua juga
                    for (int k = 0; k < 5 ; k++){  
                        String temp = array[j][k];
                        array[j][k]=array[j-1][k];
                        array[j-1][k] = temp ;
                   }
                    
                }
            }
        }
        //panggil method updateTabel untuk mengatur ulang tampilan tabelnya
        updateTabel(array);
    }
   
   
    //construktor untuk class DaftarBuku
    public DaftarBuku() {
        initComponents();
        tabelBuku.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,12));
        tabelBuku.getTableHeader().setBackground(new Color(102,102,102));
        tabelBuku.getTableHeader().setForeground(new Color(255,255,255));
    }

     //variable yang nantinya untuk menggeser window
    int mouseX;
    int mouseY;
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelBuku = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        tambahButton = new javax.swing.JButton();
        user = new javax.swing.JLabel();
        editButton = new javax.swing.JButton();
        hapusButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        exitButton = new javax.swing.JLabel();
        minimizeButton = new javax.swing.JLabel();
        pilihCb = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        tabelBuku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Buku", "Judul", "Pengarang", "Penerbit", "Tahun"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelBuku);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));

        tambahButton.setBackground(new java.awt.Color(172, 172, 172));
        tambahButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tambahButton.setForeground(new java.awt.Color(0, 0, 0));
        tambahButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/addbook.png"))); // NOI18N
        tambahButton.setText("TAMBAH");
        tambahButton.setFocusPainted(false);
        tambahButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tambahButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        tambahButton.setRequestFocusEnabled(false);
        tambahButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahButtonActionPerformed(evt);
            }
        });

        user.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        user.setForeground(new java.awt.Color(255, 255, 255));
        user.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/user.png"))); // NOI18N
        user.setText("USER");
        user.setIconTextGap(6);
        user.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                userPropertyChange(evt);
            }
        });

        editButton.setBackground(new java.awt.Color(172, 172, 172));
        editButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        editButton.setForeground(new java.awt.Color(0, 0, 0));
        editButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/edit_file_36px.png"))); // NOI18N
        editButton.setText("EDIT");
        editButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        editButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        editButton.setRequestFocusEnabled(false);
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        hapusButton.setBackground(new java.awt.Color(172, 172, 172));
        hapusButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        hapusButton.setForeground(new java.awt.Color(0, 0, 0));
        hapusButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/remove_book_36px.png"))); // NOI18N
        hapusButton.setText("HAPUS");
        hapusButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        hapusButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        hapusButton.setRequestFocusEnabled(false);
        hapusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusButtonActionPerformed(evt);
            }
        });

        logoutButton.setBackground(new java.awt.Color(172, 172, 172));
        logoutButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        logoutButton.setForeground(new java.awt.Color(0, 0, 0));
        logoutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/logout.png"))); // NOI18N
        logoutButton.setText("LOGOUT");
        logoutButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        logoutButton.setFocusPainted(false);
        logoutButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        logoutButton.setRequestFocusEnabled(false);
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        jLabel1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jLabel1PropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hapusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tambahButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(19, 19, 19))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(user)
                .addGap(10, 10, 10)
                .addComponent(tambahButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hapusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addContainerGap(173, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("DAFTAR BUKU");

        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/exit30.png"))); // NOI18N
        exitButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitButtonMouseClicked(evt);
            }
        });

        minimizeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/minimize.png"))); // NOI18N
        minimizeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        minimizeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minimizeButtonMouseClicked(evt);
            }
        });

        pilihCb.setBackground(new java.awt.Color(102, 102, 102));
        pilihCb.setForeground(new java.awt.Color(255, 255, 255));
        pilihCb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Urut Berdasarkan:", "ID Buku", "Tahun" }));
        pilihCb.setFocusable(false);
        pilihCb.setOpaque(true);
        pilihCb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pilihCbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pilihCb, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(144, 144, 144)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
                .addComponent(minimizeButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exitButton)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(minimizeButton)
                    .addComponent(exitButton)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(pilihCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //method untuk tombol tambah yang akan membuka kelas Tambah()
    private void tambahButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahButtonActionPerformed
        new Tambah().setVisible(true);
    }//GEN-LAST:event_tambahButtonActionPerformed
    
//method untuk tombol edit yang akan membuka kelas Edit()
    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        new Edit().setVisible(true);
    }//GEN-LAST:event_editButtonActionPerformed

    //method untuk tombol hapus yang akan membuka kelas Hapus()
    private void hapusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusButtonActionPerformed
        new Hapus().setVisible(true);
    }//GEN-LAST:event_hapusButtonActionPerformed

    //method untuk tombol logout yang akan menutup kelas ini dan membuka kelas Login();
    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_logoutButtonActionPerformed

    //method untuk tombol exit
    private void exitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseClicked
         ImageIcon icon = new ImageIcon("src/resources/closelogo.png");
        int jawab = JOptionPane.showOptionDialog(this,
            "Apakah anda ingin keluar?",
            "Confirm Dialog",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE, icon, null, null);

        if(jawab == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitButtonMouseClicked

    //method tombol minimize
    private void minimizeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeButtonMouseClicked
          this.setExtendedState(DaftarBuku.ICONIFIED);
    }//GEN-LAST:event_minimizeButtonMouseClicked

    //method untuk mengambil nilai variabel user di class Login dan mengaturnya ke tampilan
    private void userPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_userPropertyChange
        user.setText(Login.user);
    }//GEN-LAST:event_userPropertyChange

    //dua method dibawah ini intinya untuk menggeser window
    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        mouseX = evt.getX();
        mouseY = evt.getY();
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        int koordinatX = evt.getXOnScreen();
        int koordinatY = evt.getYOnScreen();
        this.setLocation(koordinatX-mouseX, koordinatY-mouseY);
    }//GEN-LAST:event_jPanel1MouseDragged

    //method untuk pilihan combo box saat memilih metode pengurutan data
    private void pilihCbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pilihCbActionPerformed
        try {
            int indexComboBox = pilihCb.getSelectedIndex();
            if(indexComboBox==1){
                insertionSort(buku, "0");
            }else if(indexComboBox==2){
                insertionSort(buku, "4");
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID Buku/Tahun ada yang kosong!\nHarap edit dahulu!");
        }
    }//GEN-LAST:event_pilihCbActionPerformed

    //method untuk menampilkan isi buku sementara saat aplikasi diluncurkan
    private void jLabel1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jLabel1PropertyChange
        isiSementara();
        updateTabel(buku);
    }//GEN-LAST:event_jLabel1PropertyChange

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
            java.util.logging.Logger.getLogger(DaftarBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DaftarBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DaftarBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DaftarBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DaftarBuku().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton editButton;
    private javax.swing.JLabel exitButton;
    private javax.swing.JButton hapusButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logoutButton;
    private javax.swing.JLabel minimizeButton;
    private javax.swing.JComboBox<String> pilihCb;
    private static javax.swing.JTable tabelBuku;
    private javax.swing.JButton tambahButton;
    private javax.swing.JLabel user;
    // End of variables declaration//GEN-END:variables
}
