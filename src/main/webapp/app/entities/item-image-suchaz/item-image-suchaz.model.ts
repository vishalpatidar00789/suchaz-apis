import { BaseEntity } from './../../shared';

export const enum Status {
    'ACTIVE',
    'INACTIVE'
}

export class ItemImageSuchaz implements BaseEntity {
    constructor(
        public id?: number,
        public itemImageName?: string,
        public itemImageDesc?: string,
        public itemImageSize?: number,
        public itemImageURL?: string,
        public itemImageContentType?: string,
        public itemImage?: any,
        public itemType?: string,
        public lastRefreshedDate?: number,
        public status?: Status,
        public createdDate?: number,
        public lastUpdatedDate?: number,
        public createdBy?: string,
        public lastUpdatedBy?: string,
        public itemId?: number,
    ) {
    }
}
