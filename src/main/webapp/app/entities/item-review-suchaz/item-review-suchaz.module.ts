import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuchazapisSharedModule } from '../../shared';
import {
    ItemReviewSuchazService,
    ItemReviewSuchazPopupService,
    ItemReviewSuchazComponent,
    ItemReviewSuchazDetailComponent,
    ItemReviewSuchazDialogComponent,
    ItemReviewSuchazPopupComponent,
    ItemReviewSuchazDeletePopupComponent,
    ItemReviewSuchazDeleteDialogComponent,
    itemReviewRoute,
    itemReviewPopupRoute,
} from './';

const ENTITY_STATES = [
    ...itemReviewRoute,
    ...itemReviewPopupRoute,
];

@NgModule({
    imports: [
        SuchazapisSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ItemReviewSuchazComponent,
        ItemReviewSuchazDetailComponent,
        ItemReviewSuchazDialogComponent,
        ItemReviewSuchazDeleteDialogComponent,
        ItemReviewSuchazPopupComponent,
        ItemReviewSuchazDeletePopupComponent,
    ],
    entryComponents: [
        ItemReviewSuchazComponent,
        ItemReviewSuchazDialogComponent,
        ItemReviewSuchazPopupComponent,
        ItemReviewSuchazDeleteDialogComponent,
        ItemReviewSuchazDeletePopupComponent,
    ],
    providers: [
        ItemReviewSuchazService,
        ItemReviewSuchazPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuchazapisItemReviewSuchazModule {}
