import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { UserGiftWrapperSuchazComponent } from './user-gift-wrapper-suchaz.component';
import { UserGiftWrapperSuchazDetailComponent } from './user-gift-wrapper-suchaz-detail.component';
import { UserGiftWrapperSuchazPopupComponent } from './user-gift-wrapper-suchaz-dialog.component';
import { UserGiftWrapperSuchazDeletePopupComponent } from './user-gift-wrapper-suchaz-delete-dialog.component';

export const userGiftWrapperRoute: Routes = [
    {
        path: 'user-gift-wrapper-suchaz',
        component: UserGiftWrapperSuchazComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UserGiftWrappers'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'user-gift-wrapper-suchaz/:id',
        component: UserGiftWrapperSuchazDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UserGiftWrappers'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const userGiftWrapperPopupRoute: Routes = [
    {
        path: 'user-gift-wrapper-suchaz-new',
        component: UserGiftWrapperSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UserGiftWrappers'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'user-gift-wrapper-suchaz/:id/edit',
        component: UserGiftWrapperSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UserGiftWrappers'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'user-gift-wrapper-suchaz/:id/delete',
        component: UserGiftWrapperSuchazDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UserGiftWrappers'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
