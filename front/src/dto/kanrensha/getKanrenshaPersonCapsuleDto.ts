import type MasterPersonInterface from "../../entity/masterPersonEntity";
import MasterPersonEntity from "../../entity/masterPersonEntity";

export default interface GetKanrenshaPersonCapsuleInterface{
    
}

export default class GetKanrenshaPersonCapsuleDto implements GetKanrenshaPersonCapsuleInterface{

        /** 関連者個人マスタEntity */
    masterPersonEntity:MasterPersonInterface;


    constructor(){
        this.masterPersonEntity = new  MasterPersonEntity();       
    }
}