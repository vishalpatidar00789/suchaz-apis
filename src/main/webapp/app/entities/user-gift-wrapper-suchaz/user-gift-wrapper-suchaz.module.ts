import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuchazapisSharedModule } from '../../shared';
import {
    UserGiftWrapperSuchazService,
    UserGiftWrapperSuchazPopupService,
    UserGiftWrapperSuchazComponent,
    UserGiftWrapperSuchazDetailComponent,
    UserGiftWrapperSuchazDialogComponent,
    UserGiftWrapperSuchazPopupComponent,
    UserGiftWrapperSuchazDeletePopupComponent,
    UserGiftWrapperSuchazDeleteDialogComponent,
    userGiftWrapperRoute,
    userGiftWrapperPopupRoute,
} from './';

const ENTITY_STATES = [
    ...userGiftWrapperRoute,
    ...userGiftWrapperPopupRoute,
];

@NgModule({
    imports: [
        SuchazapisSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        UserGiftWrapperSuchazComponent,
        UserGiftWrapperSuchazDetailComponent,
        UserGiftWrapperSuchazDialogComponent,
        UserGiftWrapperSuchazDeleteDialogComponent,
        UserGiftWrapperSuchazPopupComponent,
        UserGiftWrapperSuchazDeletePopupComponent,
    ],
    entryComponents: [
        UserGiftWrapperSuchazComponent,
        UserGiftWrapperSuchazDialogComponent,
        UserGiftWrapperSuchazPopupComponent,
        UserGiftWrapperSuchazDeleteDialogComponent,
        UserGiftWrapperSuchazDeletePopupComponent,
    ],
    providers: [
        UserGiftWrapperSuchazService,
        UserGiftWrapperSuchazPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuchazapisUserGiftWrapperSuchazModule {}
