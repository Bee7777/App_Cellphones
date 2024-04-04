package com.example.doan_cellphoneapp.SERVER;

import com.example.doan_cellphoneapp.MODEL.GIOHANG;

import java.util.List;

public class SERVER {
    public static String path = "http://192.168.31.112:7000/apidienthoai/";
    public static String pathImages = path + "HinhNhaSanXuat/";
    public static String urlnhasanxuat = path + "apinhasanxuat.php";
    public static String urldienthoai = path + "dienthoai.php";

    public static String pathsanpham = path + "HinhSanPham/";

    public static String pathquangcao = path + "HinhQuangCao/";

    public static String urlquangcao = path + "quangcao.php";

    public static String urltheonhasanxuat = path + "dienthoai_theochude.php";

    public static List<GIOHANG> manggiohang;


    //biến số  lượng sản phẩm trong giỏ hàng
    public static int soluong=0;


}
