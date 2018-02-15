import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { ItemImageSuchazComponent } from './item-image-suchaz.component';
import { ItemImageSuchazDetailComponent } from './item-image-suchaz-detail.component';
import { ItemImageSuchazPopupComponent } from './item-image-suchaz-dialog.component';
import { ItemImageSuchazDeletePopupComponent } from './item-image-suchaz-delete-dialog.component';

export const itemImageRoute: Routes = [
    {
        path: 'item-image-suchaz',
        component: ItemImageSuchazComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ItemImages'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'item-image-suchaz/:id',
        component: ItemImageSuchazDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ItemImages'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const itemImagePopupRoute: Routes = [
    {
        path: 'item-image-suchaz-new',
        component: ItemImageSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ItemImages'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'item-image-suchaz/:id/edit',
        component: ItemImageSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ItemImages'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'item-image-suchaz/:id/delete',
        component: ItemImageSuchazDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ItemImages'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
