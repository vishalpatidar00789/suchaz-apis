import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuchazapisSharedModule } from '../../shared';
import {
    ItemSuchazService,
    ItemSuchazPopupService,
    ItemSuchazComponent,
    ItemSuchazDetailComponent,
    ItemSuchazDialogComponent,
    ItemSuchazPopupComponent,
    ItemSuchazDeletePopupComponent,
    ItemSuchazDeleteDialogComponent,
    itemRoute,
    itemPopupRoute,
} from './';

const ENTITY_STATES = [
    ...itemRoute,
    ...itemPopupRoute,
];

@NgModule({
    imports: [
        SuchazapisSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ItemSuchazComponent,
        ItemSuchazDetailComponent,
        ItemSuchazDialogComponent,
        ItemSuchazDeleteDialogComponent,
        ItemSuchazPopupComponent,
        ItemSuchazDeletePopupComponent,
    ],
    entryComponents: [
        ItemSuchazComponent,
        ItemSuchazDialogComponent,
        ItemSuchazPopupComponent,
        ItemSuchazDeleteDialogComponent,
        ItemSuchazDeletePopupComponent,
    ],
    providers: [
        ItemSuchazService,
        ItemSuchazPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuchazapisItemSuchazModule {}
