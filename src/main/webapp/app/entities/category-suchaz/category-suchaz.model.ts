import { BaseEntity } from './../../shared';

export const enum Status {
    'ACTIVE',
    'INACTIVE'
}

export class CategorySuchaz implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public description?: string,
        public categoryCode?: string,
        public status?: Status,
        public createdDate?: number,
        public lastUpdatedDate?: number,
        public createdBy?: string,
        public lastUpdatedBy?: string,
        public items?: BaseEntity[],
        public children?: BaseEntity[],
        public itemAttributeTypes?: BaseEntity[],
        public categoryImages?: BaseEntity[],
        public parentId?: number,
    ) {
    }
}
