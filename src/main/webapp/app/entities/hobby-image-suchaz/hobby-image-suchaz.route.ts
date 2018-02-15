import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { HobbyImageSuchazComponent } from './hobby-image-suchaz.component';
import { HobbyImageSuchazDetailComponent } from './hobby-image-suchaz-detail.component';
import { HobbyImageSuchazPopupComponent } from './hobby-image-suchaz-dialog.component';
import { HobbyImageSuchazDeletePopupComponent } from './hobby-image-suchaz-delete-dialog.component';

export const hobbyImageRoute: Routes = [
    {
        path: 'hobby-image-suchaz',
        component: HobbyImageSuchazComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'HobbyImages'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'hobby-image-suchaz/:id',
        component: HobbyImageSuchazDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'HobbyImages'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const hobbyImagePopupRoute: Routes = [
    {
        path: 'hobby-image-suchaz-new',
        component: HobbyImageSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'HobbyImages'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'hobby-image-suchaz/:id/edit',
        component: HobbyImageSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'HobbyImages'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'hobby-image-suchaz/:id/delete',
        component: HobbyImageSuchazDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'HobbyImages'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
