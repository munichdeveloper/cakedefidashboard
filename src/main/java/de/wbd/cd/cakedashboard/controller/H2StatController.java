package de.wbd.cd.cakedashboard.controller;

import de.wbd.cd.cakedashboard.repo.H2LMRewardPr;
import de.wbd.cd.cakedashboard.service.H2StatService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stat")
@Profile("h2")
public class H2StatController {
    @Autowired
    private H2StatService statService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/lmrewardspda")
    public List<LMRewardDTO> getLMRewardsPerDayAndAsset() {
        return statService.getLMRewardsPerDayAndAsset().stream().map(this::map).sorted(Comparator.comparing(LMRewardDTO::getDate)).collect(Collectors.toList());
    }

    private LMRewardDTO map(H2LMRewardPr lmRewardPr) {
        LMRewardDTO map = modelMapper.map(lmRewardPr, LMRewardDTO.class);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy");
        map.setDate(LocalDate.parse(lmRewardPr.getDate(), formatter));
        return map;
    }

}
