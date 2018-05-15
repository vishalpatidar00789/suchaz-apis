import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { SuchAzMenuSuchazComponent } from './such-az-menu-suchaz.component';
import { SuchAzMenuSuchazDetailComponent } from './such-az-menu-suchaz-detail.component';
import { SuchAzMenuSuchazPopupComponent } from './such-az-menu-suchaz-dialog.component';
import { SuchAzMenuSuchazDeletePopupComponent } from './such-az-menu-suchaz-delete-dialog.component';

export const suchAzMenuRoute: Routes = [
    {
        path: 'such-az-menu-suchaz',
        component: SuchAzMenuSuchazComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SuchAzMenus'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'such-az-menu-suchaz/:id',
        component: SuchAzMenuSuchazDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SuchAzMenus'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const suchAzMenuPopupRoute: Routes = [
    {
        path: 'such-az-menu-suchaz-new',
        component: SuchAzMenuSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SuchAzMenus'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'such-az-menu-suchaz/:id/edit',
        component: SuchAzMenuSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SuchAzMenus'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'such-az-menu-suchaz/:id/delete',
        component: SuchAzMenuSuchazDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SuchAzMenus'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
