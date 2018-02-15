import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { OfferSuchaz } from './offer-suchaz.model';
import { OfferSuchazService } from './offer-suchaz.service';

@Component({
    selector: 'jhi-offer-suchaz-detail',
    templateUrl: './offer-suchaz-detail.component.html'
})
export class OfferSuchazDetailComponent implements OnInit, OnDestroy {

    offer: OfferSuchaz;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private offerService: OfferSuchazService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInOffers();
    }

    load(id) {
        this.offerService.find(id)
            .subscribe((offerResponse: HttpResponse<OfferSuchaz>) => {
                this.offer = offerResponse.body;
            });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInOffers() {
        this.eventSubscriber = this.eventManager.subscribe(
            'offerListModification',
            (response) => this.load(this.offer.id)
        );
    }
}
