package com.nieyue;

import com.apiins.jarTest.MD5Util;

public class test {
    public static void main(String[] args) {
        String o="input_charset=GBK&partner=1900000109&total_fee=1&key=8934e7d15453e97507ef794cf7b0519d";
        String sign = MD5Util.md5Hex(o).toUpperCase();
        System.out.println(sign);
    }
}
