package com.omiomi.exercises.neo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.omiomi.exercises.neo.config.AppConfig;
/**
 * Main page - map location / from here
 * @author omi
 *
 */

@Controller
public class HomeController {
	@Autowired
	AppConfig config;

	/**
	 * Controller to serve the home page (/)
	 * @return name of index page.
	 */
	@GetMapping(value="/", produces="plain/text")
    public String indexPage() {
        return "index.html";
    }
}
