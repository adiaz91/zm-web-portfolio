package com.portfolio.services;


import java.util.List;

public interface TwitterService {
    List<String> getUserTimeLine(String user);
}
