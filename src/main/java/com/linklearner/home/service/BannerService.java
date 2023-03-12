package com.linklearner.home.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linklearner.home.entity.Banner;
import com.linklearner.home.mapper.BannerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class BannerService {
    @Autowired
    BannerMapper bannerMapper;

    public List<Map<String, String>> findFiveAvailableBanner() {
        List<Banner> banners = bannerMapper.selectList(new QueryWrapper<Banner>().eq("status", 10).last("limit 0,5"));
        List<Map<String, String>> result = new ArrayList<>();
        banners.stream().map(banner -> Map.of(
                "url", banner.getImageUrl(),
                "description", banner.getDescription(),
                "linkUrl", banner.getLinkUrl()
        )).forEach(result::add);
        return result;
    }
}
