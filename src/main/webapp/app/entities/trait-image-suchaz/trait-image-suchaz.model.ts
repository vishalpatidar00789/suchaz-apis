import { BaseEntity } from './../../shared';

export const enum Status {
    'ACTIVE',
    'INACTIVE'
}

export class TraitImageSuchaz implements BaseEntity {
    constructor(
        public id?: number,
        public personalityImageName?: string,
        public personalityImageDesc?: string,
        public personalityImageSize?: number,
        public personalityImageContentType?: string,
        public personalityImage?: any,
        public personalityImageType?: string,
        public status?: Status,
        public createdDate?: number,
        public lastUpdatedDate?: number,
        public createdBy?: string,
        public lastUpdatedBy?: string,
        public traitId?: number,
    ) {
    }
}
