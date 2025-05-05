package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressRsdtTemplateEntity;

/**
 * アドレス・ベース・レジストリ住居テンプレートRepository
 */
public interface AddressRsdtTemplateRepository extends JpaRepository<AddressRsdtTemplateEntity, Integer> {

}
