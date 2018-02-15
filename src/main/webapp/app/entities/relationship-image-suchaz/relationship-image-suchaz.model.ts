import { BaseEntity } from './../../shared';

export const enum Status {
    'ACTIVE',
    'INACTIVE'
}

export class RelationshipImageSuchaz implements BaseEntity {
    constructor(
        public id?: number,
        public relationshipImageName?: string,
        public relationshipImageDesc?: string,
        public relationshipImageSize?: number,
        public relationshipImageContentType?: string,
        public relationshipImage?: any,
        public relationshipImageType?: string,
        public status?: Status,
        public createdDate?: number,
        public lastUpdatedDate?: number,
        public createdBy?: string,
        public lastUpdatedBy?: string,
        public relationshipId?: number,
    ) {
    }
}
