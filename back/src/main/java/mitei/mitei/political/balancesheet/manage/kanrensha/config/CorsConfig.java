package mitei.mitei.political.balancesheet.manage.kanrensha.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * クロスサイトアクセス設定
 * 
 */
@Configuration
public class CorsConfig {

    /** サイトをまたいでアクセスを許可するURL(自front側) */
    private static final String ALLOW_URL_CREATE = "http://localhost:5173";

    /**
     * クロスサイトアクセスフィルタ
     *
     * @return 許可リストが挿入されたフィルタ
     */
    @Bean
    protected CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList(ALLOW_URL_CREATE));
        config.setAllowCredentials(true);
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.addExposedHeader("Set-Cookie");

        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        return new CorsFilter(configSource);
    }
}
