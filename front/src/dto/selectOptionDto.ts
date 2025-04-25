/**
 * HTMLにおける、Selectボタンの選択(option)項目データInterface
 */
export default interface SelectOptionInterface {
    value: string;
    text: string;

// eslint-disable-next-line semi
}

/**
 * HTMLにおける、Selectボタンの選択(option)項目データDto
 */
export default class SelectOptionDto implements SelectOptionInterface{

    /**
     * option項目の値
     */
    value: string;

    /**
     * option項目の表示内容
     */
    text: string;

    /**
     * Creates an instance of SelectOptionDto.
     */
    constructor() {
        this.value = "";
        this.text = "";
    }
}
