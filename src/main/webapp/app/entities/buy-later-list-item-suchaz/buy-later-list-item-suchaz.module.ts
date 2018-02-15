import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuchazapisSharedModule } from '../../shared';
import {
    BuyLaterListItemSuchazService,
    BuyLaterListItemSuchazPopupService,
    BuyLaterListItemSuchazComponent,
    BuyLaterListItemSuchazDetailComponent,
    BuyLaterListItemSuchazDialogComponent,
    BuyLaterListItemSuchazPopupComponent,
    BuyLaterListItemSuchazDeletePopupComponent,
    BuyLaterListItemSuchazDeleteDialogComponent,
    buyLaterListItemRoute,
    buyLaterListItemPopupRoute,
} from './';

const ENTITY_STATES = [
    ...buyLaterListItemRoute,
    ...buyLaterListItemPopupRoute,
];

@NgModule({
    imports: [
        SuchazapisSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        BuyLaterListItemSuchazComponent,
        BuyLaterListItemSuchazDetailComponent,
        BuyLaterListItemSuchazDialogComponent,
        BuyLaterListItemSuchazDeleteDialogComponent,
        BuyLaterListItemSuchazPopupComponent,
        BuyLaterListItemSuchazDeletePopupComponent,
    ],
    entryComponents: [
        BuyLaterListItemSuchazComponent,
        BuyLaterListItemSuchazDialogComponent,
        BuyLaterListItemSuchazPopupComponent,
        BuyLaterListItemSuchazDeleteDialogComponent,
        BuyLaterListItemSuchazDeletePopupComponent,
    ],
    providers: [
        BuyLaterListItemSuchazService,
        BuyLaterListItemSuchazPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuchazapisBuyLaterListItemSuchazModule {}
