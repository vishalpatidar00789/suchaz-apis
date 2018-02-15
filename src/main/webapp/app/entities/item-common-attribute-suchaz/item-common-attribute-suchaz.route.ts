import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { ItemCommonAttributeSuchazComponent } from './item-common-attribute-suchaz.component';
import { ItemCommonAttributeSuchazDetailComponent } from './item-common-attribute-suchaz-detail.component';
import { ItemCommonAttributeSuchazPopupComponent } from './item-common-attribute-suchaz-dialog.component';
import { ItemCommonAttributeSuchazDeletePopupComponent } from './item-common-attribute-suchaz-delete-dialog.component';

export const itemCommonAttributeRoute: Routes = [
    {
        path: 'item-common-attribute-suchaz',
        component: ItemCommonAttributeSuchazComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ItemCommonAttributes'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'item-common-attribute-suchaz/:id',
        component: ItemCommonAttributeSuchazDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ItemCommonAttributes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const itemCommonAttributePopupRoute: Routes = [
    {
        path: 'item-common-attribute-suchaz-new',
        component: ItemCommonAttributeSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ItemCommonAttributes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'item-common-attribute-suchaz/:id/edit',
        component: ItemCommonAttributeSuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ItemCommonAttributes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'item-common-attribute-suchaz/:id/delete',
        component: ItemCommonAttributeSuchazDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ItemCommonAttributes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
