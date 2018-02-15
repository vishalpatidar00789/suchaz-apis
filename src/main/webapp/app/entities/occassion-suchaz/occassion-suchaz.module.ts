import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuchazapisSharedModule } from '../../shared';
import {
    OccassionSuchazService,
    OccassionSuchazPopupService,
    OccassionSuchazComponent,
    OccassionSuchazDetailComponent,
    OccassionSuchazDialogComponent,
    OccassionSuchazPopupComponent,
    OccassionSuchazDeletePopupComponent,
    OccassionSuchazDeleteDialogComponent,
    occassionRoute,
    occassionPopupRoute,
} from './';

const ENTITY_STATES = [
    ...occassionRoute,
    ...occassionPopupRoute,
];

@NgModule({
    imports: [
        SuchazapisSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        OccassionSuchazComponent,
        OccassionSuchazDetailComponent,
        OccassionSuchazDialogComponent,
        OccassionSuchazDeleteDialogComponent,
        OccassionSuchazPopupComponent,
        OccassionSuchazDeletePopupComponent,
    ],
    entryComponents: [
        OccassionSuchazComponent,
        OccassionSuchazDialogComponent,
        OccassionSuchazPopupComponent,
        OccassionSuchazDeleteDialogComponent,
        OccassionSuchazDeletePopupComponent,
    ],
    providers: [
        OccassionSuchazService,
        OccassionSuchazPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuchazapisOccassionSuchazModule {}
