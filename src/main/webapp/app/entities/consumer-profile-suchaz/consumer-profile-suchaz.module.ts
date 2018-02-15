import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuchazapisSharedModule } from '../../shared';
import {
    ConsumerProfileSuchazService,
    ConsumerProfileSuchazPopupService,
    ConsumerProfileSuchazComponent,
    ConsumerProfileSuchazDetailComponent,
    ConsumerProfileSuchazDialogComponent,
    ConsumerProfileSuchazPopupComponent,
    ConsumerProfileSuchazDeletePopupComponent,
    ConsumerProfileSuchazDeleteDialogComponent,
    consumerProfileRoute,
    consumerProfilePopupRoute,
} from './';

const ENTITY_STATES = [
    ...consumerProfileRoute,
    ...consumerProfilePopupRoute,
];

@NgModule({
    imports: [
        SuchazapisSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ConsumerProfileSuchazComponent,
        ConsumerProfileSuchazDetailComponent,
        ConsumerProfileSuchazDialogComponent,
        ConsumerProfileSuchazDeleteDialogComponent,
        ConsumerProfileSuchazPopupComponent,
        ConsumerProfileSuchazDeletePopupComponent,
    ],
    entryComponents: [
        ConsumerProfileSuchazComponent,
        ConsumerProfileSuchazDialogComponent,
        ConsumerProfileSuchazPopupComponent,
        ConsumerProfileSuchazDeleteDialogComponent,
        ConsumerProfileSuchazDeletePopupComponent,
    ],
    providers: [
        ConsumerProfileSuchazService,
        ConsumerProfileSuchazPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuchazapisConsumerProfileSuchazModule {}
