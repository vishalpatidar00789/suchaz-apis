import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { WishListItemSuchazComponent } from './wish-list-item-suchaz.component';
import { WishListItemSuchazDetailComponent } from './wish-list-item-suchaz-detail.component';
import { WishListItemSuchazPopupComponent } from './wish-list-item-suchaz-dialog.component';
import { WishListItemSuchazDeletePopupComponent } from './wish-list-item-suchaz-delete-dialog.component';

export const wishListItemRoute: Routes = [
    {
        path: 'wish-list-item-suchaz',
        component: WishListItemSuchazComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'WishListItems'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'wish-list-item-suchaz/:id',
        component: WishListItemSuchazDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'WishListItems'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const wishListItemPopupRoute: Routes = [
    {
        path: 'wish-list-item-suchaz-new',
        component: WishListItemSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'WishListItems'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'wish-list-item-suchaz/:id/edit',
        component: WishListItemSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'WishListItems'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'wish-list-item-suchaz/:id/delete',
        component: WishListItemSuchazDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'WishListItems'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
