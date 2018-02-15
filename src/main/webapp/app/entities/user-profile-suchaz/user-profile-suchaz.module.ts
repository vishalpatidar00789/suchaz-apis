import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuchazapisSharedModule } from '../../shared';
import {
    UserProfileSuchazService,
    UserProfileSuchazPopupService,
    UserProfileSuchazComponent,
    UserProfileSuchazDetailComponent,
    UserProfileSuchazDialogComponent,
    UserProfileSuchazPopupComponent,
    UserProfileSuchazDeletePopupComponent,
    UserProfileSuchazDeleteDialogComponent,
    userProfileRoute,
    userProfilePopupRoute,
} from './';

const ENTITY_STATES = [
    ...userProfileRoute,
    ...userProfilePopupRoute,
];

@NgModule({
    imports: [
        SuchazapisSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        UserProfileSuchazComponent,
        UserProfileSuchazDetailComponent,
        UserProfileSuchazDialogComponent,
        UserProfileSuchazDeleteDialogComponent,
        UserProfileSuchazPopupComponent,
        UserProfileSuchazDeletePopupComponent,
    ],
    entryComponents: [
        UserProfileSuchazComponent,
        UserProfileSuchazDialogComponent,
        UserProfileSuchazPopupComponent,
        UserProfileSuchazDeleteDialogComponent,
        UserProfileSuchazDeletePopupComponent,
    ],
    providers: [
        UserProfileSuchazService,
        UserProfileSuchazPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuchazapisUserProfileSuchazModule {}
