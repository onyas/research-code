package com.onyas.web.togglz;

import org.springframework.web.bind.annotation.*;
import org.togglz.servlet.util.HttpServletRequestHolder;

import javax.servlet.http.HttpServletRequest;

@RestController
public class DemoController {

    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    @ResponseBody
    public String demo() {
        return "Gradual_rollout:\t" + MyFeatures.Gradual_rollout.isActive() + "\t<br/>" +
                "Ip_address:\t" + MyFeatures.Ip_address.isActive() + "\t<br/>" +
                "Ip_address server:\t" + MyFeatures.Ip_address_s.isActive() + "<br/>" +
                "Release_date:\t" + MyFeatures.Release_date.isActive() + "<br/>" +
                "System_property:\t" + MyFeatures.System_property.isActive() + "<br/>" +
                "User_by_name:\t" + MyFeatures.User_by_name.isActive() + "<br/>" +
                "User_by_role:\t" + MyFeatures.User_by_role.isActive() + "<br/>" +
                "Archiver_5_2:\t" + MyFeatures.Archiver_5_2.isActive() + "<br/>"
                ;
    }


    @RequestMapping(value = "/ownerId", method = RequestMethod.GET)
    @ResponseBody
    public String ownerId(@RequestParam("ownerId") String ownerId, HttpServletRequest request) {
        request.setAttribute("ownerId", ownerId);
        HttpServletRequestHolder.release();
        HttpServletRequestHolder.bind(request);
        return "Archiver_5_2:\t" + MyFeatures.Archiver_5_2.isActive() + "<br/>";

    }
}
