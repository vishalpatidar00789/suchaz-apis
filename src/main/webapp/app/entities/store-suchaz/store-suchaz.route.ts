import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { StoreSuchazComponent } from './store-suchaz.component';
import { StoreSuchazDetailComponent } from './store-suchaz-detail.component';
import { StoreSuchazPopupComponent } from './store-suchaz-dialog.component';
import { StoreSuchazDeletePopupComponent } from './store-suchaz-delete-dialog.component';

export const storeRoute: Routes = [
    {
        path: 'store-suchaz',
        component: StoreSuchazComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Stores'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'store-suchaz/:id',
        component: StoreSuchazDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Stores'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const storePopupRoute: Routes = [
    {
        path: 'store-suchaz-new',
        component: StoreSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Stores'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'store-suchaz/:id/edit',
        component: StoreSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Stores'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'store-suchaz/:id/delete',
        component: StoreSuchazDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Stores'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
