import { BaseEntity } from './../../shared';

export const enum Status {
    'ACTIVE',
    'INACTIVE'
}

export class RelationshipSuchaz implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public description?: string,
        public relationshipCode?: string,
        public isExposedToMenu?: Status,
        public status?: Status,
        public createdDate?: number,
        public lastUpdatedDate?: number,
        public createdBy?: string,
        public lastUpdatedBy?: string,
        public relationshipImages?: BaseEntity[],
    ) {
    }
}
