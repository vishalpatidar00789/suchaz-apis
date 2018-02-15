import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuchazapisSharedModule } from '../../shared';
import {
    ActivityListSuchazService,
    ActivityListSuchazPopupService,
    ActivityListSuchazComponent,
    ActivityListSuchazDetailComponent,
    ActivityListSuchazDialogComponent,
    ActivityListSuchazPopupComponent,
    ActivityListSuchazDeletePopupComponent,
    ActivityListSuchazDeleteDialogComponent,
    activityListRoute,
    activityListPopupRoute,
} from './';

const ENTITY_STATES = [
    ...activityListRoute,
    ...activityListPopupRoute,
];

@NgModule({
    imports: [
        SuchazapisSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ActivityListSuchazComponent,
        ActivityListSuchazDetailComponent,
        ActivityListSuchazDialogComponent,
        ActivityListSuchazDeleteDialogComponent,
        ActivityListSuchazPopupComponent,
        ActivityListSuchazDeletePopupComponent,
    ],
    entryComponents: [
        ActivityListSuchazComponent,
        ActivityListSuchazDialogComponent,
        ActivityListSuchazPopupComponent,
        ActivityListSuchazDeleteDialogComponent,
        ActivityListSuchazDeletePopupComponent,
    ],
    providers: [
        ActivityListSuchazService,
        ActivityListSuchazPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuchazapisActivityListSuchazModule {}
