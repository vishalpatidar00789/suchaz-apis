import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { CategoryImageSuchazComponent } from './category-image-suchaz.component';
import { CategoryImageSuchazDetailComponent } from './category-image-suchaz-detail.component';
import { CategoryImageSuchazPopupComponent } from './category-image-suchaz-dialog.component';
import { CategoryImageSuchazDeletePopupComponent } from './category-image-suchaz-delete-dialog.component';

export const categoryImageRoute: Routes = [
    {
        path: 'category-image-suchaz',
        component: CategoryImageSuchazComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CategoryImages'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'category-image-suchaz/:id',
        component: CategoryImageSuchazDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CategoryImages'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const categoryImagePopupRoute: Routes = [
    {
        path: 'category-image-suchaz-new',
        component: CategoryImageSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CategoryImages'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'category-image-suchaz/:id/edit',
        component: CategoryImageSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CategoryImages'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'category-image-suchaz/:id/delete',
        component: CategoryImageSuchazDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CategoryImages'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
