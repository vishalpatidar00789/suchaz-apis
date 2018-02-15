import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { ActivityListItemSuchazComponent } from './activity-list-item-suchaz.component';
import { ActivityListItemSuchazDetailComponent } from './activity-list-item-suchaz-detail.component';
import { ActivityListItemSuchazPopupComponent } from './activity-list-item-suchaz-dialog.component';
import { ActivityListItemSuchazDeletePopupComponent } from './activity-list-item-suchaz-delete-dialog.component';

export const activityListItemRoute: Routes = [
    {
        path: 'activity-list-item-suchaz',
        component: ActivityListItemSuchazComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ActivityListItems'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'activity-list-item-suchaz/:id',
        component: ActivityListItemSuchazDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ActivityListItems'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const activityListItemPopupRoute: Routes = [
    {
        path: 'activity-list-item-suchaz-new',
        component: ActivityListItemSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ActivityListItems'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'activity-list-item-suchaz/:id/edit',
        component: ActivityListItemSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ActivityListItems'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'activity-list-item-suchaz/:id/delete',
        component: ActivityListItemSuchazDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ActivityListItems'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
