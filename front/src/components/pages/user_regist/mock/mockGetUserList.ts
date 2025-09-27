import type UserPersonInterface from "../../../../entity/userPersonEntity";
import UserPersonEntity from "../../../../entity/userPersonEntity";

export default function mockGetUserList(): UserPersonInterface[] {

    const list: UserPersonInterface[] = [];

    list.push(createEntity(1));
    list.push(createEntity(2));
    list.push(createEntity(3));
    list.push(createEntity(4));

    return list;
}

function createEntity(index: number): UserPersonInterface {

    const entity: UserPersonInterface = new UserPersonEntity();

    entity.userPersonId = 200 + index;
    entity.userPersonCode = 180 + index;
    entity.userPersonName = "ユーザ　花子" + index;

    return entity;
}