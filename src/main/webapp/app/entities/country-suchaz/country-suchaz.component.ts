import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { CountrySuchaz } from './country-suchaz.model';
import { CountrySuchazService } from './country-suchaz.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-country-suchaz',
    templateUrl: './country-suchaz.component.html'
})
export class CountrySuchazComponent implements OnInit, OnDestroy {
countries: CountrySuchaz[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private countryService: CountrySuchazService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.countryService.query().subscribe(
            (res: HttpResponse<CountrySuchaz[]>) => {
                this.countries = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInCountries();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: CountrySuchaz) {
        return item.id;
    }
    registerChangeInCountries() {
        this.eventSubscriber = this.eventManager.subscribe('countryListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
