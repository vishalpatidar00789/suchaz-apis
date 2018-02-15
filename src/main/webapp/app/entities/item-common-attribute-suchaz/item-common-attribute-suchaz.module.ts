import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuchazapisSharedModule } from '../../shared';
import {
    ItemCommonAttributeSuchazService,
    ItemCommonAttributeSuchazPopupService,
    ItemCommonAttributeSuchazComponent,
    ItemCommonAttributeSuchazDetailComponent,
    ItemCommonAttributeSuchazDialogComponent,
    ItemCommonAttributeSuchazPopupComponent,
    ItemCommonAttributeSuchazDeletePopupComponent,
    ItemCommonAttributeSuchazDeleteDialogComponent,
    itemCommonAttributeRoute,
    itemCommonAttributePopupRoute,
} from './';

const ENTITY_STATES = [
    ...itemCommonAttributeRoute,
    ...itemCommonAttributePopupRoute,
];

@NgModule({
    imports: [
        SuchazapisSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ItemCommonAttributeSuchazComponent,
        ItemCommonAttributeSuchazDetailComponent,
        ItemCommonAttributeSuchazDialogComponent,
        ItemCommonAttributeSuchazDeleteDialogComponent,
        ItemCommonAttributeSuchazPopupComponent,
        ItemCommonAttributeSuchazDeletePopupComponent,
    ],
    entryComponents: [
        ItemCommonAttributeSuchazComponent,
        ItemCommonAttributeSuchazDialogComponent,
        ItemCommonAttributeSuchazPopupComponent,
        ItemCommonAttributeSuchazDeleteDialogComponent,
        ItemCommonAttributeSuchazDeletePopupComponent,
    ],
    providers: [
        ItemCommonAttributeSuchazService,
        ItemCommonAttributeSuchazPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuchazapisItemCommonAttributeSuchazModule {}
