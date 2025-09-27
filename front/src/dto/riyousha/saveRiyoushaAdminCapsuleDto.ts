import FrameworkCapsuleDto from '../frameworkCapsuleDto';
import RiyoushaAdminDto from './riyoushaAdminDto';

export default interface SaveRiyoushaAdminCapsuleInterface {
}
/**
 * 運営者ユーザー格納Dto
 */
export default class SaveRiyoushaAdminCapsuleDto extends FrameworkCapsuleDto
  implements SaveRiyoushaAdminCapsuleInterface {
  /**
   * 利用者運営者Dto
   */
  riyoushaAdminDto: RiyoushaAdminDto;

  constructor() {
    super();
    this.riyoushaAdminDto = new RiyoushaAdminDto;
  }
}