import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuchazapisSharedModule } from '../../shared';
import {
    BuyLaterListSuchazService,
    BuyLaterListSuchazPopupService,
    BuyLaterListSuchazComponent,
    BuyLaterListSuchazDetailComponent,
    BuyLaterListSuchazDialogComponent,
    BuyLaterListSuchazPopupComponent,
    BuyLaterListSuchazDeletePopupComponent,
    BuyLaterListSuchazDeleteDialogComponent,
    buyLaterListRoute,
    buyLaterListPopupRoute,
} from './';

const ENTITY_STATES = [
    ...buyLaterListRoute,
    ...buyLaterListPopupRoute,
];

@NgModule({
    imports: [
        SuchazapisSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        BuyLaterListSuchazComponent,
        BuyLaterListSuchazDetailComponent,
        BuyLaterListSuchazDialogComponent,
        BuyLaterListSuchazDeleteDialogComponent,
        BuyLaterListSuchazPopupComponent,
        BuyLaterListSuchazDeletePopupComponent,
    ],
    entryComponents: [
        BuyLaterListSuchazComponent,
        BuyLaterListSuchazDialogComponent,
        BuyLaterListSuchazPopupComponent,
        BuyLaterListSuchazDeleteDialogComponent,
        BuyLaterListSuchazDeletePopupComponent,
    ],
    providers: [
        BuyLaterListSuchazService,
        BuyLaterListSuchazPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuchazapisBuyLaterListSuchazModule {}
