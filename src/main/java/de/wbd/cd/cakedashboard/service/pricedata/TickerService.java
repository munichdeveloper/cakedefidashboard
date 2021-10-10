package de.wbd.cd.cakedashboard.service.pricedata;

import de.wbd.cd.cakedashboard.entity.PriceData;
import de.wbd.cd.cakedashboard.repo.PriceDataRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Profile("ticker")
public class TickerService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PriceDataRepository priceDataRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${nomics.api.apiKey}")
    private String apiKey;

    @Value("${nomics.api.apiEndpoint}")
    private String apiEndpoint;

    @Scheduled(cron = "0 0 */1 * * ?")
    public void fetchPrices() {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiEndpoint)
                .queryParam("key", apiKey)
                .queryParam("ids", "BTC,ETH,LTC,DFI,CAKE,ADA,SOL,ATOM,DOT,BNB,AVAX,LINK,BCH,HNT")
                .queryParam("convert", "EUR")
                .queryParam("per-page", "100");

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<List<PriceDataJson>> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                });

        List<PriceData> collect = Objects.requireNonNull(response.getBody()).stream().map(this::map).collect(Collectors.toList());
        priceDataRepository.saveAll(collect);
    }

    private PriceData map(PriceDataJson priceDataJson) {
        return modelMapper.map(priceDataJson, PriceData.class);
    }

}
