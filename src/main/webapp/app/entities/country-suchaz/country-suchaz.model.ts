import { BaseEntity } from './../../shared';

export const enum Status {
    'ACTIVE',
    'INACTIVE'
}

export class CountrySuchaz implements BaseEntity {
    constructor(
        public id?: number,
        public countryName?: string,
        public countryCode?: string,
        public status?: Status,
        public createdDate?: number,
        public lastUpdatedDate?: number,
        public createdBy?: string,
        public lastUpdatedBy?: string,
        public vendors?: BaseEntity[],
    ) {
    }
}
