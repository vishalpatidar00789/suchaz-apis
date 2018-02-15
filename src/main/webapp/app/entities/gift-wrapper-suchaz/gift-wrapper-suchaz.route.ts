import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { GiftWrapperSuchazComponent } from './gift-wrapper-suchaz.component';
import { GiftWrapperSuchazDetailComponent } from './gift-wrapper-suchaz-detail.component';
import { GiftWrapperSuchazPopupComponent } from './gift-wrapper-suchaz-dialog.component';
import { GiftWrapperSuchazDeletePopupComponent } from './gift-wrapper-suchaz-delete-dialog.component';

export const giftWrapperRoute: Routes = [
    {
        path: 'gift-wrapper-suchaz',
        component: GiftWrapperSuchazComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'GiftWrappers'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'gift-wrapper-suchaz/:id',
        component: GiftWrapperSuchazDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'GiftWrappers'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const giftWrapperPopupRoute: Routes = [
    {
        path: 'gift-wrapper-suchaz-new',
        component: GiftWrapperSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'GiftWrappers'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'gift-wrapper-suchaz/:id/edit',
        component: GiftWrapperSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'GiftWrappers'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'gift-wrapper-suchaz/:id/delete',
        component: GiftWrapperSuchazDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'GiftWrappers'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
