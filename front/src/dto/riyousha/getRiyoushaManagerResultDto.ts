import FrameworkMessageAndResultDto from '../frameworkMessageAndResultDto';
import RiyoushaManagerDto from './riyoushaManagerDto';


export default interface GetRiyoushaManagerResultInterface {

}

/**
 * 利用者運営者格納Dto
 */
export default class GetRiyoushaManagerResultDto extends FrameworkMessageAndResultDto
  implements GetRiyoushaManagerResultInterface {
  /**
   * 利用者運営者Dto
   */
  riyoushaManagerDto: RiyoushaManagerDto;

  constructor() {
    super();
    this.riyoushaManagerDto = new RiyoushaManagerDto;
  }
}

