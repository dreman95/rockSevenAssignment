package com.andre.rockSevenAssignment.controller;

import com.andre.rockSevenAssignment.service.BoatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoatController {
  
   @Autowired
   private BoatService boatService;

   @RequestMapping(method = RequestMethod.GET)
   ModelAndView
   index()
   { 
       ModelAndView mav = new ModelAndView("index");
       mav.addObject("boats", boatService.calculateAverageSightings());
       return mav;
   }
}
