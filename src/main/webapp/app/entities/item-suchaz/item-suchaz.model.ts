import { BaseEntity } from './../../shared';

export const enum Status {
    'ACTIVE',
    'INACTIVE'
}

export class ItemSuchaz implements BaseEntity {
    constructor(
        public id?: number,
        public itemId?: string,
        public title?: string,
        public vendorItemType?: string,
        public description?: string,
        public vendorItemCode?: string,
        public bestPrice?: number,
        public sellingPrice?: number,
        public customerAvgRating?: string,
        public suchazAvgRating?: string,
        public status?: Status,
        public itemURL?: string,
        public brand?: string,
        public colors?: string,
        public availibity?: Status,
        public lastRefreshed?: number,
        public searchKeywords?: string,
        public createdDate?: number,
        public lastUpdatedDate?: number,
        public createdBy?: string,
        public lastUpdatedBy?: string,
        public isFeatured?: boolean,
        public lastFeaturedUPDDate?: number,
        public wishListItems?: BaseEntity[],
        public buyLaterListItems?: BaseEntity[],
        public activityListItems?: BaseEntity[],
        public itemCommonAttributes?: BaseEntity[],
        public itemReviews?: BaseEntity[],
        public userGiftWrappers?: BaseEntity[],
        public itemImages?: BaseEntity[],
        public offers?: BaseEntity[],
        public categoryId?: number,
        public vendorId?: number,
        public stores?: BaseEntity[],
    ) {
        this.isFeatured = false;
    }
}
