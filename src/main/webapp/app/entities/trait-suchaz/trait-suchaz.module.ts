import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuchazapisSharedModule } from '../../shared';
import {
    TraitSuchazService,
    TraitSuchazPopupService,
    TraitSuchazComponent,
    TraitSuchazDetailComponent,
    TraitSuchazDialogComponent,
    TraitSuchazPopupComponent,
    TraitSuchazDeletePopupComponent,
    TraitSuchazDeleteDialogComponent,
    traitRoute,
    traitPopupRoute,
} from './';

const ENTITY_STATES = [
    ...traitRoute,
    ...traitPopupRoute,
];

@NgModule({
    imports: [
        SuchazapisSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        TraitSuchazComponent,
        TraitSuchazDetailComponent,
        TraitSuchazDialogComponent,
        TraitSuchazDeleteDialogComponent,
        TraitSuchazPopupComponent,
        TraitSuchazDeletePopupComponent,
    ],
    entryComponents: [
        TraitSuchazComponent,
        TraitSuchazDialogComponent,
        TraitSuchazPopupComponent,
        TraitSuchazDeleteDialogComponent,
        TraitSuchazDeletePopupComponent,
    ],
    providers: [
        TraitSuchazService,
        TraitSuchazPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuchazapisTraitSuchazModule {}
