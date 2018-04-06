import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuchazapisSharedModule } from '../../shared';
import {
    ConsumerProfileHistorySuchazService,
    ConsumerProfileHistorySuchazPopupService,
    ConsumerProfileHistorySuchazComponent,
    ConsumerProfileHistorySuchazDetailComponent,
    ConsumerProfileHistorySuchazDialogComponent,
    ConsumerProfileHistorySuchazPopupComponent,
    ConsumerProfileHistorySuchazDeletePopupComponent,
    ConsumerProfileHistorySuchazDeleteDialogComponent,
    consumerProfileHistoryRoute,
    consumerProfileHistoryPopupRoute,
} from './';

const ENTITY_STATES = [
    ...consumerProfileHistoryRoute,
    ...consumerProfileHistoryPopupRoute,
];

@NgModule({
    imports: [
        SuchazapisSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ConsumerProfileHistorySuchazComponent,
        ConsumerProfileHistorySuchazDetailComponent,
        ConsumerProfileHistorySuchazDialogComponent,
        ConsumerProfileHistorySuchazDeleteDialogComponent,
        ConsumerProfileHistorySuchazPopupComponent,
        ConsumerProfileHistorySuchazDeletePopupComponent,
    ],
    entryComponents: [
        ConsumerProfileHistorySuchazComponent,
        ConsumerProfileHistorySuchazDialogComponent,
        ConsumerProfileHistorySuchazPopupComponent,
        ConsumerProfileHistorySuchazDeleteDialogComponent,
        ConsumerProfileHistorySuchazDeletePopupComponent,
    ],
    providers: [
        ConsumerProfileHistorySuchazService,
        ConsumerProfileHistorySuchazPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuchazapisConsumerProfileHistorySuchazModule {}
