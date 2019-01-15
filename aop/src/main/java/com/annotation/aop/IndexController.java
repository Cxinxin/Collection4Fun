package com.annotation.aop;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

        @Log(requestUrl = "/index")
        @RequestMapping(method = RequestMethod.GET)
        public String index() {
            return "index";

    }

}
