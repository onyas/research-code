package com.onyas.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
public class RedirectController {
    protected static final Logger logger = LoggerFactory.getLogger(RedirectController.class);


    @RequestMapping("redirect")
    public ModelAndView alipayforward(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        logger.info("访问/downloadRequestElecCont.action");
        String url = "redirect:http://baidu.com/";
        return new ModelAndView(url);
    }

    @RequestMapping("alipayforward/{contNo}")
    public void alipayforward2(@PathVariable("contNo") String contNo, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //String contNo =req.getParameter("contNo"); //保单号
        logger.info("访问/downloadRequestElecCont.action");
        resp.sendRedirect("http://baidu.com/downloadRequestElecCont.action?contNo=" + contNo);
    }

    @RequestMapping("alipayforward")
    public String alipayforward3(@RequestParam("contNo") String contNo, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //String contNo =req.getParameter("contNo"); //保单号
        logger.info("访问/downloadRequestElecCont.action");
        return "redirect:http://baidu.com/downloadRequestElecCont.action?contNo=" + contNo;
    }

    @RequestMapping(value = "/redirect4")
    public ResponseEntity<?> handleAuthCodeFlow() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "https://058f02a8.ngrok.io/redirect.html?username=18778205558&password=Test!123&country=us");
        return new ResponseEntity<>("", headers, HttpStatus.SEE_OTHER);
    }
}
