import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { ActivityListSuchazComponent } from './activity-list-suchaz.component';
import { ActivityListSuchazDetailComponent } from './activity-list-suchaz-detail.component';
import { ActivityListSuchazPopupComponent } from './activity-list-suchaz-dialog.component';
import { ActivityListSuchazDeletePopupComponent } from './activity-list-suchaz-delete-dialog.component';

export const activityListRoute: Routes = [
    {
        path: 'activity-list-suchaz',
        component: ActivityListSuchazComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ActivityLists'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'activity-list-suchaz/:id',
        component: ActivityListSuchazDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ActivityLists'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const activityListPopupRoute: Routes = [
    {
        path: 'activity-list-suchaz-new',
        component: ActivityListSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ActivityLists'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'activity-list-suchaz/:id/edit',
        component: ActivityListSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ActivityLists'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'activity-list-suchaz/:id/delete',
        component: ActivityListSuchazDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ActivityLists'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
