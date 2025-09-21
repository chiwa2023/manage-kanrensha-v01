import FrameworkMessageAndResultDto from '../frameworkMessageAndResultDto';
import RiyoushaComradeDto from './riyoushaComradeDto';

export default interface GetRiyoushaComradeResultInterface {

}


/**
 * 利用者API接続者格納Dto
 */
export default class GetRiyoushaComradeResultDto extends FrameworkMessageAndResultDto
  implements GetRiyoushaComradeResultInterface {
  /**
   * 利用者API接続者Dto
   */
  riyoushaComradeDto: RiyoushaComradeDto;

  constructor() {
    super();
    this.riyoushaComradeDto = new RiyoushaComradeDto;
  }
}