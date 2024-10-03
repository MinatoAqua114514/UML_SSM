package com.uml.controller;

import com.uml.model.Listing;
import com.uml.service.ListingService;
import com.uml.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @RequestMapping(value = "/get_provinces", method = RequestMethod.GET)
    public ModelAndView getProvinces () {
        ModelAndView mv = new ModelAndView("index");
        List<String> provinces = locationService.findAllProvinces();
        mv.addObject("provinces", provinces);
        return mv;
    }

    @RequestMapping(value = "/get_cities/{province_name}", method = RequestMethod.GET)
    public ModelAndView getCities(@PathVariable("province_name") String province_name) {
        ModelAndView mv = new ModelAndView("index");
        List<String> cities = locationService.findAllCitiesOfProvince(province_name);
        mv.addObject("cities", cities);
        return mv;
    }

    @RequestMapping(value = "get_districts/{city_name}", method = RequestMethod.GET)
    public ModelAndView getDistricts(@PathVariable("city_name") String city_name) {
        ModelAndView mv = new ModelAndView("index");
        List<String> districts = locationService.findAllDistrictsOfCity(city_name);
        mv.addObject("districts", districts);
        return mv;
    }

}
