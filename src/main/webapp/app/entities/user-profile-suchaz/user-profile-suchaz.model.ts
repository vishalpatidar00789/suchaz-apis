import { BaseEntity } from './../../shared';

export const enum RelationshipStatus {
    'SINGLE',
    'COMMITTED',
    'MARRIED'
}

export class UserProfileSuchaz implements BaseEntity {
    constructor(
        public id?: number,
        public fullName?: string,
        public dob?: number,
        public aboutMe?: string,
        public relationshipStatus?: RelationshipStatus,
        public geoLocation?: string,
        public prifilePicContentType?: string,
        public prifilePic?: any,
        public createdDate?: number,
        public lastUpdatedDate?: number,
        public createdBy?: string,
        public lastUpdatedBy?: string,
        public suchAzUserId?: number,
    ) {
    }
}
