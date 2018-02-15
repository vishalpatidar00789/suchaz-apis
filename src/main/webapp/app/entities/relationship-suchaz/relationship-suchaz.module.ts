import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuchazapisSharedModule } from '../../shared';
import {
    RelationshipSuchazService,
    RelationshipSuchazPopupService,
    RelationshipSuchazComponent,
    RelationshipSuchazDetailComponent,
    RelationshipSuchazDialogComponent,
    RelationshipSuchazPopupComponent,
    RelationshipSuchazDeletePopupComponent,
    RelationshipSuchazDeleteDialogComponent,
    relationshipRoute,
    relationshipPopupRoute,
} from './';

const ENTITY_STATES = [
    ...relationshipRoute,
    ...relationshipPopupRoute,
];

@NgModule({
    imports: [
        SuchazapisSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        RelationshipSuchazComponent,
        RelationshipSuchazDetailComponent,
        RelationshipSuchazDialogComponent,
        RelationshipSuchazDeleteDialogComponent,
        RelationshipSuchazPopupComponent,
        RelationshipSuchazDeletePopupComponent,
    ],
    entryComponents: [
        RelationshipSuchazComponent,
        RelationshipSuchazDialogComponent,
        RelationshipSuchazPopupComponent,
        RelationshipSuchazDeleteDialogComponent,
        RelationshipSuchazDeletePopupComponent,
    ],
    providers: [
        RelationshipSuchazService,
        RelationshipSuchazPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuchazapisRelationshipSuchazModule {}
