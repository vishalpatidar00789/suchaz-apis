import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { CountrySuchazComponent } from './country-suchaz.component';
import { CountrySuchazDetailComponent } from './country-suchaz-detail.component';
import { CountrySuchazPopupComponent } from './country-suchaz-dialog.component';
import { CountrySuchazDeletePopupComponent } from './country-suchaz-delete-dialog.component';

export const countryRoute: Routes = [
    {
        path: 'country-suchaz',
        component: CountrySuchazComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Countries'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'country-suchaz/:id',
        component: CountrySuchazDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Countries'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const countryPopupRoute: Routes = [
    {
        path: 'country-suchaz-new',
        component: CountrySuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Countries'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'country-suchaz/:id/edit',
        component: CountrySuchazPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Countries'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'country-suchaz/:id/delete',
        component: CountrySuchazDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Countries'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
