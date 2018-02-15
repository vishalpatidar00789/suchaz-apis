import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuchazapisSharedModule } from '../../shared';
import {
    CategoryImageSuchazService,
    CategoryImageSuchazPopupService,
    CategoryImageSuchazComponent,
    CategoryImageSuchazDetailComponent,
    CategoryImageSuchazDialogComponent,
    CategoryImageSuchazPopupComponent,
    CategoryImageSuchazDeletePopupComponent,
    CategoryImageSuchazDeleteDialogComponent,
    categoryImageRoute,
    categoryImagePopupRoute,
} from './';

const ENTITY_STATES = [
    ...categoryImageRoute,
    ...categoryImagePopupRoute,
];

@NgModule({
    imports: [
        SuchazapisSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        CategoryImageSuchazComponent,
        CategoryImageSuchazDetailComponent,
        CategoryImageSuchazDialogComponent,
        CategoryImageSuchazDeleteDialogComponent,
        CategoryImageSuchazPopupComponent,
        CategoryImageSuchazDeletePopupComponent,
    ],
    entryComponents: [
        CategoryImageSuchazComponent,
        CategoryImageSuchazDialogComponent,
        CategoryImageSuchazPopupComponent,
        CategoryImageSuchazDeleteDialogComponent,
        CategoryImageSuchazDeletePopupComponent,
    ],
    providers: [
        CategoryImageSuchazService,
        CategoryImageSuchazPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuchazapisCategoryImageSuchazModule {}
