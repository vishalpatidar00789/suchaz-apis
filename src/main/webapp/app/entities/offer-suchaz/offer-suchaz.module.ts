import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuchazapisSharedModule } from '../../shared';
import {
    OfferSuchazService,
    OfferSuchazPopupService,
    OfferSuchazComponent,
    OfferSuchazDetailComponent,
    OfferSuchazDialogComponent,
    OfferSuchazPopupComponent,
    OfferSuchazDeletePopupComponent,
    OfferSuchazDeleteDialogComponent,
    offerRoute,
    offerPopupRoute,
} from './';

const ENTITY_STATES = [
    ...offerRoute,
    ...offerPopupRoute,
];

@NgModule({
    imports: [
        SuchazapisSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        OfferSuchazComponent,
        OfferSuchazDetailComponent,
        OfferSuchazDialogComponent,
        OfferSuchazDeleteDialogComponent,
        OfferSuchazPopupComponent,
        OfferSuchazDeletePopupComponent,
    ],
    entryComponents: [
        OfferSuchazComponent,
        OfferSuchazDialogComponent,
        OfferSuchazPopupComponent,
        OfferSuchazDeleteDialogComponent,
        OfferSuchazDeletePopupComponent,
    ],
    providers: [
        OfferSuchazService,
        OfferSuchazPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuchazapisOfferSuchazModule {}
