import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { ItemSuchazComponent } from './item-suchaz.component';
import { ItemSuchazDetailComponent } from './item-suchaz-detail.component';
import { ItemSuchazPopupComponent } from './item-suchaz-dialog.component';
import { ItemSuchazDeletePopupComponent } from './item-suchaz-delete-dialog.component';

export const itemRoute: Routes = [
    {
        path: 'item-suchaz',
        component: ItemSuchazComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Items'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'item-suchaz/:id',
        component: ItemSuchazDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Items'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const itemPopupRoute: Routes = [
    {
        path: 'item-suchaz-new',
        component: ItemSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Items'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'item-suchaz/:id/edit',
        component: ItemSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Items'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'item-suchaz/:id/delete',
        component: ItemSuchazDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Items'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
