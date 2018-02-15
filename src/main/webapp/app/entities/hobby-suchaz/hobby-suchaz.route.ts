import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { HobbySuchazComponent } from './hobby-suchaz.component';
import { HobbySuchazDetailComponent } from './hobby-suchaz-detail.component';
import { HobbySuchazPopupComponent } from './hobby-suchaz-dialog.component';
import { HobbySuchazDeletePopupComponent } from './hobby-suchaz-delete-dialog.component';

export const hobbyRoute: Routes = [
    {
        path: 'hobby-suchaz',
        component: HobbySuchazComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Hobbies'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'hobby-suchaz/:id',
        component: HobbySuchazDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Hobbies'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const hobbyPopupRoute: Routes = [
    {
        path: 'hobby-suchaz-new',
        component: HobbySuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Hobbies'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'hobby-suchaz/:id/edit',
        component: HobbySuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Hobbies'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'hobby-suchaz/:id/delete',
        component: HobbySuchazDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Hobbies'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
