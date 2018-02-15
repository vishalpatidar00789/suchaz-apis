import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { OccassionSuchazComponent } from './occassion-suchaz.component';
import { OccassionSuchazDetailComponent } from './occassion-suchaz-detail.component';
import { OccassionSuchazPopupComponent } from './occassion-suchaz-dialog.component';
import { OccassionSuchazDeletePopupComponent } from './occassion-suchaz-delete-dialog.component';

export const occassionRoute: Routes = [
    {
        path: 'occassion-suchaz',
        component: OccassionSuchazComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Occassions'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'occassion-suchaz/:id',
        component: OccassionSuchazDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Occassions'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const occassionPopupRoute: Routes = [
    {
        path: 'occassion-suchaz-new',
        component: OccassionSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Occassions'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'occassion-suchaz/:id/edit',
        component: OccassionSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Occassions'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'occassion-suchaz/:id/delete',
        component: OccassionSuchazDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Occassions'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
