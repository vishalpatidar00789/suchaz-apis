import { BaseEntity } from './../../shared';

export const enum Status {
    'ACTIVE',
    'INACTIVE'
}

export class OccasionImageSuchaz implements BaseEntity {
    constructor(
        public id?: number,
        public occasionImageName?: string,
        public occasionImageDesc?: string,
        public occasionImageSize?: number,
        public occasionImageContentType?: string,
        public occasionImage?: any,
        public occasionImageType?: string,
        public status?: Status,
        public createdDate?: number,
        public lastUpdatedDate?: number,
        public createdBy?: string,
        public lastUpdatedBy?: string,
        public occasionId?: number,
    ) {
    }
}
