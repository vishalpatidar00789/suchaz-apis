import { BaseEntity } from './../../shared';

export const enum Status {
    'ACTIVE',
    'INACTIVE'
}

export class StoreImageSuchaz implements BaseEntity {
    constructor(
        public id?: number,
        public storeImageName?: string,
        public storeImageDesc?: string,
        public storeImageSize?: number,
        public storeImageContentType?: string,
        public storeImage?: any,
        public storeImageType?: string,
        public status?: Status,
        public createdDate?: number,
        public lastUpdatedDate?: number,
        public createdBy?: string,
        public lastUpdatedBy?: string,
        public storeId?: number,
    ) {
    }
}
