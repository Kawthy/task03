/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: one
 * Author:   Kawthy
 * Date:     2019/3/25 11:19
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.ki.controller;

import com.sun.xml.internal.ws.runtime.config.TubelineFeatureReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Kawthy
 * @create 2019/3/25
 * @since 1.0.0
 */

@RunWith(JUnit4.class)
public class one {


    public boolean  Sj(){
        String s = "1,2,3,4,5";
        String[] ss = s.split(",");
        String isj = String.valueOf(3L);
        List<String> sjk = new ArrayList<>();
        sjk.add("ikikik");
        System.out.println();
        int j = 0;
        for (String i:ss){
            if (i.equals(isj)){
                System.out.println(i);
                return true;
            }
            j++;
            System.out.println(j);
        }
//        System.out.println(ss.toString());
//        System.out.println(s.matches("[0-9]+"));
        return  false;
    }


    @Test
    public void ssj(){
//        System.out.println(Sj());
        System.out.println("a1e".matches("[a-zA-Z0-9]+"));
    }

}