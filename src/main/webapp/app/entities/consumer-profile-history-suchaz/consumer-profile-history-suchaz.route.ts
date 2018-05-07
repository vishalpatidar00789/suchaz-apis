import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { ConsumerProfileHistorySuchazComponent } from './consumer-profile-history-suchaz.component';
import { ConsumerProfileHistorySuchazDetailComponent } from './consumer-profile-history-suchaz-detail.component';
import { ConsumerProfileHistorySuchazPopupComponent } from './consumer-profile-history-suchaz-dialog.component';
import {
    ConsumerProfileHistorySuchazDeletePopupComponent
} from './consumer-profile-history-suchaz-delete-dialog.component';

export const consumerProfileHistoryRoute: Routes = [
    {
        path: 'consumer-profile-history-suchaz',
        component: ConsumerProfileHistorySuchazComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ConsumerProfileHistories'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'consumer-profile-history-suchaz/:id',
        component: ConsumerProfileHistorySuchazDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ConsumerProfileHistories'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const consumerProfileHistoryPopupRoute: Routes = [
    {
        path: 'consumer-profile-history-suchaz-new',
        component: ConsumerProfileHistorySuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ConsumerProfileHistories'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'consumer-profile-history-suchaz/:id/edit',
        component: ConsumerProfileHistorySuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ConsumerProfileHistories'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'consumer-profile-history-suchaz/:id/delete',
        component: ConsumerProfileHistorySuchazDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ConsumerProfileHistories'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
