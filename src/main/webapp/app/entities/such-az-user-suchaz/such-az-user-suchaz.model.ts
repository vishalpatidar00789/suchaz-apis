import { BaseEntity } from './../../shared';

export const enum Status {
    'ACTIVE',
    'INACTIVE'
}

export const enum UserRole {
    'GIFTER'
}

export const enum FBAccessTokenType {
    'USER_ACCESS_TOKEN'
}

export const enum SignupMethod {
    'FACEBOOK',
    'EMAIL'
}

export class SuchAzUserSuchaz implements BaseEntity {
    constructor(
        public id?: number,
        public email?: string,
        public password?: string,
        public contact?: number,
        public status?: Status,
        public role?: UserRole,
        public fbAccessToken?: string,
        public fbAccessTokenType?: FBAccessTokenType,
        public isVarified?: string,
        public tokenExpDate?: number,
        public varifiedBy?: SignupMethod,
        public signupMethod?: SignupMethod,
        public createdDate?: number,
        public lastUpdatedDate?: number,
        public createdBy?: string,
        public lastUpdatedBy?: string,
        public userGiftWrappers?: BaseEntity[],
        public consumerProfiles?: BaseEntity[],
        public traits?: BaseEntity[],
        public userProfileId?: number,
        public wishListId?: number,
        public buyLaterListId?: number,
        public activityListId?: number,
    ) {
    }
}
