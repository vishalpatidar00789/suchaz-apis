import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { TraitImageSuchazComponent } from './trait-image-suchaz.component';
import { TraitImageSuchazDetailComponent } from './trait-image-suchaz-detail.component';
import { TraitImageSuchazPopupComponent } from './trait-image-suchaz-dialog.component';
import { TraitImageSuchazDeletePopupComponent } from './trait-image-suchaz-delete-dialog.component';

export const traitImageRoute: Routes = [
    {
        path: 'trait-image-suchaz',
        component: TraitImageSuchazComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TraitImages'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'trait-image-suchaz/:id',
        component: TraitImageSuchazDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TraitImages'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const traitImagePopupRoute: Routes = [
    {
        path: 'trait-image-suchaz-new',
        component: TraitImageSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TraitImages'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'trait-image-suchaz/:id/edit',
        component: TraitImageSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TraitImages'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'trait-image-suchaz/:id/delete',
        component: TraitImageSuchazDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TraitImages'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
