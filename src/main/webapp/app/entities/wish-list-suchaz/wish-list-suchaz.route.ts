import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { WishListSuchazComponent } from './wish-list-suchaz.component';
import { WishListSuchazDetailComponent } from './wish-list-suchaz-detail.component';
import { WishListSuchazPopupComponent } from './wish-list-suchaz-dialog.component';
import { WishListSuchazDeletePopupComponent } from './wish-list-suchaz-delete-dialog.component';

export const wishListRoute: Routes = [
    {
        path: 'wish-list-suchaz',
        component: WishListSuchazComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'WishLists'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'wish-list-suchaz/:id',
        component: WishListSuchazDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'WishLists'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const wishListPopupRoute: Routes = [
    {
        path: 'wish-list-suchaz-new',
        component: WishListSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'WishLists'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'wish-list-suchaz/:id/edit',
        component: WishListSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'WishLists'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'wish-list-suchaz/:id/delete',
        component: WishListSuchazDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'WishLists'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
