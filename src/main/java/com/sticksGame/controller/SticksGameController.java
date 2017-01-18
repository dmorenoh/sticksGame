package com.sticksGame.controller;

import com.sticksGame.helpers.SticksGameHelper;
import com.sticksGame.model.GlobalStatistics;
import com.sticksGame.model.UserStatistic;
import com.sticksGame.model.request.StatisticRequest;
import com.sticksGame.model.response.StatisticPost;
import com.sticksGame.service.StatisticsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookLink;
import org.springframework.social.facebook.api.PostData;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import javax.inject.Inject;

/**
 * Created by dmorenoh on 8/1/17.
 */
@Controller
@RequestMapping("/sticksGame")
public class SticksGameController {
    @Inject
    private Facebook facebook;

    @Inject
    private ConnectionRepository connectionRepository;

    @Inject
    private StatisticsService statisticsService;

    @Inject
    private SticksGameHelper sticksGameHelper;

    @RequestMapping(value = "/getStatistics/{userId}", method = RequestMethod.GET)
    public ResponseEntity<UserStatistic> getUserStatistics(@PathVariable String userId) {
        UserStatistic userStatistic = statisticsService.getUserStatistics(userId);
        return new ResponseEntity<>(userStatistic, HttpStatus.OK);
    }

    @RequestMapping(value = "/saveStatistics", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    ResponseEntity<UserStatistic> updateUserStatistic(@RequestBody StatisticRequest statisticRequest) {
        UserStatistic userStatistic = updateUserStatistics(statisticRequest);
        return new ResponseEntity<>(userStatistic, HttpStatus.OK);
    }

    private UserStatistic updateUserStatistics(@RequestBody StatisticRequest statisticRequest) {
        User userProfile = facebook.userOperations().getUserProfile();
        return statisticsService.saveUserStatistics(userProfile.getId(), statisticRequest);
    }

    @PostMapping("/saveStatistics")
    public String updateUserStatistic(Model model, @ModelAttribute StatisticRequest statisticRequest) {
        UserStatistic userStatistic = updateUserStatistics(statisticRequest);
        model.addAttribute("userStatistic", userStatistic);
        return "userStatistics";
    }

    @RequestMapping(value = "/getGlobalStatistics", method = RequestMethod.GET)
    public ResponseEntity<GlobalStatistics> getGlobalStatistics() {
        GlobalStatistics globalStatistics = statisticsService.getGlobalStatistics();
        return new ResponseEntity<>(globalStatistics, HttpStatus.OK);
    }

    @RequestMapping(value = "/poststatistics", method = RequestMethod.POST)
    public ResponseEntity<StatisticPost> postStatistics() {

        User userProfile = facebook.userOperations().getUserProfile();
        UserStatistic userStatistics = statisticsService.getUserStatistics(userProfile.getId());
        String getStatisticsUrl = sticksGameHelper.generateGetStaticsUrl(
                userStatistics.getUserId(),
                RequestContextHolder.currentRequestAttributes());

        FacebookLink link = new FacebookLink("http://localhost:8080",
                null,
                null,
                null);
        facebook.feedOperations().postLink("I've just played sticks game and I have " + userStatistics.getGamesWon(), link);
        StatisticPost statisticPost = new StatisticPost(getStatisticsUrl);
        return new ResponseEntity<>(statisticPost, HttpStatus.OK);
    }
}
