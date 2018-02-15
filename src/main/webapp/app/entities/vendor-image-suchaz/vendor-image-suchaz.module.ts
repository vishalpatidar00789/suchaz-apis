import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuchazapisSharedModule } from '../../shared';
import {
    VendorImageSuchazService,
    VendorImageSuchazPopupService,
    VendorImageSuchazComponent,
    VendorImageSuchazDetailComponent,
    VendorImageSuchazDialogComponent,
    VendorImageSuchazPopupComponent,
    VendorImageSuchazDeletePopupComponent,
    VendorImageSuchazDeleteDialogComponent,
    vendorImageRoute,
    vendorImagePopupRoute,
} from './';

const ENTITY_STATES = [
    ...vendorImageRoute,
    ...vendorImagePopupRoute,
];

@NgModule({
    imports: [
        SuchazapisSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        VendorImageSuchazComponent,
        VendorImageSuchazDetailComponent,
        VendorImageSuchazDialogComponent,
        VendorImageSuchazDeleteDialogComponent,
        VendorImageSuchazPopupComponent,
        VendorImageSuchazDeletePopupComponent,
    ],
    entryComponents: [
        VendorImageSuchazComponent,
        VendorImageSuchazDialogComponent,
        VendorImageSuchazPopupComponent,
        VendorImageSuchazDeleteDialogComponent,
        VendorImageSuchazDeletePopupComponent,
    ],
    providers: [
        VendorImageSuchazService,
        VendorImageSuchazPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuchazapisVendorImageSuchazModule {}
