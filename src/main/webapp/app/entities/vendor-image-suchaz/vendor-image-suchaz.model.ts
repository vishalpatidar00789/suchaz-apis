import { BaseEntity } from './../../shared';

export const enum Status {
    'ACTIVE',
    'INACTIVE'
}

export class VendorImageSuchaz implements BaseEntity {
    constructor(
        public id?: number,
        public vendorImageName?: string,
        public vendorImageDesc?: string,
        public vendorImageSize?: number,
        public vendorImageContentType?: string,
        public vendorImage?: any,
        public vendorImageType?: string,
        public status?: Status,
        public createdDate?: number,
        public lastUpdatedDate?: number,
        public createdBy?: string,
        public lastUpdatedBy?: string,
        public vendorId?: number,
    ) {
    }
}
