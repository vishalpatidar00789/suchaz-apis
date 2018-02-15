import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { RelationshipSuchazComponent } from './relationship-suchaz.component';
import { RelationshipSuchazDetailComponent } from './relationship-suchaz-detail.component';
import { RelationshipSuchazPopupComponent } from './relationship-suchaz-dialog.component';
import { RelationshipSuchazDeletePopupComponent } from './relationship-suchaz-delete-dialog.component';

export const relationshipRoute: Routes = [
    {
        path: 'relationship-suchaz',
        component: RelationshipSuchazComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Relationships'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'relationship-suchaz/:id',
        component: RelationshipSuchazDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Relationships'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const relationshipPopupRoute: Routes = [
    {
        path: 'relationship-suchaz-new',
        component: RelationshipSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Relationships'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'relationship-suchaz/:id/edit',
        component: RelationshipSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Relationships'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'relationship-suchaz/:id/delete',
        component: RelationshipSuchazDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Relationships'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
