import { BaseEntity } from './../../shared';

export const enum Status {
    'ACTIVE',
    'INACTIVE'
}

export class OccassionSuchaz implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public description?: string,
        public occasionCode?: string,
        public isExposedToMenu?: Status,
        public status?: Status,
        public createdDate?: number,
        public lastUpdatedDate?: number,
        public createdBy?: string,
        public lastUpdatedBy?: string,
        public occasionImages?: BaseEntity[],
    ) {
    }
}
