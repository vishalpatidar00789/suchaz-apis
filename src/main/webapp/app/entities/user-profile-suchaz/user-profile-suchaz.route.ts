import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { UserProfileSuchazComponent } from './user-profile-suchaz.component';
import { UserProfileSuchazDetailComponent } from './user-profile-suchaz-detail.component';
import { UserProfileSuchazPopupComponent } from './user-profile-suchaz-dialog.component';
import { UserProfileSuchazDeletePopupComponent } from './user-profile-suchaz-delete-dialog.component';

export const userProfileRoute: Routes = [
    {
        path: 'user-profile-suchaz',
        component: UserProfileSuchazComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UserProfiles'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'user-profile-suchaz/:id',
        component: UserProfileSuchazDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UserProfiles'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const userProfilePopupRoute: Routes = [
    {
        path: 'user-profile-suchaz-new',
        component: UserProfileSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UserProfiles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'user-profile-suchaz/:id/edit',
        component: UserProfileSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UserProfiles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'user-profile-suchaz/:id/delete',
        component: UserProfileSuchazDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UserProfiles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
