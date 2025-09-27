import FrameworkCapsuleDto from '../frameworkCapsuleDto';
import RiyoushaComradeEntity from '../../entity/riyoushaComradeEntity';

export default interface GetRiyoushaComradeCapsuleInterface extends FrameworkCapsuleDto {

}

/**
 * 利用者API接続者格納Dto
 */
export default class GetRiyoushaComradeCapsuleDto extends FrameworkCapsuleDto
  implements GetRiyoushaComradeCapsuleInterface {
  /**
   * 利用者API接続者Entity
   */
  riyoushaComradeEntity: RiyoushaComradeEntity;

  constructor() {
    super();
    this.riyoushaComradeEntity = new RiyoushaComradeEntity;
  }
}