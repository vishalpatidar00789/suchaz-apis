import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuchazapisSharedModule } from '../../shared';
import {
    RelationshipImageSuchazService,
    RelationshipImageSuchazPopupService,
    RelationshipImageSuchazComponent,
    RelationshipImageSuchazDetailComponent,
    RelationshipImageSuchazDialogComponent,
    RelationshipImageSuchazPopupComponent,
    RelationshipImageSuchazDeletePopupComponent,
    RelationshipImageSuchazDeleteDialogComponent,
    relationshipImageRoute,
    relationshipImagePopupRoute,
} from './';

const ENTITY_STATES = [
    ...relationshipImageRoute,
    ...relationshipImagePopupRoute,
];

@NgModule({
    imports: [
        SuchazapisSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        RelationshipImageSuchazComponent,
        RelationshipImageSuchazDetailComponent,
        RelationshipImageSuchazDialogComponent,
        RelationshipImageSuchazDeleteDialogComponent,
        RelationshipImageSuchazPopupComponent,
        RelationshipImageSuchazDeletePopupComponent,
    ],
    entryComponents: [
        RelationshipImageSuchazComponent,
        RelationshipImageSuchazDialogComponent,
        RelationshipImageSuchazPopupComponent,
        RelationshipImageSuchazDeleteDialogComponent,
        RelationshipImageSuchazDeletePopupComponent,
    ],
    providers: [
        RelationshipImageSuchazService,
        RelationshipImageSuchazPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuchazapisRelationshipImageSuchazModule {}
