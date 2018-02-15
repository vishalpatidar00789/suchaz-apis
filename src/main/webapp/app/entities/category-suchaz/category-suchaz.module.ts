import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuchazapisSharedModule } from '../../shared';
import {
    CategorySuchazService,
    CategorySuchazPopupService,
    CategorySuchazComponent,
    CategorySuchazDetailComponent,
    CategorySuchazDialogComponent,
    CategorySuchazPopupComponent,
    CategorySuchazDeletePopupComponent,
    CategorySuchazDeleteDialogComponent,
    categoryRoute,
    categoryPopupRoute,
} from './';

const ENTITY_STATES = [
    ...categoryRoute,
    ...categoryPopupRoute,
];

@NgModule({
    imports: [
        SuchazapisSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        CategorySuchazComponent,
        CategorySuchazDetailComponent,
        CategorySuchazDialogComponent,
        CategorySuchazDeleteDialogComponent,
        CategorySuchazPopupComponent,
        CategorySuchazDeletePopupComponent,
    ],
    entryComponents: [
        CategorySuchazComponent,
        CategorySuchazDialogComponent,
        CategorySuchazPopupComponent,
        CategorySuchazDeleteDialogComponent,
        CategorySuchazDeletePopupComponent,
    ],
    providers: [
        CategorySuchazService,
        CategorySuchazPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuchazapisCategorySuchazModule {}
