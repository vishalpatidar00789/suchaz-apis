import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { VendorSuchazComponent } from './vendor-suchaz.component';
import { VendorSuchazDetailComponent } from './vendor-suchaz-detail.component';
import { VendorSuchazPopupComponent } from './vendor-suchaz-dialog.component';
import { VendorSuchazDeletePopupComponent } from './vendor-suchaz-delete-dialog.component';

export const vendorRoute: Routes = [
    {
        path: 'vendor-suchaz',
        component: VendorSuchazComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Vendors'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'vendor-suchaz/:id',
        component: VendorSuchazDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Vendors'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const vendorPopupRoute: Routes = [
    {
        path: 'vendor-suchaz-new',
        component: VendorSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Vendors'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'vendor-suchaz/:id/edit',
        component: VendorSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Vendors'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'vendor-suchaz/:id/delete',
        component: VendorSuchazDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Vendors'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
