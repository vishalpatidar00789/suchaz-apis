import { BaseEntity } from './../../shared';

export const enum Status {
    'ACTIVE',
    'INACTIVE'
}

export class CategoryImageSuchaz implements BaseEntity {
    constructor(
        public id?: number,
        public categoryImageName?: string,
        public categoryImageDesc?: string,
        public categoryImageSize?: number,
        public categoryImageContentType?: string,
        public categoryImage?: any,
        public categoryImageType?: string,
        public status?: Status,
        public createdDate?: number,
        public lastUpdatedDate?: number,
        public createdBy?: string,
        public lastUpdatedBy?: string,
        public categoryId?: number,
    ) {
    }
}
