import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { ConsumerProfileSuchazComponent } from './consumer-profile-suchaz.component';
import { ConsumerProfileSuchazDetailComponent } from './consumer-profile-suchaz-detail.component';
import { ConsumerProfileSuchazPopupComponent } from './consumer-profile-suchaz-dialog.component';
import { ConsumerProfileSuchazDeletePopupComponent } from './consumer-profile-suchaz-delete-dialog.component';

export const consumerProfileRoute: Routes = [
    {
        path: 'consumer-profile-suchaz',
        component: ConsumerProfileSuchazComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ConsumerProfiles'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'consumer-profile-suchaz/:id',
        component: ConsumerProfileSuchazDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ConsumerProfiles'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const consumerProfilePopupRoute: Routes = [
    {
        path: 'consumer-profile-suchaz-new',
        component: ConsumerProfileSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ConsumerProfiles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'consumer-profile-suchaz/:id/edit',
        component: ConsumerProfileSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ConsumerProfiles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'consumer-profile-suchaz/:id/delete',
        component: ConsumerProfileSuchazDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ConsumerProfiles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
