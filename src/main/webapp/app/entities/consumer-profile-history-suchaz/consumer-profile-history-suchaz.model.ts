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
<<<<<<< HEAD
        public isLoggedInUser?: boolean,
    ) {
        this.isLoggedInUser = false;
=======
    ) {
>>>>>>> 6cab73db24b70508e06c72c996d5888289cc39ad
    }
}
