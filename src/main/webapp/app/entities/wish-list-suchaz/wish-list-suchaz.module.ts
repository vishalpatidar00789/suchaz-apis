import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuchazapisSharedModule } from '../../shared';
import {
    WishListSuchazService,
    WishListSuchazPopupService,
    WishListSuchazComponent,
    WishListSuchazDetailComponent,
    WishListSuchazDialogComponent,
    WishListSuchazPopupComponent,
    WishListSuchazDeletePopupComponent,
    WishListSuchazDeleteDialogComponent,
    wishListRoute,
    wishListPopupRoute,
} from './';

const ENTITY_STATES = [
    ...wishListRoute,
    ...wishListPopupRoute,
];

@NgModule({
    imports: [
        SuchazapisSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        WishListSuchazComponent,
        WishListSuchazDetailComponent,
        WishListSuchazDialogComponent,
        WishListSuchazDeleteDialogComponent,
        WishListSuchazPopupComponent,
        WishListSuchazDeletePopupComponent,
    ],
    entryComponents: [
        WishListSuchazComponent,
        WishListSuchazDialogComponent,
        WishListSuchazPopupComponent,
        WishListSuchazDeleteDialogComponent,
        WishListSuchazDeletePopupComponent,
    ],
    providers: [
        WishListSuchazService,
        WishListSuchazPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuchazapisWishListSuchazModule {}
