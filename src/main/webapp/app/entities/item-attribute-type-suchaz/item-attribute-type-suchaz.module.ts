import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuchazapisSharedModule } from '../../shared';
import {
    ItemAttributeTypeSuchazService,
    ItemAttributeTypeSuchazPopupService,
    ItemAttributeTypeSuchazComponent,
    ItemAttributeTypeSuchazDetailComponent,
    ItemAttributeTypeSuchazDialogComponent,
    ItemAttributeTypeSuchazPopupComponent,
    ItemAttributeTypeSuchazDeletePopupComponent,
    ItemAttributeTypeSuchazDeleteDialogComponent,
    itemAttributeTypeRoute,
    itemAttributeTypePopupRoute,
} from './';

const ENTITY_STATES = [
    ...itemAttributeTypeRoute,
    ...itemAttributeTypePopupRoute,
];

@NgModule({
    imports: [
        SuchazapisSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ItemAttributeTypeSuchazComponent,
        ItemAttributeTypeSuchazDetailComponent,
        ItemAttributeTypeSuchazDialogComponent,
        ItemAttributeTypeSuchazDeleteDialogComponent,
        ItemAttributeTypeSuchazPopupComponent,
        ItemAttributeTypeSuchazDeletePopupComponent,
    ],
    entryComponents: [
        ItemAttributeTypeSuchazComponent,
        ItemAttributeTypeSuchazDialogComponent,
        ItemAttributeTypeSuchazPopupComponent,
        ItemAttributeTypeSuchazDeleteDialogComponent,
        ItemAttributeTypeSuchazDeletePopupComponent,
    ],
    providers: [
        ItemAttributeTypeSuchazService,
        ItemAttributeTypeSuchazPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuchazapisItemAttributeTypeSuchazModule {}
