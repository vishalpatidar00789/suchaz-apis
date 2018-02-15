import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuchazapisSharedModule } from '../../shared';
import {
    SuchAzUserSuchazService,
    SuchAzUserSuchazPopupService,
    SuchAzUserSuchazComponent,
    SuchAzUserSuchazDetailComponent,
    SuchAzUserSuchazDialogComponent,
    SuchAzUserSuchazPopupComponent,
    SuchAzUserSuchazDeletePopupComponent,
    SuchAzUserSuchazDeleteDialogComponent,
    suchAzUserRoute,
    suchAzUserPopupRoute,
} from './';

const ENTITY_STATES = [
    ...suchAzUserRoute,
    ...suchAzUserPopupRoute,
];

@NgModule({
    imports: [
        SuchazapisSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        SuchAzUserSuchazComponent,
        SuchAzUserSuchazDetailComponent,
        SuchAzUserSuchazDialogComponent,
        SuchAzUserSuchazDeleteDialogComponent,
        SuchAzUserSuchazPopupComponent,
        SuchAzUserSuchazDeletePopupComponent,
    ],
    entryComponents: [
        SuchAzUserSuchazComponent,
        SuchAzUserSuchazDialogComponent,
        SuchAzUserSuchazPopupComponent,
        SuchAzUserSuchazDeleteDialogComponent,
        SuchAzUserSuchazDeletePopupComponent,
    ],
    providers: [
        SuchAzUserSuchazService,
        SuchAzUserSuchazPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuchazapisSuchAzUserSuchazModule {}
