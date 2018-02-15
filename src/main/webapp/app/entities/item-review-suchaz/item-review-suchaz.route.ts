import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { ItemReviewSuchazComponent } from './item-review-suchaz.component';
import { ItemReviewSuchazDetailComponent } from './item-review-suchaz-detail.component';
import { ItemReviewSuchazPopupComponent } from './item-review-suchaz-dialog.component';
import { ItemReviewSuchazDeletePopupComponent } from './item-review-suchaz-delete-dialog.component';

export const itemReviewRoute: Routes = [
    {
        path: 'item-review-suchaz',
        component: ItemReviewSuchazComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ItemReviews'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'item-review-suchaz/:id',
        component: ItemReviewSuchazDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ItemReviews'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const itemReviewPopupRoute: Routes = [
    {
        path: 'item-review-suchaz-new',
        component: ItemReviewSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ItemReviews'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'item-review-suchaz/:id/edit',
        component: ItemReviewSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ItemReviews'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'item-review-suchaz/:id/delete',
        component: ItemReviewSuchazDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ItemReviews'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
