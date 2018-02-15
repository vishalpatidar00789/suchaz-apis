import { BaseEntity } from './../../shared';

export class ItemAttributeTypeSuchaz implements BaseEntity {
    constructor(
        public id?: number,
        public attributeTypeName?: string,
        public itemCommonAttributes?: BaseEntity[],
        public categoryId?: number,
    ) {
    }
}
