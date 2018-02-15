import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { BuyLaterListSuchazComponent } from './buy-later-list-suchaz.component';
import { BuyLaterListSuchazDetailComponent } from './buy-later-list-suchaz-detail.component';
import { BuyLaterListSuchazPopupComponent } from './buy-later-list-suchaz-dialog.component';
import { BuyLaterListSuchazDeletePopupComponent } from './buy-later-list-suchaz-delete-dialog.component';

export const buyLaterListRoute: Routes = [
    {
        path: 'buy-later-list-suchaz',
        component: BuyLaterListSuchazComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BuyLaterLists'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'buy-later-list-suchaz/:id',
        component: BuyLaterListSuchazDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BuyLaterLists'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const buyLaterListPopupRoute: Routes = [
    {
        path: 'buy-later-list-suchaz-new',
        component: BuyLaterListSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BuyLaterLists'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'buy-later-list-suchaz/:id/edit',
        component: BuyLaterListSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BuyLaterLists'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'buy-later-list-suchaz/:id/delete',
        component: BuyLaterListSuchazDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BuyLaterLists'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
