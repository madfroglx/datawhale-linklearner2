package com.linklearner.home.mapper;

import com.linklearner.home.entity.Banner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BannerMapperTest {
    @Autowired
    BannerMapper bannerMapper;

    @Test
    void findFiveAvailableBanner() {
    }
}