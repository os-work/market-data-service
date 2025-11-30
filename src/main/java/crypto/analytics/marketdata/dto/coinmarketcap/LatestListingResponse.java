package crypto.analytics.marketdata.dto.coinmarketcap;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public record LatestListingResponse(
        Status status,
        List<CryptoData> data
) {

    public record Status(
            @JsonProperty("timestamp") String timestamp,
            @JsonProperty("error_code") int errorCode,
            @JsonProperty("error_message") String errorMessage,
            @JsonProperty("elapsed") int elapsed,
            @JsonProperty("credit_count") int creditCount,
            @JsonProperty("notice") String notice
    ) {}

    public record CryptoData(
            @JsonProperty("id") long id,
            @JsonProperty("name") String name,
            @JsonProperty("symbol") String symbol,
            @JsonProperty("slug") String slug,
            @JsonProperty("cmc_rank") int cmcRank,
            @JsonProperty("num_market_pairs") int numMarketPairs,
            @JsonProperty("circulating_supply") double circulatingSupply,
            @JsonProperty("total_supply") double totalSupply,
            @JsonProperty("max_supply") Double maxSupply,
            @JsonProperty("infinite_supply") boolean infiniteSupply,
            @JsonProperty("last_updated") String lastUpdated,
            @JsonProperty("date_added") String dateAdded,
            @JsonProperty("tags") List<String> tags,
            @JsonProperty("platform") Platform platform,
            @JsonProperty("self_reported_circulating_supply") Double selfReportedCirculatingSupply,
            @JsonProperty("self_reported_market_cap") Double selfReportedMarketCap,
            @JsonProperty("tvl_ratio") Double tvlRatio,
            @JsonProperty("quote") Map<String, Quote> quote
    ) {}

    public record Platform(
            @JsonProperty("id") long id,
            @JsonProperty("name") String name,
            @JsonProperty("symbol") String symbol,
            @JsonProperty("slug") String slug,
            @JsonProperty("token_address") String tokenAddress
    ) {}

    public record Quote(
            @JsonProperty("price") double price,
            @JsonProperty("volume_24h") double volume24h,
            @JsonProperty("volume_change_24h") double volumeChange24h,
            @JsonProperty("percent_change_1h") double percentChange1h,
            @JsonProperty("percent_change_24h") double percentChange24h,
            @JsonProperty("percent_change_7d") double percentChange7d,
            @JsonProperty("percent_change_30d") Double percentChange30d,
            @JsonProperty("percent_change_60d") Double percentChange60d,
            @JsonProperty("percent_change_90d") Double percentChange90d,
            @JsonProperty("market_cap") double marketCap,
            @JsonProperty("market_cap_dominance") double marketCapDominance,
            @JsonProperty("fully_diluted_market_cap") double fullyDilutedMarketCap,
            @JsonProperty("last_updated") String lastUpdated
    ) {}
}

