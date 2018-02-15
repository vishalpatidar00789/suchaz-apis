import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuchazapisSharedModule } from '../../shared';
import {
    TraitImageSuchazService,
    TraitImageSuchazPopupService,
    TraitImageSuchazComponent,
    TraitImageSuchazDetailComponent,
    TraitImageSuchazDialogComponent,
    TraitImageSuchazPopupComponent,
    TraitImageSuchazDeletePopupComponent,
    TraitImageSuchazDeleteDialogComponent,
    traitImageRoute,
    traitImagePopupRoute,
} from './';

const ENTITY_STATES = [
    ...traitImageRoute,
    ...traitImagePopupRoute,
];

@NgModule({
    imports: [
        SuchazapisSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        TraitImageSuchazComponent,
        TraitImageSuchazDetailComponent,
        TraitImageSuchazDialogComponent,
        TraitImageSuchazDeleteDialogComponent,
        TraitImageSuchazPopupComponent,
        TraitImageSuchazDeletePopupComponent,
    ],
    entryComponents: [
        TraitImageSuchazComponent,
        TraitImageSuchazDialogComponent,
        TraitImageSuchazPopupComponent,
        TraitImageSuchazDeleteDialogComponent,
        TraitImageSuchazDeletePopupComponent,
    ],
    providers: [
        TraitImageSuchazService,
        TraitImageSuchazPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuchazapisTraitImageSuchazModule {}
