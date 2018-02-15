import { BaseEntity } from './../../shared';

export class OfferSuchaz implements BaseEntity {
    constructor(
        public id?: number,
        public offerName?: string,
        public offerDesc?: string,
        public offerCode?: string,
        public offerTC?: any,
        public items?: BaseEntity[],
    ) {
    }
}
