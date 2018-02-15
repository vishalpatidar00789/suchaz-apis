import { BaseEntity } from './../../shared';

export const enum Status {
    'ACTIVE',
    'INACTIVE'
}

export class VendorSuchaz implements BaseEntity {
    constructor(
        public id?: number,
        public vendorName?: string,
        public rateLimit?: number,
        public startDate?: number,
        public endDate?: number,
        public accessKey?: string,
        public secretKey?: string,
        public associateKey?: string,
        public affiliteId?: string,
        public accessKeyExpDate?: number,
        public scretKeyExpDate?: number,
        public status?: Status,
        public createdDate?: number,
        public lastUpdatedDate?: number,
        public createdBy?: string,
        public lastUpdatedBy?: string,
        public items?: BaseEntity[],
        public vendorImages?: BaseEntity[],
        public countryId?: number,
    ) {
    }
}
