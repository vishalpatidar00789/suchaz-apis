import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { CountrySuchaz } from './country-suchaz.model';
import { CountrySuchazService } from './country-suchaz.service';

@Component({
    selector: 'jhi-country-suchaz-detail',
    templateUrl: './country-suchaz-detail.component.html'
})
export class CountrySuchazDetailComponent implements OnInit, OnDestroy {

    country: CountrySuchaz;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private countryService: CountrySuchazService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCountries();
    }

    load(id) {
        this.countryService.find(id)
            .subscribe((countryResponse: HttpResponse<CountrySuchaz>) => {
                this.country = countryResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCountries() {
        this.eventSubscriber = this.eventManager.subscribe(
            'countryListModification',
            (response) => this.load(this.country.id)
        );
    }
}
