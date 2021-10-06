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
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;// nếu không có dữ liệu
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
        return HDlist;// nếu không có dữ liệu
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
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return HDlist;// nếu không có dữ liệu
    }
    
    
    
    public void addHoaDon(HoaDon hd){
        
        conn = MyConnection.getMyConnection();
        String sql = "INSERT INTO hoadon(MaBan, TenNV) VALUE(?,?)";
        
        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1, hd.getMaban());
            ps.setString(2, hd.getTennv());
            
            int rs = ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateHoaDon(HoaDon hd){
        
        conn = MyConnection.getMyConnection();
        
        String sql = "update hoadon set trangthai = ? WHERE mahd = ?";
        
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(2, hd.getMahd());
            ps.setString(1, hd.getTrangthai());
            
            int rs = ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteHoaDon(String mahd){
        
        conn = MyConnection.getMyConnection();
        String sql = "DELETE FROM hoadon WHERE MaHD = ?";
        
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, mahd);            
            int rs = ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public HoaDon GetHoaDonByMaBan(int maban){
        
        conn = MyConnection.getMyConnection();
        String sql = "Select * From hoadon Where MaBan = ? AND TrangThai = 'Chưa thanh toán'";
        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1, maban);
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
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;        
    }
}
