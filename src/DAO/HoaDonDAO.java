/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connect.MyConnection;
import Model.CTHoaDon;
import Model.HoaDon;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 *
 * @author NHT_Kurumi
 */
public class HoaDonDAO {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    public List<HoaDon> getListHD(){
        List<HoaDon> HDlist = new ArrayList<HoaDon>();
        
        conn = MyConnection.getMyConnection();
        
        String sql = "SELECT * FROM hoadon";
        
        try{
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                HoaDon hd = new HoaDon();               
                hd.setMahd(rs.getString("MaHD"));
                hd.setMaban(rs.getInt("MaBan"));
                hd.setGioden(rs.getTimestamp("GioDen"));
                hd.setGiocapnhat(rs.getTimestamp("GioCapNhat"));
                hd.setTennv(rs.getString("TenNV"));
                hd.setTongtien(rs.getFloat("TongTien"));
                hd.setTrangthai(rs.getString("TrangThai"));
                
                HDlist.add(hd);
            }
            ps.close();
            conn.close();                
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return HDlist;
    }
    
    public HoaDon getHoaDonByMaHD(String mahd){
        
        conn = MyConnection.getMyConnection();
        
        String sql = "SELECT * FROM hoadon WHERE MaHD = ?";
        
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1,mahd);
            rs = ps.executeQuery();
            
            if(rs.next()){
                HoaDon hd = new HoaDon();               
                hd.setMahd(rs.getString("MaHD"));
                hd.setMaban(rs.getInt("MaBan"));
                hd.setGioden(rs.getTimestamp("GioDen"));
                hd.setGiocapnhat(rs.getTimestamp("GioCapNhat"));
                hd.setTennv(rs.getString("TenNV"));
                hd.setTongtien(rs.getFloat("TongTien"));
                hd.setTrangthai(rs.getString("TrangThai"));
                
                return hd;
            }
            ps.close();
            conn.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;// n???u kh??ng c?? d??? li???u
    }
    
    public List<HoaDon> getListHDbyMaHD(String mahd){
        List<HoaDon> HDlist = new ArrayList<HoaDon>();
        conn = MyConnection.getMyConnection();
        
        String sql = " SELECT * FROM hoadon WHERE MaHD LIKE ? ";
        
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1,mahd);            
            rs = ps.executeQuery();
            
            while(rs.next()){
                HoaDon hd = new HoaDon();               
                hd.setMahd(rs.getString("MaHD"));
                hd.setMaban(rs.getInt("MaBan"));
                hd.setGioden(rs.getTimestamp("GioDen"));
                hd.setGiocapnhat(rs.getTimestamp("GioCapNhat"));
                hd.setTennv(rs.getString("TenNV"));
                hd.setTongtien(rs.getFloat("TongTien"));
                hd.setTrangthai(rs.getString("TrangThai"));
                
                HDlist.add(hd);
            }
            ps.close();
            conn.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return HDlist;// n???u kh??ng c?? d??? li???u
    }
    
    public List<HoaDon> getListHDbyInputDay(String inputDay1, String inputDay2){
        List<HoaDon> HDlist = new ArrayList<HoaDon>();
        conn = MyConnection.getMyConnection();
        
        String sql = " SELECT * FROM hoadon where  GioDen >= ? AND GioDen <= ? ";
        
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, inputDay1);
            ps.setString(2, inputDay2);            
            rs = ps.executeQuery();
            
            while(rs.next()){
                HoaDon hd = new HoaDon();               
                hd.setMahd(rs.getString("MaHD"));
                hd.setMaban(rs.getInt("MaBan"));
                hd.setGioden(rs.getTimestamp("GioDen"));
                hd.setGiocapnhat(rs.getTimestamp("GioCapNhat"));
                hd.setTennv(rs.getString("TenNV"));
                hd.setTongtien(rs.getFloat("TongTien"));
                hd.setTrangthai(rs.getString("TrangThai"));
                
                HDlist.add(hd);
            }
            ps.close();
            conn.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return HDlist;// n???u kh??ng c?? d??? li???u
    }
    
    
//    
//    public void addHoaDon(HoaDon hd){
//        
//        conn = MyConnection.getMyConnection();
//        
//        String sql = "INSERT INTO hoa_don(mahd, ngayban, tonggiaban) VALUE(?,?,?)";
//        
//        try{
//            ps = connection.prepareStatement(sql);
//            
//            ps.setString(1, hd.getMahd());
//            ps.setDate(2, hd.getNgayban());
//            ps.setFloat(3, hd.getTonggiaban());
//            
//            int rs = ps.executeUpdate();
//            
//            ps.close();
//            conn.close();
//        }catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    
    
    public void deleteHoaDon(String mahd){
        
        conn = MyConnection.getMyConnection();
        
        String sql = "DELETE FROM hoadon WHERE MaHD = ?";
        
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, mahd);            
            int rs = ps.executeUpdate();

            ps.close();
            conn.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
