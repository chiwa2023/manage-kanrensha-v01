package mitei.mitei.political.balancesheet.manage.kanrensha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Main
 */
@SpringBootApplication
public class BackApplication { // NOPMD

    /**
     * 全体起動処理を行う
     *
     * @param args 引数
     */
    public static void main(final String[] args) {
        SpringApplication.run(BackApplication.class, args);
    }

}
