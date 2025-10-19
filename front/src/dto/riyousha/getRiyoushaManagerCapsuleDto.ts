import FrameworkCapsuleDto from '../frameworkCapsuleDto';
import RiyoushaManagerEntity from '../../entity/riyoushaManagerEntity';

export default interface GetRiyoushaManagerCapsuleInterface {

}



/**
 * 利用者運営者格納Dto
 */
export default class GetRiyoushaManagerCapsuleDto extends FrameworkCapsuleDto
  implements GetRiyoushaManagerCapsuleInterface {
  /**
   * 利用者運営者Entity
   */
  riyoushaManagerEntity: RiyoushaManagerEntity;

  constructor() {
    super();
    this.riyoushaManagerEntity = new RiyoushaManagerEntity;
  }
}