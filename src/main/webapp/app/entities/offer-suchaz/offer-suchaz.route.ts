import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { OfferSuchazComponent } from './offer-suchaz.component';
import { OfferSuchazDetailComponent } from './offer-suchaz-detail.component';
import { OfferSuchazPopupComponent } from './offer-suchaz-dialog.component';
import { OfferSuchazDeletePopupComponent } from './offer-suchaz-delete-dialog.component';

export const offerRoute: Routes = [
    {
        path: 'offer-suchaz',
        component: OfferSuchazComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Offers'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'offer-suchaz/:id',
        component: OfferSuchazDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Offers'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const offerPopupRoute: Routes = [
    {
        path: 'offer-suchaz-new',
        component: OfferSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Offers'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'offer-suchaz/:id/edit',
        component: OfferSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Offers'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'offer-suchaz/:id/delete',
        component: OfferSuchazDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Offers'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
