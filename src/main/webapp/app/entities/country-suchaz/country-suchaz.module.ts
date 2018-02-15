import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuchazapisSharedModule } from '../../shared';
import {
    CountrySuchazService,
    CountrySuchazPopupService,
    CountrySuchazComponent,
    CountrySuchazDetailComponent,
    CountrySuchazDialogComponent,
    CountrySuchazPopupComponent,
    CountrySuchazDeletePopupComponent,
    CountrySuchazDeleteDialogComponent,
    countryRoute,
    countryPopupRoute,
} from './';

const ENTITY_STATES = [
    ...countryRoute,
    ...countryPopupRoute,
];

@NgModule({
    imports: [
        SuchazapisSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        CountrySuchazComponent,
        CountrySuchazDetailComponent,
        CountrySuchazDialogComponent,
        CountrySuchazDeleteDialogComponent,
        CountrySuchazPopupComponent,
        CountrySuchazDeletePopupComponent,
    ],
    entryComponents: [
        CountrySuchazComponent,
        CountrySuchazDialogComponent,
        CountrySuchazPopupComponent,
        CountrySuchazDeleteDialogComponent,
        CountrySuchazDeletePopupComponent,
    ],
    providers: [
        CountrySuchazService,
        CountrySuchazPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuchazapisCountrySuchazModule {}
