import FrameworkCapsuleDto from '../frameworkCapsuleDto';
import RiyoushaComradeDto from './riyoushaComradeDto';


export default interface SaveRiyoushaComradeCapsuleInterface {
}


/**
 * APIユーザ格納Dto
 */
export default class SaveRiyoushaComradeCapsuleDto extends FrameworkCapsuleDto
  implements SaveRiyoushaComradeCapsuleInterface {
  /**
   * 利用者API接続Dto
   */
  riyoushaComradeDto: RiyoushaComradeDto;

  constructor() {
    super();
    this.riyoushaComradeDto = new RiyoushaComradeDto;
  }
}