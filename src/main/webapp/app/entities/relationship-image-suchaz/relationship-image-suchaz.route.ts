import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { RelationshipImageSuchazComponent } from './relationship-image-suchaz.component';
import { RelationshipImageSuchazDetailComponent } from './relationship-image-suchaz-detail.component';
import { RelationshipImageSuchazPopupComponent } from './relationship-image-suchaz-dialog.component';
import { RelationshipImageSuchazDeletePopupComponent } from './relationship-image-suchaz-delete-dialog.component';

export const relationshipImageRoute: Routes = [
    {
        path: 'relationship-image-suchaz',
        component: RelationshipImageSuchazComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RelationshipImages'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'relationship-image-suchaz/:id',
        component: RelationshipImageSuchazDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RelationshipImages'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const relationshipImagePopupRoute: Routes = [
    {
        path: 'relationship-image-suchaz-new',
        component: RelationshipImageSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RelationshipImages'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'relationship-image-suchaz/:id/edit',
        component: RelationshipImageSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RelationshipImages'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'relationship-image-suchaz/:id/delete',
        component: RelationshipImageSuchazDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RelationshipImages'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
