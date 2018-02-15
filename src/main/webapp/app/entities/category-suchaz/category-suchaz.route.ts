import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { CategorySuchazComponent } from './category-suchaz.component';
import { CategorySuchazDetailComponent } from './category-suchaz-detail.component';
import { CategorySuchazPopupComponent } from './category-suchaz-dialog.component';
import { CategorySuchazDeletePopupComponent } from './category-suchaz-delete-dialog.component';

export const categoryRoute: Routes = [
    {
        path: 'category-suchaz',
        component: CategorySuchazComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Categories'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'category-suchaz/:id',
        component: CategorySuchazDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Categories'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const categoryPopupRoute: Routes = [
    {
        path: 'category-suchaz-new',
        component: CategorySuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Categories'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'category-suchaz/:id/edit',
        component: CategorySuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Categories'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'category-suchaz/:id/delete',
        component: CategorySuchazDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Categories'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
