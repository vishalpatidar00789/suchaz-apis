import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuchazapisSharedModule } from '../../shared';
import {
    StoreImageSuchazService,
    StoreImageSuchazPopupService,
    StoreImageSuchazComponent,
    StoreImageSuchazDetailComponent,
    StoreImageSuchazDialogComponent,
    StoreImageSuchazPopupComponent,
    StoreImageSuchazDeletePopupComponent,
    StoreImageSuchazDeleteDialogComponent,
    storeImageRoute,
    storeImagePopupRoute,
} from './';

const ENTITY_STATES = [
    ...storeImageRoute,
    ...storeImagePopupRoute,
];

@NgModule({
    imports: [
        SuchazapisSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        StoreImageSuchazComponent,
        StoreImageSuchazDetailComponent,
        StoreImageSuchazDialogComponent,
        StoreImageSuchazDeleteDialogComponent,
        StoreImageSuchazPopupComponent,
        StoreImageSuchazDeletePopupComponent,
    ],
    entryComponents: [
        StoreImageSuchazComponent,
        StoreImageSuchazDialogComponent,
        StoreImageSuchazPopupComponent,
        StoreImageSuchazDeleteDialogComponent,
        StoreImageSuchazDeletePopupComponent,
    ],
    providers: [
        StoreImageSuchazService,
        StoreImageSuchazPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuchazapisStoreImageSuchazModule {}
