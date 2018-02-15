import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { OccasionImageSuchazComponent } from './occasion-image-suchaz.component';
import { OccasionImageSuchazDetailComponent } from './occasion-image-suchaz-detail.component';
import { OccasionImageSuchazPopupComponent } from './occasion-image-suchaz-dialog.component';
import { OccasionImageSuchazDeletePopupComponent } from './occasion-image-suchaz-delete-dialog.component';

export const occasionImageRoute: Routes = [
    {
        path: 'occasion-image-suchaz',
        component: OccasionImageSuchazComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'OccasionImages'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'occasion-image-suchaz/:id',
        component: OccasionImageSuchazDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'OccasionImages'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const occasionImagePopupRoute: Routes = [
    {
        path: 'occasion-image-suchaz-new',
        component: OccasionImageSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'OccasionImages'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'occasion-image-suchaz/:id/edit',
        component: OccasionImageSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'OccasionImages'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'occasion-image-suchaz/:id/delete',
        component: OccasionImageSuchazDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'OccasionImages'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
