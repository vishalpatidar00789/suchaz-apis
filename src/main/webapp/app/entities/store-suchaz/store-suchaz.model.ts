import { BaseEntity } from './../../shared';

export const enum Status {
    'ACTIVE',
    'INACTIVE'
}

export class StoreSuchaz implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public description?: string,
        public storeCode?: string,
        public status?: Status,
        public createdDate?: number,
        public lastUpdatedDate?: number,
        public createdBy?: string,
        public lastUpdatedBy?: string,
        public storeImages?: BaseEntity[],
        public items?: BaseEntity[],
    ) {
    }
}
