package mitei.mitei.political.balancesheet.manage.kanrensha.logic;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressPostalRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.util.WriteLogService;

/**
 * 住居反映状況を
 */
@Component
public class CheckRsdtLogic {

    /** Logger */
    private final Logger log = LoggerFactory.getLogger(WriteLogService.class);

    /** 郵便番号Repository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    /**
     * 処理を行う
     */
    @SuppressWarnings("unchecked")
    public void practice() {

        List<AddressPostalEntity> list = addressPostalRepository.findByLgCodeStartingWith("01");

        for (AddressPostalEntity entity : list) {

            Query query = entityManager.createNativeQuery("SELECT count(*) FROM address_rsdt_" + entity.getLgCode()
                    + " WHERE address_block LIKE '" + entity.getAddressName() + "%'", Integer.class);

            List<Integer> listCount = query.getResultList();

            log.info(entity.getLgCode() + ":::" + entity.getAddressName() + "--件数" + listCount.get(0));

        }

    }
}
