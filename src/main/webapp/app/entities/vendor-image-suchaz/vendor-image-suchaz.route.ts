import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { VendorImageSuchazComponent } from './vendor-image-suchaz.component';
import { VendorImageSuchazDetailComponent } from './vendor-image-suchaz-detail.component';
import { VendorImageSuchazPopupComponent } from './vendor-image-suchaz-dialog.component';
import { VendorImageSuchazDeletePopupComponent } from './vendor-image-suchaz-delete-dialog.component';

export const vendorImageRoute: Routes = [
    {
        path: 'vendor-image-suchaz',
        component: VendorImageSuchazComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'VendorImages'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'vendor-image-suchaz/:id',
        component: VendorImageSuchazDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'VendorImages'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const vendorImagePopupRoute: Routes = [
    {
        path: 'vendor-image-suchaz-new',
        component: VendorImageSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'VendorImages'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'vendor-image-suchaz/:id/edit',
        component: VendorImageSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'VendorImages'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'vendor-image-suchaz/:id/delete',
        component: VendorImageSuchazDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'VendorImages'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
