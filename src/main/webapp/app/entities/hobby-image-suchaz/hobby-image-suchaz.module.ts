import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuchazapisSharedModule } from '../../shared';
import {
    HobbyImageSuchazService,
    HobbyImageSuchazPopupService,
    HobbyImageSuchazComponent,
    HobbyImageSuchazDetailComponent,
    HobbyImageSuchazDialogComponent,
    HobbyImageSuchazPopupComponent,
    HobbyImageSuchazDeletePopupComponent,
    HobbyImageSuchazDeleteDialogComponent,
    hobbyImageRoute,
    hobbyImagePopupRoute,
} from './';

const ENTITY_STATES = [
    ...hobbyImageRoute,
    ...hobbyImagePopupRoute,
];

@NgModule({
    imports: [
        SuchazapisSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        HobbyImageSuchazComponent,
        HobbyImageSuchazDetailComponent,
        HobbyImageSuchazDialogComponent,
        HobbyImageSuchazDeleteDialogComponent,
        HobbyImageSuchazPopupComponent,
        HobbyImageSuchazDeletePopupComponent,
    ],
    entryComponents: [
        HobbyImageSuchazComponent,
        HobbyImageSuchazDialogComponent,
        HobbyImageSuchazPopupComponent,
        HobbyImageSuchazDeleteDialogComponent,
        HobbyImageSuchazDeletePopupComponent,
    ],
    providers: [
        HobbyImageSuchazService,
        HobbyImageSuchazPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuchazapisHobbyImageSuchazModule {}
