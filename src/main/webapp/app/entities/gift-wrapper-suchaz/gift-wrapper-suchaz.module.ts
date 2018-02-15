import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuchazapisSharedModule } from '../../shared';
import {
    GiftWrapperSuchazService,
    GiftWrapperSuchazPopupService,
    GiftWrapperSuchazComponent,
    GiftWrapperSuchazDetailComponent,
    GiftWrapperSuchazDialogComponent,
    GiftWrapperSuchazPopupComponent,
    GiftWrapperSuchazDeletePopupComponent,
    GiftWrapperSuchazDeleteDialogComponent,
    giftWrapperRoute,
    giftWrapperPopupRoute,
} from './';

const ENTITY_STATES = [
    ...giftWrapperRoute,
    ...giftWrapperPopupRoute,
];

@NgModule({
    imports: [
        SuchazapisSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        GiftWrapperSuchazComponent,
        GiftWrapperSuchazDetailComponent,
        GiftWrapperSuchazDialogComponent,
        GiftWrapperSuchazDeleteDialogComponent,
        GiftWrapperSuchazPopupComponent,
        GiftWrapperSuchazDeletePopupComponent,
    ],
    entryComponents: [
        GiftWrapperSuchazComponent,
        GiftWrapperSuchazDialogComponent,
        GiftWrapperSuchazPopupComponent,
        GiftWrapperSuchazDeleteDialogComponent,
        GiftWrapperSuchazDeletePopupComponent,
    ],
    providers: [
        GiftWrapperSuchazService,
        GiftWrapperSuchazPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuchazapisGiftWrapperSuchazModule {}
