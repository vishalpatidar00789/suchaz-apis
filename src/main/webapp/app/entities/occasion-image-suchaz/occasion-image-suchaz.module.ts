import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuchazapisSharedModule } from '../../shared';
import {
    OccasionImageSuchazService,
    OccasionImageSuchazPopupService,
    OccasionImageSuchazComponent,
    OccasionImageSuchazDetailComponent,
    OccasionImageSuchazDialogComponent,
    OccasionImageSuchazPopupComponent,
    OccasionImageSuchazDeletePopupComponent,
    OccasionImageSuchazDeleteDialogComponent,
    occasionImageRoute,
    occasionImagePopupRoute,
} from './';

const ENTITY_STATES = [
    ...occasionImageRoute,
    ...occasionImagePopupRoute,
];

@NgModule({
    imports: [
        SuchazapisSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        OccasionImageSuchazComponent,
        OccasionImageSuchazDetailComponent,
        OccasionImageSuchazDialogComponent,
        OccasionImageSuchazDeleteDialogComponent,
        OccasionImageSuchazPopupComponent,
        OccasionImageSuchazDeletePopupComponent,
    ],
    entryComponents: [
        OccasionImageSuchazComponent,
        OccasionImageSuchazDialogComponent,
        OccasionImageSuchazPopupComponent,
        OccasionImageSuchazDeleteDialogComponent,
        OccasionImageSuchazDeletePopupComponent,
    ],
    providers: [
        OccasionImageSuchazService,
        OccasionImageSuchazPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuchazapisOccasionImageSuchazModule {}
