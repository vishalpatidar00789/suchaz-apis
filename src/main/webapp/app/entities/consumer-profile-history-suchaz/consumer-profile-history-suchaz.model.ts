import { BaseEntity } from './../../shared';

export const enum Gender {
    'MALE',
    'FEMALE'
}

export const enum AgeGroup {
    'KIDS',
    'TEEN',
    'YOUTH',
    'ELDER'
}

export class ConsumerProfileHistorySuchaz implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public gender?: Gender,
        public age?: AgeGroup,
        public inputTraits?: string,
        public traitStructure?: string,
        public activityStructure?: string,
        public hobbyStructure?: string,
        public inputReletionship?: string,
        public inputHobbies?: string,
        public reccomendedProductTypes?: string,
        public createdDate?: number,
        public lastUpdatedDate?: number,
        public createdBy?: string,
        public lastUpdatedBy?: string,
    ) {
    }
}
