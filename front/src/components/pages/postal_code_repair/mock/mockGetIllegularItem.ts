import type PostalIrregularItemInterface from "../../../../dto/postal/postalIrregularItemDto";
import PostalIrregularItemDto from "../../../../dto/postal/postalIrregularItemDto";
import type SearchPostalIllegularResultInterface from "../../../../dto/postal/searchPostalIllegularResultDto";
import SearchPostalIllegularResultDto from "../../../../dto/postal/searchPostalIllegularResultDto";



export default function mockGetIllegularItem(): SearchPostalIllegularResultInterface {

    const dto:SearchPostalIllegularResultInterface = new SearchPostalIllegularResultDto();

    const list: PostalIrregularItemInterface[] = [];

    list.push(createDto(1));
    list.push(createDto(2));
    list.push(createDto(3));
    list.push(createDto(4));
    list.push(createDto(5));

    dto.listItem = list;
    dto.allCount =249;
    dto.limit = 30;
    dto.pageNumber =0;

    return dto;
}

function createDto(index: number) {

    const dto: PostalIrregularItemInterface = new PostalIrregularItemDto();

    dto.addressPostalIrregularId = 200 + index;
    dto.postal1 = "797979" + index;
    dto.lgCode = "1231231";
    dto.addressName = (index + 2) + "角形ビルディング";

    return dto;
}