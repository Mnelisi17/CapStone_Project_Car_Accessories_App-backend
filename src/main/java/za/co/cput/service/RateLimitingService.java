package za.co.cput.service;

// import io.github.bucket4j.Bandwidth;
// import io.github.bucket4j.Bucket;
// import io.github.bucket4j.Bucket4j;
// import io.github.bucket4j.Refill;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimitingService {

    // private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    private static final long DEFAULT_CAPACITY = 100;
    private static final long DEFAULT_REFILL_TOKENS = 100;
    private static final Duration DEFAULT_REFILL_PERIOD = Duration.ofHours(1);

    private static final long AUTH_CAPACITY = 10;
    private static final long AUTH_REFILL_TOKENS = 10;
    private static final Duration AUTH_REFILL_PERIOD = Duration.ofMinutes(15);

    private static final long PAYMENT_CAPACITY = 20;
    private static final long PAYMENT_REFILL_TOKENS = 20;
    private static final Duration PAYMENT_REFILL_PERIOD = Duration.ofMinutes(30);

    // public Bucket createBucket(String key, RateLimitType type) {
    //     return buckets.computeIfAbsent(key, k -> {
    //         switch (type) {
    //             case AUTHENTICATION:
    //                 return createAuthBucket();
    //             case PAYMENT:
    //                 return createPaymentBucket();
    //             case API:
    //                 return createApiBucket();
    //             default:
    //                 return createDefaultBucket();
    //         }
    //     });
    // }

    // public boolean allowRequest(String key, RateLimitType type) {
    //     Bucket bucket = createBucket(key, type);
    //     return bucket.tryConsume(1);
    // }

    // public boolean allowRequest(String key, RateLimitType type, long tokens) {
    //     Bucket bucket = createBucket(key, type);
    //     return bucket.tryConsume(tokens);
    // }

    // public long getAvailableTokens(String key, RateLimitType type) {
    //     Bucket bucket = createBucket(key, type);
    //     return bucket.getAvailableTokens();
    // }

    // public void clearBucket(String key) {
    //     buckets.remove(key);
    // }

    // public void clearAllBuckets() {
    //     buckets.clear();
    // }

    // private Bucket createDefaultBucket() {
    //     Bandwidth limit = Bandwidth.classic(DEFAULT_CAPACITY,
    //             Refill.intervally(DEFAULT_REFILL_TOKENS, DEFAULT_REFILL_PERIOD));
    //     return Bucket4j.builder().addLimit(limit).build();
    // }

    // private Bucket createAuthBucket() {
    //     Bandwidth limit = Bandwidth.classic(AUTH_CAPACITY,
    //             Refill.intervally(AUTH_REFILL_TOKENS, AUTH_REFILL_PERIOD));
    //     return Bucket4j.builder().addLimit(limit).build();
    // }

    // private Bucket createPaymentBucket() {
    //     Bandwidth limit = Bandwidth.classic(PAYMENT_CAPACITY,
    //             Refill.intervally(PAYMENT_REFILL_TOKENS, PAYMENT_REFILL_PERIOD));
    //     return Bucket4j.builder().addLimit(limit).build();
    // }

    // private Bucket createApiBucket() {
    //     Bandwidth limit = Bandwidth.classic(1000,
    //             Refill.intervally(1000, Duration.ofHours(1)));
    //     return Bucket4j.builder().addLimit(limit).build();
    // }

    public enum RateLimitType {
        DEFAULT,
        AUTHENTICATION,
        PAYMENT,
        API
    }
}