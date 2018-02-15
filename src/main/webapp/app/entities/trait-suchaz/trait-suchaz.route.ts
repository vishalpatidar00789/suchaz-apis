import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { TraitSuchazComponent } from './trait-suchaz.component';
import { TraitSuchazDetailComponent } from './trait-suchaz-detail.component';
import { TraitSuchazPopupComponent } from './trait-suchaz-dialog.component';
import { TraitSuchazDeletePopupComponent } from './trait-suchaz-delete-dialog.component';

export const traitRoute: Routes = [
    {
        path: 'trait-suchaz',
        component: TraitSuchazComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Traits'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'trait-suchaz/:id',
        component: TraitSuchazDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Traits'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const traitPopupRoute: Routes = [
    {
        path: 'trait-suchaz-new',
        component: TraitSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Traits'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'trait-suchaz/:id/edit',
        component: TraitSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Traits'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'trait-suchaz/:id/delete',
        component: TraitSuchazDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Traits'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
