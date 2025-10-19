import FrameworkCapsuleDto from '../frameworkCapsuleDto';
import RiyoushaAdminEntity from '../../entity/riyoushaAdminEntity';


export default interface GetRiyoushaAdminCapsuleInterface {

}


/**
 * 関連者企業団体格納Dto
 */
export default class GetRiyoushaAdminCapsuleDto extends FrameworkCapsuleDto
  implements GetRiyoushaAdminCapsuleInterface {
  /**
   * 利用者管理者Entity
   */
  riyoushaAdminEntity: RiyoushaAdminEntity;

  constructor() {
    super();
    this.riyoushaAdminEntity = new RiyoushaAdminEntity();
  }
}