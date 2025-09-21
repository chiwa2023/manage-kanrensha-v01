import FrameworkMessageAndResultDto from '../frameworkMessageAndResultDto';
import RiyoushaAdminDto from './riyoushaAdminDto';


export default interface GetRiyoushaAdminResultInterface {
}


/**
 * 利用者管理者格納Dto
 */
export default class GetRiyoushaAdminResultDto extends FrameworkMessageAndResultDto
  implements GetRiyoushaAdminResultInterface {
  /**
   * 利用者管理者Dto
   */
  riyoushaAdminDto: RiyoushaAdminDto;

  constructor() {
    super();
    this.riyoushaAdminDto = new RiyoushaAdminDto;
  }
}