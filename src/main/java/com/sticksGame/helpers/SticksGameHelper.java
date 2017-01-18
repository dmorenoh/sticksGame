package com.sticksGame.helpers;

import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dmorenoh on 8/1/17.
 */
@Component
public class SticksGameHelper {
    public String generateGetStaticsUrl(String userId, RequestAttributes requestAttributes){
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        String currentUrl = request.getRequestURL().toString();
        String getStatisticsUrl = currentUrl.replaceAll("poststatistics","getStatistics/") + userId;
        return getStatisticsUrl;
    }
}
