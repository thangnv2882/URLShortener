package com.urlshortener.controllers;

import com.urlshortener.model.URL;
import com.urlshortener.service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class URLController {

    @Autowired
    private URLService urlService;

    private boolean report = false;

    @GetMapping(value = {"/", "/home"})
    public String getHome(Model model) {
        URL url = urlService.findByURLId(URLService.URLIdPresent);
        model.addAttribute("mess", report);
        report = false;
        if (url != null) {
            model.addAttribute("urlOri", url.getOriginalURL());
            model.addAttribute("urlShort", url.getShortenURL());
        }
        return "home";
    }

    @GetMapping("/notFound")
    public String notFoundPage() {
        return "n";
    }

    @GetMapping("/{shortenURL}")
    public RedirectView getPage(@PathVariable("shortenURL") String shortenURL) {
        return urlService.getPageFromShortURL(shortenURL);
    }

    @PostMapping("/save")
    public String saveURL(@ModelAttribute URL url) {
        report = true;
        urlService.createShortenURL(url);
        return "redirect:/home";
    }
}
