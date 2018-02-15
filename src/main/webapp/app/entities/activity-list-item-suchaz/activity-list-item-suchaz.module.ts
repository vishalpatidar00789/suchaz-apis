import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuchazapisSharedModule } from '../../shared';
import {
    ActivityListItemSuchazService,
    ActivityListItemSuchazPopupService,
    ActivityListItemSuchazComponent,
    ActivityListItemSuchazDetailComponent,
    ActivityListItemSuchazDialogComponent,
    ActivityListItemSuchazPopupComponent,
    ActivityListItemSuchazDeletePopupComponent,
    ActivityListItemSuchazDeleteDialogComponent,
    activityListItemRoute,
    activityListItemPopupRoute,
} from './';

const ENTITY_STATES = [
    ...activityListItemRoute,
    ...activityListItemPopupRoute,
];

@NgModule({
    imports: [
        SuchazapisSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ActivityListItemSuchazComponent,
        ActivityListItemSuchazDetailComponent,
        ActivityListItemSuchazDialogComponent,
        ActivityListItemSuchazDeleteDialogComponent,
        ActivityListItemSuchazPopupComponent,
        ActivityListItemSuchazDeletePopupComponent,
    ],
    entryComponents: [
        ActivityListItemSuchazComponent,
        ActivityListItemSuchazDialogComponent,
        ActivityListItemSuchazPopupComponent,
        ActivityListItemSuchazDeleteDialogComponent,
        ActivityListItemSuchazDeletePopupComponent,
    ],
    providers: [
        ActivityListItemSuchazService,
        ActivityListItemSuchazPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuchazapisActivityListItemSuchazModule {}
