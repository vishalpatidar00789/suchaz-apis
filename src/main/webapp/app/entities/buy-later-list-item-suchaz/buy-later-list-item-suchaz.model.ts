import { BaseEntity } from './../../shared';

export const enum Status {
    'ACTIVE',
    'INACTIVE'
}

export class BuyLaterListItemSuchaz implements BaseEntity {
    constructor(
        public id?: number,
        public status?: Status,
        public createdDate?: number,
        public lastUpdatedDate?: number,
        public createdBy?: string,
        public lastUpdatedBy?: string,
        public buyLaterListId?: number,
        public itemId?: number,
    ) {
    }
}
