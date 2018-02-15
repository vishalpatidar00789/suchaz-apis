import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { StoreImageSuchazComponent } from './store-image-suchaz.component';
import { StoreImageSuchazDetailComponent } from './store-image-suchaz-detail.component';
import { StoreImageSuchazPopupComponent } from './store-image-suchaz-dialog.component';
import { StoreImageSuchazDeletePopupComponent } from './store-image-suchaz-delete-dialog.component';

export const storeImageRoute: Routes = [
    {
        path: 'store-image-suchaz',
        component: StoreImageSuchazComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StoreImages'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'store-image-suchaz/:id',
        component: StoreImageSuchazDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StoreImages'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const storeImagePopupRoute: Routes = [
    {
        path: 'store-image-suchaz-new',
        component: StoreImageSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StoreImages'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'store-image-suchaz/:id/edit',
        component: StoreImageSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StoreImages'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'store-image-suchaz/:id/delete',
        component: StoreImageSuchazDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StoreImages'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
