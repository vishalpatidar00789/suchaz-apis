import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuchazapisSharedModule } from '../../shared';
import {
    StoreSuchazService,
    StoreSuchazPopupService,
    StoreSuchazComponent,
    StoreSuchazDetailComponent,
    StoreSuchazDialogComponent,
    StoreSuchazPopupComponent,
    StoreSuchazDeletePopupComponent,
    StoreSuchazDeleteDialogComponent,
    storeRoute,
    storePopupRoute,
} from './';

const ENTITY_STATES = [
    ...storeRoute,
    ...storePopupRoute,
];

@NgModule({
    imports: [
        SuchazapisSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        StoreSuchazComponent,
        StoreSuchazDetailComponent,
        StoreSuchazDialogComponent,
        StoreSuchazDeleteDialogComponent,
        StoreSuchazPopupComponent,
        StoreSuchazDeletePopupComponent,
    ],
    entryComponents: [
        StoreSuchazComponent,
        StoreSuchazDialogComponent,
        StoreSuchazPopupComponent,
        StoreSuchazDeleteDialogComponent,
        StoreSuchazDeletePopupComponent,
    ],
    providers: [
        StoreSuchazService,
        StoreSuchazPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuchazapisStoreSuchazModule {}
