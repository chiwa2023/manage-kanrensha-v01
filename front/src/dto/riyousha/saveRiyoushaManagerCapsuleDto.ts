import FrameworkCapsuleDto from '../frameworkCapsuleDto';
import RiyoushaManagerDto from './riyoushaManagerDto';

export default interface SaveRiyoushaManagerCapsuleInterface {
}

/**
 * 運営者ユーザー格納Dto
 */
export default class SaveRiyoushaManagerCapsuleDto extends FrameworkCapsuleDto
  implements SaveRiyoushaManagerCapsuleInterface {

  /** 利用者運営者Dto */
  riyoushaManagerDto: RiyoushaManagerDto;

  constructor() {
    super();
    this.riyoushaManagerDto = new RiyoushaManagerDto;
  }
}