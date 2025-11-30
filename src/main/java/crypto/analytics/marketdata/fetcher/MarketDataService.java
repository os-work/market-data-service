package crypto.analytics.marketdata.fetcher;

import crypto.analytics.marketdata.dto.coinmarketcap.LatestListingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class MarketDataService {

    @Value("${app.coinmarketcap.endpoint-crypto-listings}")
    private String endpoint;

    private final WebClient webClient;
    private final KafkaTemplate<String, LatestListingResponse> kafka;

    @Autowired
    public MarketDataService(WebClient webClient, KafkaTemplate<String, LatestListingResponse> kafka) {
        this.webClient = webClient;
        this.kafka = kafka;
    }

    @Scheduled(fixedDelayString = "${app.marketdata.poll-interval-ms}")
    public void fetchAndPublish() {
        // Call CoinMarketCap API for latest prices (e.g. vs USD)
        LatestListingResponse price = webClient.get()
                .uri(uriBuilder -> uriBuilder.path(endpoint)
                        .queryParam("start", "1")
                        .queryParam("limit", "3")
                        .queryParam("convert", "USD")
                        .build())
                .retrieve()
                .bodyToMono(LatestListingResponse.class).block();
        // TODO Publish to Kafka topic "crypto-prices"
        // kafka.send("crypto-prices", price);
    }
}
