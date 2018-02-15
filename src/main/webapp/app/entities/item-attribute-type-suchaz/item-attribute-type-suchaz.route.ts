import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { ItemAttributeTypeSuchazComponent } from './item-attribute-type-suchaz.component';
import { ItemAttributeTypeSuchazDetailComponent } from './item-attribute-type-suchaz-detail.component';
import { ItemAttributeTypeSuchazPopupComponent } from './item-attribute-type-suchaz-dialog.component';
import { ItemAttributeTypeSuchazDeletePopupComponent } from './item-attribute-type-suchaz-delete-dialog.component';

export const itemAttributeTypeRoute: Routes = [
    {
        path: 'item-attribute-type-suchaz',
        component: ItemAttributeTypeSuchazComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ItemAttributeTypes'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'item-attribute-type-suchaz/:id',
        component: ItemAttributeTypeSuchazDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ItemAttributeTypes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const itemAttributeTypePopupRoute: Routes = [
    {
        path: 'item-attribute-type-suchaz-new',
        component: ItemAttributeTypeSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ItemAttributeTypes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'item-attribute-type-suchaz/:id/edit',
        component: ItemAttributeTypeSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ItemAttributeTypes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'item-attribute-type-suchaz/:id/delete',
        component: ItemAttributeTypeSuchazDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ItemAttributeTypes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
