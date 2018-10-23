package com.iris.oracle4springboot.controller;

import com.sun.media.jfxmedia.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@RestController
public class OneController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/")
    @ResponseBody
    public String index(){


        String sql = "SELECT  IATA_NO  from tb_tte_agency where country_code='AU' AND IATA_NO IS NOT NULL";

        System.out.println("before:"+ LocalTime.now());

        List <Map <String, Object>> iataList=jdbcTemplate.queryForList(sql);

        System.out.println("after:"+ LocalTime.now());
        System.out.println("执行完成");


        return "hello spring boot";
    }


}
