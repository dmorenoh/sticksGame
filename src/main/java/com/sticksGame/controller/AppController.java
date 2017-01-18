package com.sticksGame.controller;

import com.sticksGame.model.UserStatistic;
import com.sticksGame.service.StatisticsService;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookLink;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

/**
 * Created by dmorenoh on 8/1/17.
 */
@Controller
public class AppController {
    private Facebook facebook;
    private ConnectionRepository connectionRepository;

    @Inject
    private StatisticsService statisticsService;

    @Inject
    public AppController(Facebook facebook, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
    }

    //@RequestMapping(value = "/", method = RequestMethod.GET)
    @GetMapping("/")
    public String login(Model model) {
        Connection<Facebook> primaryConnection = connectionRepository.findPrimaryConnection(Facebook.class);
        if (primaryConnection == null) {
            return "redirect:/connect/facebook";
        }

        User userProfile = facebook.userOperations().getUserProfile();
        UserStatistic userStatistics = statisticsService.getUserStatistics(userProfile.getId());
        model.addAttribute("facebookProfile", userProfile);
        if (userStatistics == null) {
            return "notExistingUser";
        }

        model.addAttribute("userStatistic", userStatistics);
        return "userStatistics";
    }

    @PostMapping("/postWonGames")
    public String postWonGames(Model model){
        Connection<Facebook> primaryConnection = connectionRepository.findPrimaryConnection(Facebook.class);
        if (primaryConnection == null) {
            return "redirect:/connect/facebook";
        }
        User userProfile = facebook.userOperations().getUserProfile();
        UserStatistic userStatistics = statisticsService.getUserStatistics(userProfile.getId());
        FacebookLink link = new FacebookLink("http://localhost:8080/index.html",
                null,
                null,
                null);
        facebook.feedOperations().postLink("I've just played sticks game and I have " + userStatistics.getGamesWon() + "won", link);
        model.addAttribute("facebookProfile", userProfile);
        model.addAttribute("userStatistic", userStatistics);
        return "userStatistics";
    }
}
