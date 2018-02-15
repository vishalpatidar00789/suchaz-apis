import { BaseEntity } from './../../shared';

export const enum Status {
    'ACTIVE',
    'INACTIVE'
}

export class HobbyImageSuchaz implements BaseEntity {
    constructor(
        public id?: number,
        public hobbyImageName?: string,
        public hobbyImageDesc?: string,
        public hobbyImageSize?: number,
        public hobbyImageContentType?: string,
        public hobbyImage?: any,
        public hobbyImageType?: string,
        public status?: Status,
        public createdDate?: number,
        public lastUpdatedDate?: number,
        public createdBy?: string,
        public lastUpdatedBy?: string,
        public hobbyId?: number,
    ) {
    }
}
