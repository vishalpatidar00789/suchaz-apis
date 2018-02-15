import { BaseEntity } from './../../shared';

export class ItemCommonAttributeSuchaz implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public value?: string,
        public itemId?: number,
        public itemAttributeTypeId?: number,
    ) {
    }
}
