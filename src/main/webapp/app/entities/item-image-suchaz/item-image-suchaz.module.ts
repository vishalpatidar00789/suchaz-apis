import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuchazapisSharedModule } from '../../shared';
import {
    ItemImageSuchazService,
    ItemImageSuchazPopupService,
    ItemImageSuchazComponent,
    ItemImageSuchazDetailComponent,
    ItemImageSuchazDialogComponent,
    ItemImageSuchazPopupComponent,
    ItemImageSuchazDeletePopupComponent,
    ItemImageSuchazDeleteDialogComponent,
    itemImageRoute,
    itemImagePopupRoute,
} from './';

const ENTITY_STATES = [
    ...itemImageRoute,
    ...itemImagePopupRoute,
];

@NgModule({
    imports: [
        SuchazapisSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ItemImageSuchazComponent,
        ItemImageSuchazDetailComponent,
        ItemImageSuchazDialogComponent,
        ItemImageSuchazDeleteDialogComponent,
        ItemImageSuchazPopupComponent,
        ItemImageSuchazDeletePopupComponent,
    ],
    entryComponents: [
        ItemImageSuchazComponent,
        ItemImageSuchazDialogComponent,
        ItemImageSuchazPopupComponent,
        ItemImageSuchazDeleteDialogComponent,
        ItemImageSuchazDeletePopupComponent,
    ],
    providers: [
        ItemImageSuchazService,
        ItemImageSuchazPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuchazapisItemImageSuchazModule {}
