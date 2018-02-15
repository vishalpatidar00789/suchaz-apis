import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuchazapisSharedModule } from '../../shared';
import {
    VendorSuchazService,
    VendorSuchazPopupService,
    VendorSuchazComponent,
    VendorSuchazDetailComponent,
    VendorSuchazDialogComponent,
    VendorSuchazPopupComponent,
    VendorSuchazDeletePopupComponent,
    VendorSuchazDeleteDialogComponent,
    vendorRoute,
    vendorPopupRoute,
} from './';

const ENTITY_STATES = [
    ...vendorRoute,
    ...vendorPopupRoute,
];

@NgModule({
    imports: [
        SuchazapisSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        VendorSuchazComponent,
        VendorSuchazDetailComponent,
        VendorSuchazDialogComponent,
        VendorSuchazDeleteDialogComponent,
        VendorSuchazPopupComponent,
        VendorSuchazDeletePopupComponent,
    ],
    entryComponents: [
        VendorSuchazComponent,
        VendorSuchazDialogComponent,
        VendorSuchazPopupComponent,
        VendorSuchazDeleteDialogComponent,
        VendorSuchazDeletePopupComponent,
    ],
    providers: [
        VendorSuchazService,
        VendorSuchazPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuchazapisVendorSuchazModule {}
