/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.HoaDon;

import DAO.*;
import Model.*;
import java.awt.Color;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
/**
 *
 * @author NHT_Kurumi
 */
public class jf_editHD extends javax.swing.JFrame {

    HoaDonDAO hoadonDAO;
    CTHoaDonDAO cthoadonDAO;
    private DefaultTableModel ModelTD;
    private DefaultTableModel ModelCTHD;
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    public static String SoLuong;
    String MaHD = String.valueOf(jp_HoaDon.tableHD.getValueAt(jp_HoaDon.tableHD.getSelectedRow(), 0));
    /**
     * Creates new form EditHoaDonJFrame
     */
    public jf_editHD() {
        initComponents();
        setTitle("Cập nhật hóa đơn");
        setResizable(false);//Vô hiệu hóa nút phóng to
        getContentPane().setBackground(Color.WHITE);//Backgrounf đổi màu
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);//Đóng Jframe khi click(x)
        
        LamMoi();

//        txtTongTienMoi.setText(String.valueOf(cbh.TinhTongTien(tableCTHD)));
        
        txtSearchSP.setToolTipText("Từ khóa phải có trong tên sản phẩm!");
    }
    
//    private void TableTD(){
//        sanphamService = new SanPhamService();
//        
//        ModelTD = new DefaultTableModel(){
//            @Override //Không cho người dùng edit table
//            public boolean isCellEditable(int row, int column) {
//                return false;
//            }   
//        };
//        
//        tableTD.setModel(ModelTD);
//        ModelTD.addColumn("STT");
//        ModelTD.addColumn("Mã SP");
//        ModelTD.addColumn("Tên SP");
//        ModelTD.addColumn("SL");
//        ModelTD.addColumn("Đơn giá");
//        
//        tableTD.getColumnModel().getColumn(0).setMinWidth(50);
//        tableTD.getColumnModel().getColumn(0).setMaxWidth(50);
//        tableTD.getColumnModel().getColumn(1).setMinWidth(200);
//        tableTD.getColumnModel().getColumn(1).setMaxWidth(250);
//        tableTD.getColumnModel().getColumn(2).setMinWidth(20);
//        tableTD.getColumnModel().getColumn(2).setMinWidth(20);
//        tableTD.getColumnModel().getColumn(3).setMinWidth(80);
//        tableTD.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
//        
//        setDataSPtable(sanphamService.getListSP());
//    }
//
//    private void setDataSPtable(List<SanPham> SPlist){
//        for(SanPham sp : SPlist){
//            ModelTD.addRow(new Object[]{
//                sp.getMasp(), sp.getTensp(), sp.getSoluong(), 
//                String.format("%.0f",sp.getDongiaban())
//            });
//        }
//    }
    
    private void TableCTHD(){
        cthoadonDAO = new CTHoaDonDAO();
        ModelCTHD = new DefaultTableModel(){
            @Override //Không cho người dùng edit table
            public boolean isCellEditable(int row, int column) {
                return false;
            }   
        };
        tableCTHD.setModel(ModelCTHD); 
        ModelCTHD.addColumn("Mã món");
        ModelCTHD.addColumn("STT");
        ModelCTHD.addColumn("Tên món");
        ModelCTHD.addColumn("Đơn vị");
        ModelCTHD.addColumn("Số lượng");
        ModelCTHD.addColumn("Đơn giá");
        ModelCTHD.addColumn("Thành tiền");
        
        //Giảm đọ dài cột đầu tiên của tableCTHD về 0
        tableCTHD.getColumnModel().getColumn(0).setMinWidth(0);
        tableCTHD.getColumnModel().getColumn(0).setMaxWidth(0);
        tableCTHD.getColumnModel().getColumn(1).setMinWidth(50);
        tableCTHD.getColumnModel().getColumn(1).setMaxWidth(50);
        tableCTHD.getColumnModel().getColumn(2).setMinWidth(285);
        tableCTHD.getColumnModel().getColumn(2).setMaxWidth(285);
        tableCTHD.getColumnModel().getColumn(3).setMinWidth(135);
        tableCTHD.getColumnModel().getColumn(3).setMaxWidth(135);
        tableCTHD.getColumnModel().getColumn(4).setMinWidth(70);
        tableCTHD.getColumnModel().getColumn(4).setMaxWidth(70);
        tableCTHD.getColumnModel().getColumn(5).setMinWidth(170);
        tableCTHD.getColumnModel().getColumn(5).setMaxWidth(170);
        tableCTHD.getColumnModel().getColumn(6).setMinWidth(170);
        tableCTHD.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        
        setDataCTHDtable2(cthoadonDAO.getListCTHDbyMaHD(MaHD));
        
    }
    
    private void setDataCTHDtable1(ThucDon td, String SoLuong) {
        ModelCTHD.addRow(new Object[]{
            td.getMamon(),ModelCTHD.getRowCount()+1, td.getTenmon(), 
            td.getDvtinh(), SoLuong, String.format("%.0f",td.getDongia()), 
            String.format("%.0f",Float.parseFloat(SoLuong) * td.getDongia())
            });
        
    }
    
    private void setDataCTHDtable2(List<CTHoaDon> CTHDlist) {
        for(CTHoaDon cthd : CTHDlist){
        ModelCTHD.addRow(new Object[]{
            cthd.getMamon(), 
            ModelCTHD.getRowCount() + 1, 
            cthd.getTenmon(), cthd.getDvtinh(), 
            cthd.getSoluong(),
            String.format("%.0f",cthd.getDongia()),
            String.format("%.0f",cthd.getThanhtien())
            });
        }
    }
    
    
//    private void ThanhToan(){
//        txtTongTienMoi.setText(cbh.TinhTongTien(tableCTHD));
//        float TongTienMoi = Float.valueOf(txtTongTienMoi.getText().toString());
//        float TongTienCu = Float.valueOf(txtTongTienCu.getText().toString());
//        float KetToan = TongTienCu - TongTienMoi;
//        txtTienTraKhach.setText(String.format("%.0f", KetToan));
//    }
    
    public void LamMoi(){
        hoadonDAO = new HoaDonDAO();
        HoaDon hd = hoadonDAO.getHoaDonByMaHD(MaHD);
        lblMaHD.setText(hd.getMahd());
        lblMaBan.setText(String.valueOf(hd.getMaban()));
        lblGioLap.setText(df.format(hd.getGioden()));
        lblGioCapNhat.setText(df.format(hd.getGiocapnhat()));
        lblTongTien.setText(String.format("%,.0f",hd.getTongtien()));
        lblTenNV.setText(hd.getTennv());
        
        TableCTHD();
//        ThanhToan();
        
        //Load Item combobox
//        jcbDVTinh.removeAllItems();
//        sanphamService = new SanPhamService();
//        sanphamService.loadDataComboBox(this.jcbDVTinh);
        
        txtSearchSP.setText("");
        txtSLMua.setText("1");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pmnCTHD = new javax.swing.JPopupMenu();
        mniDeleteTD = new javax.swing.JMenuItem();
        mniEditSP = new javax.swing.JMenuItem();
        panelHoaDon = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCTHD = new javax.swing.JTable();
        txtTongTienMoi = new javax.swing.JTextField();
        txtTienTraKhach = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jbtThanhToan = new javax.swing.JButton();
        jbtClose = new javax.swing.JButton();
        lblTongTien = new javax.swing.JLabel();
        lblGioCapNhat = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblTenNV = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblGioLap = new javax.swing.JLabel();
        lblMaHD = new javax.swing.JLabel();
        lblMaBan = new javax.swing.JLabel();
        panelSanPham = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableTD = new javax.swing.JTable();
        txtSearchSP = new javax.swing.JTextField();
        jcbDVTinh = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtSLMua = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btSelectMon = new javax.swing.JButton();

        mniDeleteTD.setText("Xóa sản phẩm");
        mniDeleteTD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDeleteTDActionPerformed(evt);
            }
        });
        pmnCTHD.add(mniDeleteTD);

        mniEditSP.setText("Sửa sản phẩm");
        mniEditSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniEditSPActionPerformed(evt);
            }
        });
        pmnCTHD.add(mniEditSP);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        panelHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        panelHoaDon.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        tableCTHD.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tableCTHD.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tableCTHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên món", "Đon vị", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        tableCTHD.setComponentPopupMenu(pmnCTHD);
        tableCTHD.setGridColor(new java.awt.Color(255, 255, 255));
        tableCTHD.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                tableCTHDComponentShown(evt);
            }
        });
        jScrollPane2.setViewportView(tableCTHD);

        txtTongTienMoi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTongTienMoi.setText("0");
        txtTongTienMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongTienMoiActionPerformed(evt);
            }
        });
        txtTongTienMoi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTongTienMoiKeyReleased(evt);
            }
        });

        txtTienTraKhach.setEditable(false);
        txtTienTraKhach.setBackground(new java.awt.Color(255, 255, 255));
        txtTienTraKhach.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTienTraKhach.setText("0");
        txtTienTraKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienTraKhachActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Tổng tiền mới");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Tiền trả khách");

        jbtThanhToan.setBackground(new java.awt.Color(0, 204, 0));
        jbtThanhToan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jbtThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        jbtThanhToan.setText("Lưu");
        jbtThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtThanhToanActionPerformed(evt);
            }
        });

        jbtClose.setBackground(new java.awt.Color(204, 0, 0));
        jbtClose.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jbtClose.setForeground(new java.awt.Color(255, 255, 255));
        jbtClose.setText("Đóng");
        jbtClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCloseActionPerformed(evt);
            }
        });

        lblTongTien.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTongTien.setForeground(java.awt.Color.red);

        lblGioCapNhat.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblGioCapNhat.setForeground(java.awt.Color.red);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel10.setText("Nhân viên lập");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel9.setText("Mã HD");

        lblTenNV.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTenNV.setForeground(java.awt.Color.red);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel11.setText("Giờ đến");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel12.setText("Tổng tiền");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel13.setText("Giờ cập nhật");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel14.setText("Mã bàn");

        lblGioLap.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblGioLap.setForeground(java.awt.Color.red);

        lblMaHD.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMaHD.setForeground(java.awt.Color.red);

        lblMaBan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMaBan.setForeground(java.awt.Color.red);

        javax.swing.GroupLayout panelHoaDonLayout = new javax.swing.GroupLayout(panelHoaDon);
        panelHoaDon.setLayout(panelHoaDonLayout);
        panelHoaDonLayout.setHorizontalGroup(
            panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(panelHoaDonLayout.createSequentialGroup()
                        .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblMaHD, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                            .addComponent(lblMaBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(85, 85, 85)
                        .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblGioCapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                            .addComponent(lblGioLap, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                            .addComponent(lblTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelHoaDonLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTongTienMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTienTraKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addComponent(jbtClose, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbtThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelHoaDonLayout.setVerticalGroup(
            panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelHoaDonLayout.createSequentialGroup()
                        .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMaBan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelHoaDonLayout.createSequentialGroup()
                        .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblGioLap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblGioCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtClose, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongTienMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienTraKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelSanPham.setBackground(new java.awt.Color(255, 255, 255));
        panelSanPham.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thực đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        tableTD.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tableTD.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tableTD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã món", "Tên món", "Số lượng", "Đơn giá"
            }
        ));
        tableTD.setGridColor(new java.awt.Color(255, 255, 255));
        tableTD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableTDMouseClicked(evt);
            }
        });
        tableTD.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                tableTDComponentShown(evt);
            }
        });
        jScrollPane1.setViewportView(tableTD);

        txtSearchSP.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtSearchSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchSPActionPerformed(evt);
            }
        });
        txtSearchSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchSPKeyReleased(evt);
            }
        });

        jcbDVTinh.setBackground(new java.awt.Color(0, 153, 255));
        jcbDVTinh.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jcbDVTinh.setForeground(new java.awt.Color(255, 255, 255));
        jcbDVTinh.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jcbDVTinhPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jcbDVTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbDVTinhActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Chọn đơn vị tính");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Nhập từ khóa");

        txtSLMua.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtSLMua.setText("1");
        txtSLMua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSLMuaActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Nhập số lượng");

        btSelectMon.setBackground(new java.awt.Color(0, 204, 0));
        btSelectMon.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btSelectMon.setForeground(new java.awt.Color(255, 255, 255));
        btSelectMon.setText("Thêm");
        btSelectMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSelectMonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSanPhamLayout = new javax.swing.GroupLayout(panelSanPham);
        panelSanPham.setLayout(panelSanPhamLayout);
        panelSanPhamLayout.setHorizontalGroup(
            panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSanPhamLayout.createSequentialGroup()
                        .addGroup(panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelSanPhamLayout.createSequentialGroup()
                                .addComponent(txtSLMua, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btSelectMon, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelSanPhamLayout.createSequentialGroup()
                        .addGroup(panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
                            .addGroup(panelSanPhamLayout.createSequentialGroup()
                                .addGroup(panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelSanPhamLayout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(126, 126, 126)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelSanPhamLayout.createSequentialGroup()
                                        .addComponent(txtSearchSP, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jcbDVTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        panelSanPhamLayout.setVerticalGroup(
            panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchSP, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbDVTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSLMua, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSelectMon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableCTHDComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tableCTHDComponentShown

    }//GEN-LAST:event_tableCTHDComponentShown

    private void txtTongTienMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongTienMoiActionPerformed
//        ThanhToan();
    }//GEN-LAST:event_txtTongTienMoiActionPerformed

    private void txtTongTienMoiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTongTienMoiKeyReleased

    }//GEN-LAST:event_txtTongTienMoiKeyReleased

    private void txtTienTraKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienTraKhachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienTraKhachActionPerformed
    int i=0;
    private void jbtThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtThanhToanActionPerformed
        if (Float.parseFloat(txtTongTienMoi.getText()) != 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng thêm sản phẩm vào phiếu nhập!");
        } else {
            
            //Update tổng tiền hóa đơn
            String MaHD = lblMaHD.getText().toString();
            HoaDon hd = hoadonDAO.getHoaDonByMaHD(MaHD);
            //xóa ds trc đó trong mysql
            CTHoaDon cthd = cthoadonDAO.getCTHoaDonByMaHD(MaHD);
            cthoadonDAO.deleteCTHoaDon(MaHD);
            for (int i = 0; i < tableCTHD.getRowCount(); i++) {
            //thêm ds mới trong table vào mysql
            cthd.setMahd(MaHD);
            cthd.setMamon(Integer.parseInt(tableCTHD.getValueAt(i, 0).toString()));
            cthd.setTenmon(tableCTHD.getValueAt(i, 2).toString());
            cthd.setDvtinh(tableCTHD.getValueAt(i, 3).toString());
            cthd.setSoluong(Integer.parseInt(tableCTHD.getValueAt(i, 4).toString()));
            cthd.setDongia(Float.parseFloat(tableCTHD.getValueAt(i, 5).toString()));
            cthd.setThanhtien(Float.parseFloat(tableCTHD.getValueAt(i, 6).toString()));
            cthoadonDAO.addCTHoaDon(cthd);
            }

            JOptionPane.showMessageDialog(this, "Bạn đã cập nhật thành công!");
            LamMoi();
            i=i+1;
        } 
    }//GEN-LAST:event_jbtThanhToanActionPerformed

    private void jbtCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCloseActionPerformed
//        if (i == 0) {
//            if (j >= 1 || m >= 1 || n >= 1) {
//                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn lưu thay đổi không?", "Thông báo", JOptionPane.YES_NO_OPTION);
//                if (confirm == JOptionPane.YES_OPTION) {
//                    //Update tổng tiền hóa đơn
//                    String MaHD = txtMaHD.getText().toString();
//                    HoaDon hd = hoadonService.getHoaDonByMaHD(MaHD);
//                    hd.setTonggiaban(Float.parseFloat(txtTongTienMoi.getText()));
//                    hoadonService.updateHoaDon(hd);
//                    //Thêm cthd
//                    cehd = new CodeEditHoaDon();
//                    cehd.EditHDandCTHD(tableCTHD, txtMaHD.getText().toString());
//
//                    this.dispose();
//                } else {
//                    this.dispose();
//                }
//            } else {
//                this.dispose();
//            }
//        } else {
//            this.dispose();
//        }
    }//GEN-LAST:event_jbtCloseActionPerformed

    private void tableTDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableTDMouseClicked

    }//GEN-LAST:event_tableTDMouseClicked

    private void tableTDComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tableTDComponentShown

    }//GEN-LAST:event_tableTDComponentShown

    private void txtSearchSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchSPActionPerformed

    private void txtSearchSPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchSPKeyReleased
//        String TuKhoa = "%"+ txtSearchSP.getText() +"%";
//
//        ModelTD.setRowCount(0);
//        setDataSPtable(sanphamService.getListSPbyTenSP(tableTD, TuKhoa));
    }//GEN-LAST:event_txtSearchSPKeyReleased

    private void jcbDVTinhPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jcbDVTinhPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        //Tìm kiếm bằng Jcombobox
//        try {
//            String SPdvtinh = (String) this.jcbDVTinh.getSelectedItem();
//
//            //Làm mới mỗi lần tìm kiếm tableTD
//            ModelSP.setRowCount(0);
//            setDataSPtable(sanphamService.getListSPbyDVTinh(SPdvtinh));
//
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
//            e.printStackTrace();
//        }
    }//GEN-LAST:event_jcbDVTinhPopupMenuWillBecomeInvisible

    private void jcbDVTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbDVTinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbDVTinhActionPerformed

    private void txtSLMuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSLMuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSLMuaActionPerformed
    int m=0;
    private void mniDeleteTDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDeleteTDActionPerformed
        try {
            int row = tableCTHD.getSelectedRow();

            ModelCTHD.removeRow(row);
            for (int i = 0; i < tableCTHD.getRowCount(); i++) {
                tableCTHD.setValueAt(i + 1, i, 1);//reset STT
            }
//            ThanhToan();
            m=m+1;
            //LamMoi();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm!", "Error Warning", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_mniDeleteTDActionPerformed
    int n=0;
    private void mniEditSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniEditSPActionPerformed
//        try {
//            int row = tableCTHD.getSelectedRow();
//            SLMua2=tableCTHD.getValueAt(row, 4).toString();
//            System.out.println("SLM2:"+SLMua2);
//            
//            //Thêm SP
//            String MaSPtableSP = tableCTHD.getValueAt(row, 0).toString();
//            SanPham sp = sanphamService.getSanPhamByMaSP(MaSPtableSP);
//            setDataCTHDtable1(sp, SLMua2);
//            
//            //Xóa hàng vừa được thêm vào
//            ModelCTHD.removeRow(ModelCTHD.getRowCount() - 1);
//
//                
//            JFrameHDeditSLM editSLM = new JFrameHDeditSLM();
//            editSLM.setLocationRelativeTo(null);
//            editSLM.setVisible(true);
//            n=n+1;
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm!", "Error Warning", JOptionPane.ERROR_MESSAGE);
//        }
    }//GEN-LAST:event_mniEditSPActionPerformed
    int j=0;
    private void btSelectMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSelectMonActionPerformed
//        int row = tableTD.getSelectedRow();
//        if (row == -1) {
//            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm!", "Error Warning", JOptionPane.ERROR_MESSAGE);
//        } else {
//            SLMua2 = txtSLMua.getText();
//            if (Integer.parseInt(SLMua2) <= 0 || SLMua2.equals("")) {
//                JOptionPane.showMessageDialog(this, "Vui lòng nhập lại số lượng mua!");
//                txtSLMua.requestFocusInWindow();
//            } else {
//                String MaSPtableSP = tableTD.getValueAt(row, 0).toString();
//                SanPham sp = sanphamService.getSanPhamByMaSP(MaSPtableSP);
//
//                // sao chép dữ liệu từ table vào list
//                //Chuyển về vector
//                Vector data = ModelCTHD.getDataVector();
//                // sao chép dữ liệu từ table vào list
//                int mColIndex = 0;
//                List colData = new ArrayList(tableCTHD.getRowCount());
//                for (int i = 0; i < tableCTHD.getRowCount(); i++) {
//                    Vector row2 = (Vector) data.elementAt(i);
//                    colData.add(row2.get(mColIndex));
//                }
//                //so sánh với mã sp trong list vừa có
//                if (colData.contains(MaSPtableSP)) {
//                    JOptionPane.showMessageDialog(this, "Sản phẩm đã có trong hóa đơn!", "Error Warning", JOptionPane.ERROR_MESSAGE);
//                } else {
//                    //Kiểm tra số lượng nhập vào
//                    int SL = Integer.valueOf(tableTD.getValueAt(row, 2).toString());
//                    if (Integer.valueOf(SLMua2) > SL) {
//                        JOptionPane.showMessageDialog(this, "Vui lòng nhập lại số lượng mua!");
//                        txtSLMua.requestFocusInWindow();
//                    } else {
//                        setDataCTHDtable1(sp, SLMua2);
//
//                        txtSLMua.requestFocusInWindow();
//                        //Làm mới tiền trả
//                        ThanhToan();
//                        
//                        j=j+1;
//                    }
//                }
//            }
//        }
    }//GEN-LAST:event_btSelectMonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btSelectMon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtClose;
    private javax.swing.JButton jbtThanhToan;
    private javax.swing.JComboBox<String> jcbDVTinh;
    private javax.swing.JLabel lblGioCapNhat;
    private javax.swing.JLabel lblGioLap;
    private javax.swing.JLabel lblMaBan;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JLabel lblTenNV;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JMenuItem mniDeleteTD;
    private javax.swing.JMenuItem mniEditSP;
    private javax.swing.JPanel panelHoaDon;
    private javax.swing.JPanel panelSanPham;
    private javax.swing.JPopupMenu pmnCTHD;
    public static javax.swing.JTable tableCTHD;
    private static javax.swing.JTable tableTD;
    private javax.swing.JTextField txtSLMua;
    private javax.swing.JTextField txtSearchSP;
    public static javax.swing.JTextField txtTienTraKhach;
    public static javax.swing.JTextField txtTongTienMoi;
    // End of variables declaration//GEN-END:variables
}
