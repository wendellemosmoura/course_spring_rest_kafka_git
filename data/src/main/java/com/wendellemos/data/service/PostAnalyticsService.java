package com.wendellemos.data.service;

import com.wendellemos.data.dto.CarPostDTO;
import org.springframework.stereotype.Service;

@Service
public interface PostAnalyticsService {

    void saveDataAnalytics(CarPostDTO carPostDTO);
}
