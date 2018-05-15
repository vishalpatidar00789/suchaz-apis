import { BaseEntity } from './../../shared';

export const enum Status {
    'ACTIVE',
    'INACTIVE'
}

export class SuchAzMenuSuchaz implements BaseEntity {
    constructor(
        public id?: number,
        public title?: string,
        public menu?: string,
        public menuCode?: string,
        public discription?: string,
        public menuImageContentType?: string,
        public menuImage?: any,
        public status?: Status,
        public isExposedToMenu?: Status,
        public createdDate?: number,
        public lastUpdatedDate?: number,
        public createdBy?: string,
        public lastUpdatedBy?: string,
        public items?: BaseEntity[],
    ) {
    }
}
