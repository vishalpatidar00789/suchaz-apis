import { BaseEntity } from './../../shared';

export class ItemReviewSuchaz implements BaseEntity {
    constructor(
        public id?: number,
        public ratingValue?: string,
        public ratingComment?: string,
        public itemId?: number,
    ) {
    }
}
