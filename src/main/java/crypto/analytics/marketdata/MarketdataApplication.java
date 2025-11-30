package crypto.analytics.marketdata;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableScheduling
public class MarketdataApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketdataApplication.class, args);
	}

    @Bean
    public WebClient coinMarketCapWebClient(
            @Value("${app.coinmarketcap.base-url}") String baseUrl,
            @Value("${app.coinmarketcap.header-name}") String header,
            @Value("${app.coinmarketcap.api-key}") String apiKey) {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(header, apiKey)
                .defaultHeader(HttpHeaders.ACCEPT, "application/json")
                .build();
    }

}
