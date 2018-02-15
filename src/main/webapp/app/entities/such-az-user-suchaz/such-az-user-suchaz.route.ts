import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { SuchAzUserSuchazComponent } from './such-az-user-suchaz.component';
import { SuchAzUserSuchazDetailComponent } from './such-az-user-suchaz-detail.component';
import { SuchAzUserSuchazPopupComponent } from './such-az-user-suchaz-dialog.component';
import { SuchAzUserSuchazDeletePopupComponent } from './such-az-user-suchaz-delete-dialog.component';

export const suchAzUserRoute: Routes = [
    {
        path: 'such-az-user-suchaz',
        component: SuchAzUserSuchazComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SuchAzUsers'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'such-az-user-suchaz/:id',
        component: SuchAzUserSuchazDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SuchAzUsers'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const suchAzUserPopupRoute: Routes = [
    {
        path: 'such-az-user-suchaz-new',
        component: SuchAzUserSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SuchAzUsers'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'such-az-user-suchaz/:id/edit',
        component: SuchAzUserSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SuchAzUsers'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'such-az-user-suchaz/:id/delete',
        component: SuchAzUserSuchazDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SuchAzUsers'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
