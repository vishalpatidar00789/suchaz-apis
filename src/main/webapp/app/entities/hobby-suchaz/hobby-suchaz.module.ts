import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuchazapisSharedModule } from '../../shared';
import {
    HobbySuchazService,
    HobbySuchazPopupService,
    HobbySuchazComponent,
    HobbySuchazDetailComponent,
    HobbySuchazDialogComponent,
    HobbySuchazPopupComponent,
    HobbySuchazDeletePopupComponent,
    HobbySuchazDeleteDialogComponent,
    hobbyRoute,
    hobbyPopupRoute,
} from './';

const ENTITY_STATES = [
    ...hobbyRoute,
    ...hobbyPopupRoute,
];

@NgModule({
    imports: [
        SuchazapisSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        HobbySuchazComponent,
        HobbySuchazDetailComponent,
        HobbySuchazDialogComponent,
        HobbySuchazDeleteDialogComponent,
        HobbySuchazPopupComponent,
        HobbySuchazDeletePopupComponent,
    ],
    entryComponents: [
        HobbySuchazComponent,
        HobbySuchazDialogComponent,
        HobbySuchazPopupComponent,
        HobbySuchazDeleteDialogComponent,
        HobbySuchazDeletePopupComponent,
    ],
    providers: [
        HobbySuchazService,
        HobbySuchazPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuchazapisHobbySuchazModule {}
