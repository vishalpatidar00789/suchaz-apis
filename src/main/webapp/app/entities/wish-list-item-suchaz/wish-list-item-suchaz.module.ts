import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuchazapisSharedModule } from '../../shared';
import {
    WishListItemSuchazService,
    WishListItemSuchazPopupService,
    WishListItemSuchazComponent,
    WishListItemSuchazDetailComponent,
    WishListItemSuchazDialogComponent,
    WishListItemSuchazPopupComponent,
    WishListItemSuchazDeletePopupComponent,
    WishListItemSuchazDeleteDialogComponent,
    wishListItemRoute,
    wishListItemPopupRoute,
} from './';

const ENTITY_STATES = [
    ...wishListItemRoute,
    ...wishListItemPopupRoute,
];

@NgModule({
    imports: [
        SuchazapisSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        WishListItemSuchazComponent,
        WishListItemSuchazDetailComponent,
        WishListItemSuchazDialogComponent,
        WishListItemSuchazDeleteDialogComponent,
        WishListItemSuchazPopupComponent,
        WishListItemSuchazDeletePopupComponent,
    ],
    entryComponents: [
        WishListItemSuchazComponent,
        WishListItemSuchazDialogComponent,
        WishListItemSuchazPopupComponent,
        WishListItemSuchazDeleteDialogComponent,
        WishListItemSuchazDeletePopupComponent,
    ],
    providers: [
        WishListItemSuchazService,
        WishListItemSuchazPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuchazapisWishListItemSuchazModule {}
