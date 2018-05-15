import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuchazapisSharedModule } from '../../shared';
import {
    SuchAzMenuSuchazService,
    SuchAzMenuSuchazPopupService,
    SuchAzMenuSuchazComponent,
    SuchAzMenuSuchazDetailComponent,
    SuchAzMenuSuchazDialogComponent,
    SuchAzMenuSuchazPopupComponent,
    SuchAzMenuSuchazDeletePopupComponent,
    SuchAzMenuSuchazDeleteDialogComponent,
    suchAzMenuRoute,
    suchAzMenuPopupRoute,
} from './';

const ENTITY_STATES = [
    ...suchAzMenuRoute,
    ...suchAzMenuPopupRoute,
];

@NgModule({
    imports: [
        SuchazapisSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        SuchAzMenuSuchazComponent,
        SuchAzMenuSuchazDetailComponent,
        SuchAzMenuSuchazDialogComponent,
        SuchAzMenuSuchazDeleteDialogComponent,
        SuchAzMenuSuchazPopupComponent,
        SuchAzMenuSuchazDeletePopupComponent,
    ],
    entryComponents: [
        SuchAzMenuSuchazComponent,
        SuchAzMenuSuchazDialogComponent,
        SuchAzMenuSuchazPopupComponent,
        SuchAzMenuSuchazDeleteDialogComponent,
        SuchAzMenuSuchazDeletePopupComponent,
    ],
    providers: [
        SuchAzMenuSuchazService,
        SuchAzMenuSuchazPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuchazapisSuchAzMenuSuchazModule {}
