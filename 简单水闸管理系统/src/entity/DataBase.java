package entity;

import other.Methods;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author xie
 * @create 2022-05-19-15:22
 * function: handles database-related operations
 * all public methods are static
 */
public class DataBase {

    public static void main(String[] args){
        restoreTables();
    }
    /**
     * export all data from the User and Watergate tables
     * @return List includes the data form the User and Watergate tables
     */
    public static List select(){
        List ans = new ArrayList();
        ans.add(selectUser());
        ans.add(selectWaterGate());
        return ans;
    }

    /**
     * export all data from the User table
     * @return List<User> includes the data from the User tables
     */
    public static List<User> selectUser(){
        String sql = "select * from user";
        List<User> users = new ArrayList<User>();
        Connection conn = DbUtil.getConnection();
        PreparedStatement ptmt = null;
        ResultSet rs = null;
        try{
            ptmt = conn.prepareStatement(sql);
            rs = ptmt.executeQuery(sql);
            // 如果有数据，rs.next()返回true
            while(rs.next()){
                User us = new User();
                us.setUser_name(rs.getString("user_name"));
                us.setJob_number(rs.getString("job_number"));
                us.setPassword(rs.getString("password"));
                us.setRole(rs.getString("role"));
                us.setCurrent_state(rs.getString("current_state"));
                users.add(us);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            dbclose(rs,ptmt,conn);
        }
        System.out.println(Methods.getCurrentTime() +": 数据库用户数据导出成功");
        return users;
    }

    /**
     * export the data form the Watergate table
     * @return List<WaterGate> include the data from the Watergate table
     */
    public static List<WaterGate> selectWaterGate(){
        String sql = "select * from watergate";
        List<WaterGate> waterGates = new ArrayList<WaterGate>();
        Connection conn = DbUtil.getConnection();
        PreparedStatement ptmt = null;
        ResultSet rs = null;
        try{
            ptmt = conn.prepareStatement(sql);
            rs = ptmt.executeQuery(sql);
            // 如果有数据，rs.next()返回true
            while(rs.next()){
                WaterGate wg = new WaterGate();
                wg.setWatergateid(rs.getString("watergateid"));
                wg.setBuild_time(rs.getString("build_time"));
                wg.setIntroduction(rs.getString("introduction"));
                wg.setLatitude(rs.getString("latitude"));
                wg.setLongitude(rs.getString("longitude"));
                wg.setWatergatename(rs.getString("watergatename"));
                wg.setPrincipal(rs.getString("principal"));
                waterGates.add(wg);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            dbclose(rs,ptmt,conn);
        }
        System.out.println(Methods.getCurrentTime()+": 数据库水闸数据导出成功");
        return waterGates;
    }

    /**
     * delete data with ID equal 'id' form the Watergate table
     * @param watergateid the id of the water gate
     */
    public static void deleteWaterGate(String watergateid){
        String sql = "delete from watergate where watergateid = ?";
        Connection conn = DbUtil.getConnection();
        PreparedStatement ptmt = null;
        ResultSet rs = null;
        try{
            ptmt = conn.prepareStatement(sql);
            ptmt.setString(1,watergateid);
            ptmt.execute();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            dbclose(rs,ptmt,conn);
        }
        System.out.println(Methods.getCurrentTime()+": 数据库删除id="+watergateid+"的水闸数据完成");
    }

    /**
     * update the water gate information
     * @param wg WaterGate
     */
    public static void updateWaterGate(WaterGate wg){
        String sql = "update watergate set watergatename = ? , " +
                "build_time = ? , " +
                "principal = ? , " +
                "longitude = ? ," +
                "latitude = ? ," +
                "introduction = ? " +
                "where watergateid = ? ";
        Connection conn = DbUtil.getConnection();
        PreparedStatement ptmt = null;
        try {
            ptmt = conn.prepareStatement(sql);
            ptmt.setString(1,wg.getWatergatename());
            ptmt.setString(2,wg.getBuild_time());
            ptmt.setString(3,wg.getPrincipal());
            ptmt.setString(4,wg.getLongitude());
            ptmt.setString(5,wg.getLatitude());
            ptmt.setString(6,wg.getIntroduction());
            ptmt.setString(7,wg.getWatergateid());
            ptmt.execute();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbclose(ptmt,conn);
        }
        System.out.println(Methods.getCurrentTime()+": 数据库修改id="+wg.getWatergateid()+"的水闸数据完成");
    }

    /**
     * insert new water gate information
     * @param wg WaterGate
     */
    public static void insertWaterGate(WaterGate wg){
        String sql = "insert into watergate(watergateid,watergatename,build_time,principal,longitude,latitude,introduction)" +
                "value(?,?,?,?,?,?,?)";
        Connection conn = DbUtil.getConnection();
        PreparedStatement ptmt = null;
        try {
            ptmt = conn.prepareStatement(sql);
            ptmt.setString(1,wg.getWatergateid());
            ptmt.setString(2,wg.getWatergatename());
            ptmt.setString(3,wg.getBuild_time());
            ptmt.setString(4,wg.getPrincipal());
            ptmt.setString(5,wg.getLongitude());
            ptmt.setString(6,wg.getLatitude());
            ptmt.setString(7,wg.getIntroduction());
            ptmt.execute();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbclose(ptmt,conn);
        }
        System.out.println(Methods.getCurrentTime()+": 数据库插入id="+wg.getWatergateid()+"的水闸数据完成");
    }

    /**
     * close all accesses of the database
     * @param ptmt PreparedStatement
     * @param conn Connect
     */
    private static void dbclose(PreparedStatement ptmt,Connection conn){
        dbclose(null,ptmt,conn);
    }

    /**
     * close all accesses of the database
     * @param rs   ResultSet
     * @param ptmt PreparedStatement
     * @param conn Connect
     */
    private static void dbclose(ResultSet rs,PreparedStatement ptmt,Connection conn){
        if(rs!=null){
            try{
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(ptmt!=null){
            try{
                ptmt.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(conn != null){
            try{
                conn.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * init all tables
     */
    public static void initTables(){
        initUser();
        initWaterGate();
    }

    /**
     * init the User table
     */
    public static void initUser(){
        String drop = "drop table if exists user";
        String sql = "create table user(" +
                "user_name varchar(10) not null comment '用户名'," +
                "job_number varchar(10) not null comment '工号'," +
                "password varchar(20) not null comment '密码'," +
                "role varchar(8) not null comment '角色(管理员、普通用户)'," +
                "current_state varchar(4) not null comment '当前状态(正常、停用)'," +
                "primary key(user_name))";
        Connection conn = DbUtil.getConnection();
        PreparedStatement ptmt = null;
        try{
            ptmt = conn.prepareStatement(drop);
            ptmt.execute();
            ptmt = conn.prepareStatement(sql);
            ptmt.execute();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbclose(ptmt,conn);
        }
        System.out.println(Methods.getCurrentTime()+": 数据库用户表初始化成功");
    }

    /**
     * init the Watergate table
     */
    public static void initWaterGate(){
        String drop = "drop table if exists watergate";
        String sql = "create table WaterGate(" +
                "watergateid varchar(5) not null," +
                "watergatename varchar(10) not null comment '水闸名'," +
                "build_time varchar(15) not null comment '建成时间'," +
                "principal varchar(10) not null comment '负责人'," +
                "longitude double not null comment '经度'," +
                "latitude double not null comment '纬度'," +
                "introduction varchar(20) comment '简介'," +
                "primary key(watergateid)) ENGINE=InnoDB DEFAULT CHARSET=utf8";       Connection conn = DbUtil.getConnection();
        PreparedStatement ptmt = null;
        try{
            ptmt = conn.prepareStatement(drop);
            ptmt.execute();
            ptmt = conn.prepareStatement(sql);
            ptmt.execute();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbclose(ptmt,conn);
        }

        System.out.println(Methods.getCurrentTime()+": 数据库水闸信息表初始化成功");
    }

    /**
     * restore the entire database
     */
    public static void restoreTables(){
        restoreUser();
        restoreWaterGate();
    }

    /**
     * restore the User table
     * missing configuration file
     * Use file operations to import information
     */
    public static void restoreUser() {
        System.out.println("开始还原数据库用户表数据......");
        initUser(); // init the User table

        Scanner input = new Scanner(System.in);
        String sql = "insert into user(user_name,job_number,password,role,current_state)" +
                "value(?,?,?,?,?)";
        int number = 0;
        Connection conn = DbUtil.getConnection();
        PreparedStatement ptmt = null;

        File file = new File("E:\\IntelliJ IDEA\\IdeaProjects\\简单水闸管理系统\\src\\entity\\restoreUserTable.txt");
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                String strs[] = str.split(" ");
                if (strs.length != 5) {
                    continue;
                }
                try {
                    ptmt = conn.prepareStatement(sql);
                    ptmt.setString(1, strs[0]);
                    ptmt.setString(2, strs[1]);
                    ptmt.setString(3, strs[2]);
                    ptmt.setString(4, strs[3]);
                    ptmt.setString(5, strs[4]);
                    ptmt.execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
                fileReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            dbclose(ptmt, conn);
        }
        System.out.println("开始还原数据库用户表数据......");
    }
    /**
     * restore the Watergate table
     */
    public static void restoreWaterGate(){
        System.out.println("开始还原数据库用户表数据......");
        initWaterGate(); // init the Watergate table

        Scanner input = new Scanner(System.in);
        String sql = "insert into watergate(watergateid,watergatename,build_time,principal,longitude,latitude,introduction)" +
                "value(?,?,?,?,?,?,?)";
        Connection conn = DbUtil.getConnection();
        PreparedStatement ptmt = null;

        File file = new File("E:\\IntelliJ IDEA\\IdeaProjects\\简单水闸管理系统\\src\\entity\\restoreWatergateTable.txt");
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try{
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String str = null;
            while((str=bufferedReader.readLine())!=null){
                String strs[] = str.split(" ");
                if(strs.length != 7){
                    continue;
                }
                try {
                    ptmt = conn.prepareStatement(sql);
                    ptmt.setString(1,strs[0]);
                    ptmt.setString(2,strs[1]);
                    ptmt.setString(3,strs[2]);
                    ptmt.setString(4,strs[3]);
                    ptmt.setString(5,strs[4]);
                    ptmt.setString(6,strs[5]);
                    ptmt.setString(7,strs[6]);
                    ptmt.execute();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            try{
                bufferedReader.close();
                fileReader.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            dbclose(ptmt,conn);
        }
        System.out.println("开始还原数据库用户表数据......");
    }
}











