import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { BuyLaterListItemSuchazComponent } from './buy-later-list-item-suchaz.component';
import { BuyLaterListItemSuchazDetailComponent } from './buy-later-list-item-suchaz-detail.component';
import { BuyLaterListItemSuchazPopupComponent } from './buy-later-list-item-suchaz-dialog.component';
import { BuyLaterListItemSuchazDeletePopupComponent } from './buy-later-list-item-suchaz-delete-dialog.component';

export const buyLaterListItemRoute: Routes = [
    {
        path: 'buy-later-list-item-suchaz',
        component: BuyLaterListItemSuchazComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BuyLaterListItems'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'buy-later-list-item-suchaz/:id',
        component: BuyLaterListItemSuchazDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BuyLaterListItems'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const buyLaterListItemPopupRoute: Routes = [
    {
        path: 'buy-later-list-item-suchaz-new',
        component: BuyLaterListItemSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BuyLaterListItems'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'buy-later-list-item-suchaz/:id/edit',
        component: BuyLaterListItemSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BuyLaterListItems'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'buy-later-list-item-suchaz/:id/delete',
        component: BuyLaterListItemSuchazDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BuyLaterListItems'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
