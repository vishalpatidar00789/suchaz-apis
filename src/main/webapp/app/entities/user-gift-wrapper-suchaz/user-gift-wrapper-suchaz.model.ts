import { BaseEntity } from './../../shared';

export const enum Status {
    'ACTIVE',
    'INACTIVE'
}

export class UserGiftWrapperSuchaz implements BaseEntity {
    constructor(
        public id?: number,
        public giftWrapperImgContentType?: string,
        public giftWrapperImg?: any,
        public status?: Status,
        public createdDate?: number,
        public lastUpdatedDate?: number,
        public createdBy?: string,
        public lastUpdatedBy?: string,
        public suchAzUserId?: number,
        public itemId?: number,
        public giftWrapperId?: number,
    ) {
    }
}
